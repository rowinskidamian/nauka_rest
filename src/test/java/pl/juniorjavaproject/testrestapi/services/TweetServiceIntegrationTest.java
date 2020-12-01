package pl.juniorjavaproject.testrestapi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.juniorjavaproject.testrestapi.domain.dto.TweetDTO;
import pl.juniorjavaproject.testrestapi.domain.dto.UserDTO;
import pl.juniorjavaproject.testrestapi.exceptions.UserIdNotPresentException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class TweetServiceIntegrationTest {

    @Autowired
    TweetService tweetService;

    @Test
    void givenTweetDtoShouldReturnSavedTweetLongId() throws UserIdNotPresentException {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        TweetDTO tweetDTO = TweetDTO.builder()
                .tweetText("TEST TEXT")
                .tweetTitle("TITLE TEST")
                .userDTO(userDTO).build();

        //when
        Long tweetId = tweetService.create(tweetDTO);

        //then
        assertThat(tweetId).isNotNull();
    }



}
