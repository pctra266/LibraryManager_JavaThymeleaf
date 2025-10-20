package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.UserDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@WebMvcTest(UserController.class)
@MockBean(LibraryService.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testGetAllUsers() throws Exception {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setName("Alice");

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setName("Bob");

        List<UserDTO> fakeUserList = new ArrayList<>(List.of(user1, user2));

        given(libraryService.getAllUsers()).willReturn(fakeUserList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("newUser"))
                .andExpect(model().attribute("users", fakeUserList))
                .andExpect(model().attribute("newUser", instanceOf(UserDTO.class)));
    }

    @Test
    public void testSaveUser() throws Exception {
        mockMvc.perform(post("/users")
                        .param("name", "New User")
                        .param("dob", "2000-01-01")
                        .param("nationality", "Testland")
                        .param("balance", "100")
                        .param("membership", "0")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(libraryService, times(1)).saveUser(any(UserDTO.class));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long userIdToDelete = 123L;

        mockMvc.perform(post("/users/delete/" + userIdToDelete))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(libraryService, times(1)).removeUser(userIdToDelete);
    }
}