package pl.damianrowinski.nauka_rest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.damianrowinski.nauka_rest.domain.dto.TweetDTO;
import pl.damianrowinski.nauka_rest.domain.dto.UserDTO;
import pl.damianrowinski.nauka_rest.domain.model.User;
import pl.damianrowinski.nauka_rest.domain.repositories.UserRepository;
import pl.damianrowinski.nauka_rest.exceptions.ElementNotFoundException;
import pl.damianrowinski.nauka_rest.exceptions.UserIdNotPresentException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

// test with live server and H2 database, repository is not mocked

@SpringBootTest
public class TweetServiceIntegrationTest {

    @Autowired
    TweetService tweetService;

    @Autowired
    UserRepository userRepository;

    private UserDTO userDTO;

    @BeforeEach
    void init() {
        User user = new User();
        user.setEmail("test@email.pl");
        user.setFirstName("Damian");
        user.setLastName("Rowiński");
        user.setPassword("TEST_PASSWORD");
        User savedUser = userRepository.save(user);
        userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
    }

    @Test
    void givenTweetDtoShouldReturnSavedTweetLongId() throws UserIdNotPresentException, ElementNotFoundException {
        //given
        TweetDTO tweetDTO = TweetDTO.builder()
                .tweetText("TEST TEXT")
                .tweetTitle("TITLE TEST")
                .userDTO(userDTO).build();

        //when
        Long tweetId = tweetService.create(tweetDTO);

        //then
        assertThat(tweetId).isNotNull();
    }

    @Test
    void shouldReturnListOfTweets() throws UserIdNotPresentException, ElementNotFoundException {
        //given
        TweetDTO tweetDTO = TweetDTO.builder()
                .tweetText("TEST TEXT")
                .tweetTitle("TITLE TEST")
                .userDTO(userDTO).build();

        tweetService.create(tweetDTO);

        //when
        List<TweetDTO> listTweetsDTO = tweetService.list();
        TweetDTO firstTweetDTO = listTweetsDTO.get(0);

        //then
        assertAll(
                () -> assertThat(listTweetsDTO.size()).isEqualTo(1),
                () -> assertThat(firstTweetDTO.getTweetTitle()).isEqualTo(tweetDTO.getTweetTitle()),
                () -> assertThat(firstTweetDTO.getTweetText()).isEqualTo(tweetDTO.getTweetText()),
                () -> assertThat(firstTweetDTO.getUserDTO().getId()).isEqualTo(userDTO.getId())
        );
    }

}
