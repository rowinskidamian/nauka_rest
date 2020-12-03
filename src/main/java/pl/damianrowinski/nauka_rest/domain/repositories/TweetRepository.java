package pl.damianrowinski.nauka_rest.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damianrowinski.nauka_rest.domain.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Tweet findTweetById(long id);
}
