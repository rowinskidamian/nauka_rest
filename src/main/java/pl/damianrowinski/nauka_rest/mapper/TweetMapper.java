package pl.damianrowinski.nauka_rest.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.damianrowinski.nauka_rest.domain.dto.TweetDTO;
import pl.damianrowinski.nauka_rest.domain.dto.UserDTO;
import pl.damianrowinski.nauka_rest.domain.model.Tweet;
import pl.damianrowinski.nauka_rest.domain.model.User;

@Component
@Slf4j
public class TweetMapper {

    public TweetDTO from(Tweet tweet) {
        log.info("from({})", tweet);
        ModelMapper modelMapper = new ModelMapper();
        TweetDTO tweetDTO = modelMapper.map(tweet, TweetDTO.class);
        User userToMap = tweet.getUser();
        UserDTO userDTO = modelMapper.map(userToMap, UserDTO.class);
        tweetDTO.setUser(userDTO);
        log.info("from({}) = {}", tweet, tweetDTO);
        return tweetDTO;
    }

    public Tweet from(TweetDTO tweetDTO) {
        log.info("from({})", tweetDTO);
        ModelMapper modelMapper = new ModelMapper();
        Tweet tweet = modelMapper.map(tweetDTO, Tweet.class);
        log.info("from({}) = {}", tweetDTO, tweet);
        return tweet;
    }
}
