package pl.damianrowinski.nauka_rest.mapper;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.damianrowinski.nauka_rest.domain.dto.UserDTO;
import pl.damianrowinski.nauka_rest.domain.model.Tweet;
import pl.damianrowinski.nauka_rest.domain.model.User;

@Component
public class TweetMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(TweetMapper.class);

    public pl.damianrowinski.nauka_rest.domain.dto.TweetDTO from(Tweet tweet) {
        LOGGER.info("from({})", tweet);
        ModelMapper modelMapper = new ModelMapper();
        pl.damianrowinski.nauka_rest.domain.dto.TweetDTO tweetDTO = modelMapper.map(tweet, pl.damianrowinski.nauka_rest.domain.dto.TweetDTO.class);
        User userToMap = tweet.getUser();
        UserDTO userDTO = modelMapper.map(userToMap, UserDTO.class);
        tweetDTO.setUser(userDTO);
        LOGGER.info("from({}) = {}", tweet, tweetDTO);
        return tweetDTO;
    }

    public Tweet from(pl.damianrowinski.nauka_rest.domain.dto.TweetDTO tweetDTO) {
        LOGGER.info("from({})", tweetDTO);
        ModelMapper modelMapper = new ModelMapper();
        Tweet tweet = modelMapper.map(tweetDTO, Tweet.class);
        LOGGER.info("from({}) = {}", tweetDTO, tweet);
        return tweet;
    }
}
