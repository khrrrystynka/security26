package chnu.edu.security26;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class AccessTests
 * @since 18.10.2026 - 22.51
 */


import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    @Test
    @WithAnonymousUser
    public void whenAnonymThenStatusUnauthorized() throws Exception {

        ResultActions result = mockMvc.perform(get("/api/v1/items"));

        var status = result.andReturn().getResponse().getStatus();
        var message = result.andReturn().getResponse().getErrorMessage();
        assertEquals(401, status);
        assertNotNull(message);
        assertEquals("Unauthorized", message);

    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatusOk() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/admin"));
        var status = result.andReturn().getResponse().getStatus();
        var content = result.andReturn().getResponse().getContentAsString();
        assertEquals(200, status);
        assertEquals("Hello Admin!", content);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatus403() throws Exception {
        ResultActions resultActions =   mockMvc.perform(get("/api/v1/items/hello/user"));
        //      .andExpect(status().isForbidden());
        var status = resultActions.andReturn().getResponse().getStatus();
        assertEquals(403, status);
        var message = resultActions.andReturn().getResponse().getErrorMessage();
        assertEquals("Forbidden", message);
    }


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAccessUserThenStatusOk() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/user"));
        var status = result.andReturn().getResponse().getStatus();
        var content = result.andReturn().getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals("Hello!, user", content);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAccessAdminThenStatus403() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/admin"));
        var status = result.andReturn().getResponse().getStatus();

        assertEquals(403, status);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void whenAdminAccessManThenStatusOk() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/man"));
        var status = result.andReturn().getResponse().getStatus();
        var content = result.andReturn().getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals("Hello, man!", content);
    }

    @Test
    @WithMockUser(username = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperadminAccessManThenStatusOk() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/man"));
        var status = result.andReturn().getResponse().getStatus();
        var content = result.andReturn().getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals("Hello, man!", content);
    }

    @Test
    @WithMockUser(username = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperadminAccessAdminThenStatus403() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/admin"));
        var status = result.andReturn().getResponse().getStatus();

        assertEquals(403, status);
    }

    @Test
    @WithAnonymousUser
    void whenAnonymousAccessUserThenStatus401() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/user"));
        var status = result.andReturn().getResponse().getStatus();

        assertEquals(401, status);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAccessUnknownThenStatusOk() throws Exception {
        var result = mockMvc.perform(get("/api/v1/items/hello/unknown"));
        var status = result.andReturn().getResponse().getStatus();
        var content = result.andReturn().getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals("Hello Unknown!", content);
    }



}