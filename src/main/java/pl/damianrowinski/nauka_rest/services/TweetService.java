package pl.damianrowinski.nauka_rest.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.damianrowinski.nauka_rest.domain.dto.TweetDTO;
import pl.damianrowinski.nauka_rest.domain.dto.UserDTO;
import pl.damianrowinski.nauka_rest.domain.model.User;
import pl.damianrowinski.nauka_rest.exceptions.ElementNotFoundException;
import pl.damianrowinski.nauka_rest.exceptions.UserIdNotPresentException;
import pl.damianrowinski.nauka_rest.mapper.TweetMapper;
import pl.damianrowinski.nauka_rest.domain.model.Tweet;
import pl.damianrowinski.nauka_rest.domain.repositories.TweetRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final TweetMapper tweetMapper;

    public TweetService(TweetRepository tweetRepository, UserService userService, ModelMapper modelMapper,
                        TweetMapper tweetMapper) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.tweetMapper = tweetMapper;
    }

    public List<TweetDTO> list() {
        List<Tweet> tweetList = tweetRepository.findAll();
        List<TweetDTO> tweetDTOSList = new ArrayList<>();
        if (!tweetList.isEmpty()) {
            for (Tweet tweet : tweetList) {
                UserDTO userDTO = modelMapper.map(tweet.getUser(), UserDTO.class);
                TweetDTO tweetDTO = modelMapper.map(tweet, TweetDTO.class);
                tweetDTO.setUserDTO(userDTO);
                tweetDTOSList.add(tweetDTO);
            }
        }
        return tweetDTOSList;
    }

    public Long create(TweetDTO tweetDTO) throws UserIdNotPresentException {
        if (tweetDTO.getUserDTO() == null || tweetDTO.getUserDTO().getId() == null)
            throw new UserIdNotPresentException("Brak podanego id użytkownika.");
        Long id = tweetDTO.getUserDTO().getId();
        Tweet tweet = modelMapper.map(tweetDTO, Tweet.class);
        Optional<User> optionalUser = userService.findUserById(id);
        Tweet savedTweet = tweetRepository.save(tweet);
        return savedTweet.getId();
    }

    public TweetDTO read(long id) throws ElementNotFoundException {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        Tweet tweet = tweetOptional.orElseThrow(() -> new ElementNotFoundException("Nie ma elementu o podanym ID."));
        TweetDTO tweetDTO = modelMapper.map(tweet, TweetDTO.class);
        UserDTO userDTO = modelMapper.map(tweet.getUser(), UserDTO.class);
        tweetDTO.setUserDTO(userDTO);
        return tweetDTO;
    }

    public TweetDTO update(Long id, TweetDTO tweetDTO) throws ElementNotFoundException {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        tweetOptional.orElseThrow(() -> new ElementNotFoundException("Nie ma elementu o podanym ID."));
        Tweet tweet = tweetMapper.from(tweetDTO);
        Tweet savedTweet = tweetRepository.save(tweet);
        return tweetMapper.from(savedTweet);
    }

    public void delete(Long id) throws ElementNotFoundException {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        Tweet tweet = tweetOptional.orElseThrow(
                () -> new ElementNotFoundException("Nie ma elementu o podanym ID."));
        tweetRepository.delete(tweet);
    }
}
