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

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.BookDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@WebMvcTest(BookController.class)
@MockBean(LibraryService.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testGetAllBooks() throws Exception {
        given(libraryService.getAllAuthors()).willReturn(new ArrayList<>());
        given(libraryService.getAllBooks()).willReturn(new ArrayList<>());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("newBook", instanceOf(BookDTO.class)));
    }

    @Test
    public void testSaveBook() throws Exception {
        mockMvc.perform(post("/books")
                        .param("name", "Test Book")
                        .param("publishDate", "2025-01-01")
                        .param("publisher", "Test Publisher")
                        .param("available", "10")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        verify(libraryService, times(1)).saveBook(any(BookDTO.class));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long bookId = 42L;

        mockMvc.perform(post("/books/delete/" + bookId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        verify(libraryService, times(1)).removeBook(bookId);
    }
}