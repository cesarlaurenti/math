package com.tenpo.math;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuditControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void should_return_200_when_no_page() throws Exception {
        mockMvc.perform(get("/audit"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_page() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page","0");
        queryParams.add("size","3");
        mockMvc.perform(get("/audit").queryParams(queryParams))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_405_when_http_method_is_not_post() throws Exception {
        mockMvc.perform(post("/audit")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(put("/audit")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(delete("/audit")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(patch("/audit")).andExpect(status().isMethodNotAllowed());
    }
}
