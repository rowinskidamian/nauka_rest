package pl.juniorjavaproject.testrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.juniorjavaproject.testrestapi.services.TweetManagerService;
import pl.juniorjavaproject.testrestapi.services.TweetService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//starting whole Spring context wo server, only web layer

@SpringBootTest
@AutoConfigureMockMvc
class ContextRestControllerMockMvcTest {

    @Autowired private MockMvc mockMvc;

    @Test
    void shouldReturnNotFoundStatusWhenListIsEmpty() throws Exception {

        this.mockMvc.perform(get("/api/tweets"))
                .andExpect(status().isNotFound());
    }


}