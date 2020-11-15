package pl.juniorjavaproject.testrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.juniorjavaproject.testrestapi.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Tweet findTweetById(long id);
}
