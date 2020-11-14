package pl.juniorjavaproject.testrestapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.juniorjavaproject.testrestapi.domain.repositories.TweetRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TweetService {

    private final TweetRepository tweetRepository;


}
