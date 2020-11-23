package pl.juniorjavaproject.testrestapi.mapper;

import org.junit.jupiter.api.Test;
import pl.juniorjavaproject.testrestapi.domain.dto.TweetDTO;
import pl.juniorjavaproject.testrestapi.domain.dto.UserDTO;
import pl.juniorjavaproject.testrestapi.domain.model.Tweet;
import pl.juniorjavaproject.testrestapi.domain.model.User;

import static org.assertj.core.api.Assertions.*;

class TweetMapperTest {

    @Test
    void givenTweetShouldReturnTweetDTOwithDTOfieldsMappedFromTweet() {
        //given
        long id = 1L;
        String firstName = "Damian";
        String lastName = "Rowiński";
        String textTweet = "test tweet text";
        String titleTweet = "test title from tweet";

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setId(id);

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setId(id);

        tweet.setTweetText(textTweet);
        tweet.setTweetTitle(titleTweet);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);

        TweetDTO expectedTweetDTO = new TweetDTO();
        expectedTweetDTO.setUserDTO(userDTO);
        expectedTweetDTO.setId(id);
        expectedTweetDTO.setTweetText(textTweet);
        expectedTweetDTO.setTweetTitle(titleTweet);

        TweetMapper tweetMapper = new TweetMapper();

        //when
        TweetDTO returnedTweetDTO = tweetMapper.from(tweet);

        //then
        assertThat(returnedTweetDTO).isEqualTo(expectedTweetDTO);
    }


}