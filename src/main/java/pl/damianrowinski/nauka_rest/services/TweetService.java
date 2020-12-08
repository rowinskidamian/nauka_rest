package pl.damianrowinski.nauka_rest.services;

import org.springframework.stereotype.Service;
import pl.damianrowinski.nauka_rest.domain.dto.TweetDTO;
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
    private final TweetMapper tweetMapper;

    public TweetService(TweetRepository tweetRepository, UserService userService, TweetMapper tweetMapper) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
        this.tweetMapper = tweetMapper;
    }

    public List<TweetDTO> list() {
        List<Tweet> tweetList = tweetRepository.findAll();
        List<TweetDTO> tweetDTOSList = new ArrayList<>();
        if (!tweetList.isEmpty()) {
            for (Tweet tweet : tweetList) {
                TweetDTO tweetDTO = tweetMapper.from(tweet);
                tweetDTOSList.add(tweetDTO);
            }
        }
        return tweetDTOSList;
    }

    public Long create(TweetDTO tweetDTO) throws UserIdNotPresentException, ElementNotFoundException {
        if (tweetDTO.getUser() == null || tweetDTO.getUser().getId() == null)
            throw new UserIdNotPresentException("Brak podanego id użytkownika.");
        Long id = tweetDTO.getUser().getId();
        Tweet tweet = tweetMapper.from(tweetDTO);
        userService.findUserById(id);
        Tweet savedTweet = tweetRepository.save(tweet);
        return savedTweet.getId();
    }

    public TweetDTO read(long id) throws ElementNotFoundException {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        Tweet tweet = tweetOptional.orElseThrow(() -> new ElementNotFoundException("Nie ma elementu o podanym ID."));
        return tweetMapper.from(tweet);
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
