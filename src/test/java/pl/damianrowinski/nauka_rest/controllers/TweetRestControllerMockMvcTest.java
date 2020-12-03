package pl.damianrowinski.nauka_rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.damianrowinski.nauka_rest.domain.dto.TweetDTO;
import pl.damianrowinski.nauka_rest.domain.dto.UserDTO;
import pl.damianrowinski.nauka_rest.domain.model.User;
import pl.damianrowinski.nauka_rest.domain.repositories.UserRepository;
import pl.damianrowinski.nauka_rest.services.TweetService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// testing only web layer wo Spring Context
// web layer for particular controller

@WebMvcTest(TweetRestController.class)
public class TweetRestControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TweetService tweetService;

    private UserDTO userDTO;

    @BeforeEach
    void init() {
        User user = new User();
        user.setEmail("test@email.pl");
        user.setFirstName("Damian");
        user.setLastName("Rowiński");
        user.setPassword("TEST_PASSWORD");
        user.setId(1L);
        userDTO = new UserDTO();
        userDTO.setId(user.getId());

        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(user));
    }

    @Test
    void shouldReturnNotFoundStatusWhenListIsEmpty() throws Exception {
        mockMvc.perform(get("/api/tweets"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnListWithOneTweet() throws Exception {
        //given
        TweetDTO tweet = new TweetDTO();
        tweet.setTweetText("TEST TEXT");
        tweet.setTweetTitle("TITLE TEST");

        tweet.setUserDTO(userDTO);

        List<TweetDTO> tweetList = List.of(tweet);
        String tweetListJson = objectMapper.writeValueAsString(tweetList);

        System.out.println(tweetListJson);

        //when
        when(tweetService.list()).thenReturn(tweetList);

        //then
        mockMvc.perform(get("/api/tweets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(tweetListJson));
    }


}
