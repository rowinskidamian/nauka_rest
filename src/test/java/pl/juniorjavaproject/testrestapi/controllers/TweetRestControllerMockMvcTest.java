package pl.juniorjavaproject.testrestapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.juniorjavaproject.testrestapi.domain.dto.TweetDTO;
import pl.juniorjavaproject.testrestapi.domain.dto.UserDTO;
import pl.juniorjavaproject.testrestapi.services.TweetManagerService;
import pl.juniorjavaproject.testrestapi.services.TweetService;

import java.util.List;

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
    private TweetService tweetService;
    @MockBean private TweetManagerService tweetManagerService;

    @Test
    void shouldReturnNotFoundStatusWhenListIsEmpty() throws Exception {
        this.mockMvc.perform(get("/api/tweets"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnListWithOneTweet() throws Exception {
        //given
        TweetDTO tweet = new TweetDTO();
        tweet.setTweetText("TEST TEXT");
        tweet.setTweetTitle("TITLE TEST");

        UserDTO user = new UserDTO();
        user.setId(1L);
        tweet.setUserDTO(user);

        List<TweetDTO> tweetList = List.of(tweet);
        String tweetListJson = objectMapper.writeValueAsString(tweetList);

        //when
        when(tweetService.list()).thenReturn(tweetList);

        //then
        this.mockMvc.perform(get("/api/tweets"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(tweetListJson));
    }
}
