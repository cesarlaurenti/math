package com.tenpo.math;

import com.tenpo.math.user.*;
import com.tenpo.math.util.JSONUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    public void should_return_201_when_every_param_is_present() throws Exception {
        mockMvc.perform(post("/signup")
                .content(JSONUtils.asJsonString(new SignupUserRequest("user","pass",new ArrayList<Role>(), true)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    public void should_return_409_when_username_param_is_duplicated() throws Exception {
        AppUser appUser = new AppUser("user", "pass", new ArrayList(),true);
        mockMvc.perform(post("/signup")
                    .content(JSONUtils.asJsonString(appUser))
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        mockMvc.perform(post("/signup")
                .content(JSONUtils.asJsonString(appUser))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());

    }

    @Test
    public void should_return_400_when_username_param_is_not_present() throws Exception {
        mockMvc.perform(post("/signup")
                .content(JSONUtils.asJsonString(new SignupUserRequest(null,"pass",new ArrayList<Role>(), true)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_400_when_password_param_is_not_present() throws Exception {
        mockMvc.perform(post("/signup")
                .content(JSONUtils.asJsonString(new SignupUserRequest("user",null, new ArrayList<Role>(), true)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_400_when_roles_param_is_not_present() throws Exception {
        mockMvc.perform(post("/signup")
                .content(JSONUtils.asJsonString(new SignupUserRequest("user","pass", null, true)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_405_when_http_method_is_not_post() throws Exception {
        mockMvc.perform(get("/signup")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(put("/signup")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(delete("/signup")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(head("/signup")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(patch("/signup")).andExpect(status().isMethodNotAllowed());
    }
}
