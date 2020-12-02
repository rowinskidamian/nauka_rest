package pl.juniorjavaproject.testrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.juniorjavaproject.testrestapi.services.TweetManagerService;
import pl.juniorjavaproject.testrestapi.services.TweetService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// testing only web layer wo Spring Context
// web layer for particular controller

@WebMvcTest(TweetRestController.class)
public class TweetRestControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TweetService tweetService;
    @MockBean private TweetManagerService tweetManagerService;

    @Test
    void shouldReturnNotFoundStatusWhenListIsEmpty() throws Exception {

        this.mockMvc.perform(get("/api/tweets"))
                .andExpect(status().isNotFound());
    }
}
