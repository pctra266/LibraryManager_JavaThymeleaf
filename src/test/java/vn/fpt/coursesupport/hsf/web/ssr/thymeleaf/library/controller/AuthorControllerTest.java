package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.AuthorDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@WebMvcTest(AuthorController.class)
@MockBean(LibraryService.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testGetAllAuthors() throws Exception {
        given(libraryService.getAllAuthors()).willReturn(new ArrayList<>());
        given(libraryService.getAllBooks()).willReturn(new ArrayList<>());

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("newAuthor", instanceOf(AuthorDTO.class)));
    }

    @Test
    public void testSaveAuthor() throws Exception {
        mockMvc.perform(post("/authors")
                        .param("name", "Test Author")
                        .param("dob", "2000-01-01")
                        .param("nationality", "Vietnam")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors"));

        verify(libraryService, times(1)).saveAuthor(any(AuthorDTO.class));
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Long authorId = 42L;

        mockMvc.perform(post("/authors/delete/" + authorId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors"));

        verify(libraryService, times(1)).removeAuthor(authorId);
    }
}