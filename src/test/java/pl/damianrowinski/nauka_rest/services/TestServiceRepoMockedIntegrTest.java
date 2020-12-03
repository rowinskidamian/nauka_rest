package pl.damianrowinski.nauka_rest.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.damianrowinski.nauka_rest.domain.dto.UserDTO;
import pl.damianrowinski.nauka_rest.domain.model.Tweet;
import pl.damianrowinski.nauka_rest.domain.model.User;
import pl.damianrowinski.nauka_rest.domain.repositories.TweetRepository;
import pl.damianrowinski.nauka_rest.domain.repositories.UserRepository;
import pl.damianrowinski.nauka_rest.mapper.TweetMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestServiceRepoMockedIntegrTest {

    @MockBean
    private TweetRepository tweetRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TweetMapper tweetMapper;

    @Autowired
    private TweetService tweetService;

    @Test
    void shouldReturnListOfTweetsDTO() {
        //given
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(1L);

        User user1 = new User();
        user1.setId(userDTO1.getId());

        pl.damianrowinski.nauka_rest.domain.dto.TweetDTO tweetDTO1 = pl.damianrowinski.nauka_rest.domain.dto.TweetDTO.builder()
                .id(1L)
                .userDTO(userDTO1)
                .tweetText("Tweet text 1")
                .tweetTitle("Tweet title 1")
                .build();

        Tweet tweet1 = tweetMapper.from(tweetDTO1);

        pl.damianrowinski.nauka_rest.domain.dto.TweetDTO tweetDTO2 = pl.damianrowinski.nauka_rest.domain.dto.TweetDTO.builder()
                .id(2L)
                .userDTO(userDTO1)
                .tweetText("tweet text 2222")
                .tweetTitle("tweet title 222")
                .build();

        Tweet tweet2 = tweetMapper.from(tweetDTO2);

        List<pl.damianrowinski.nauka_rest.domain.dto.TweetDTO> listOf2Tweets = List.of(tweetDTO1, tweetDTO2);

        //when
        when(userRepository.findById(userDTO1.getId())).thenReturn(Optional.of(user1));
        when(tweetRepository.findAll()).thenReturn(List.of(tweet1, tweet2));

        List<pl.damianrowinski.nauka_rest.domain.dto.TweetDTO> listOfReturnedTweets = tweetService.list();

        //then
        assertThat(listOfReturnedTweets).isEqualTo(listOf2Tweets);
    }

}
