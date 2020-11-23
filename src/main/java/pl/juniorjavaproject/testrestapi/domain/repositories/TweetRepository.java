package pl.juniorjavaproject.testrestapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.juniorjavaproject.testrestapi.domain.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Tweet findTweetById(long id);
}
