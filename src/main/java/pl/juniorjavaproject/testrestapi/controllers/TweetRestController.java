package pl.juniorjavaproject.testrestapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.juniorjavaproject.testrestapi.dto.TweetDTO;
import pl.juniorjavaproject.testrestapi.exceptions.IdsAreNotTheSameException;
import pl.juniorjavaproject.testrestapi.services.TweetService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tweets")
public class TweetRestController {

    private final TweetService tweetService;

    @GetMapping
    public ResponseEntity getAll() {
        List<TweetDTO> tweetDTOList = tweetService.getAll();
        if (!tweetDTOList.isEmpty()) {
            return ResponseEntity.ok(tweetDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity saveTweet(@Valid @RequestBody TweetDTO tweetDTO) {
        return ResponseEntity.created(URI.create("/api/tweets/" + tweetService.saveTweet(tweetDTO))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable long id) {
        TweetDTO tweetDTO = tweetService.findTweetById(id);
        if (tweetDTO != null) {
            return ResponseEntity.ok(tweetDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public void editTweet(@PathVariable long id, @Valid @RequestBody TweetDTO tweetDTO) {
        if (tweetDTO.getId() != id) {
            throw new IdsAreNotTheSameException("ID przekazanego elementu i ID z uri nie są takie same");
        }
        tweetService.editTweet(tweetDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTweet(@PathVariable long id) {
        tweetService.deleteTweet(id);
    }
}
