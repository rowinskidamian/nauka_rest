package pl.juniorjavaproject.testrestapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.juniorjavaproject.testrestapi.controllers.TweetRestController;
import pl.juniorjavaproject.testrestapi.services.TweetManagerService;
import pl.juniorjavaproject.testrestapi.services.TweetService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class TestRestApiApplicationTest {
    //przykład Smoke Test // sanity check // if the app is starting and doing basic functionalities

    @Autowired
    private TweetRestController tweetRestController;

    @Autowired
    private TweetManagerService tweetManagerService;

    @Autowired
    private TweetService tweetService;

    @Test
    void shouldLoadContextAndCreateControllersAndServices() throws Exception{
        assertAll(
                () -> assertThat(tweetRestController).isNotNull(),
                () -> assertThat(tweetManagerService).isNotNull(),
                () -> assertThat(tweetService).isNotNull()
        );

    }



}