package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.ManagerDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@WebMvcTest(ManagerController.class)
@MockBean(LibraryService.class)
public class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testLogin() throws Exception {
        Long managerId = 1L;
        ManagerDTO fakeManager = new ManagerDTO();
        fakeManager.setId(managerId);
        fakeManager.setName("Test Manager");

        given(libraryService.login(managerId)).willReturn(fakeManager);

        mockMvc.perform(get("/managers/" + managerId))
                .andExpect(status().isOk())
                .andExpect(view().name("manager"))
                .andExpect(model().attributeExists("manager"))
                .andExpect(model().attribute("manager", fakeManager));
    }
}