package pl.juniorjavaproject.testrestapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.juniorjavaproject.testrestapi.services.TweetService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tweets")
public class TweetRestController {

    private final TweetService tweetService;
}
