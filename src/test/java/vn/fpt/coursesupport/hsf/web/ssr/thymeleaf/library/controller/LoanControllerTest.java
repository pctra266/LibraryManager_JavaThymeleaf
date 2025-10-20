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

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.LoanDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@WebMvcTest(LoanController.class)
@MockBean(LibraryService.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testGetAllLoans() throws Exception {
        given(libraryService.getAllLoans()).willReturn(new ArrayList<>());
        given(libraryService.getAllUsers()).willReturn(new ArrayList<>());
        given(libraryService.getAllBooks()).willReturn(new ArrayList<>());

        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(view().name("loans"))
                .andExpect(model().attributeExists("loans"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("newLoan", instanceOf(LoanDTO.class)));
    }

    @Test
    public void testSaveLoan() throws Exception {
        mockMvc.perform(post("/loans")
                        .param("userId", "1")
                        .param("bookId", "2")
                        .param("dueDate", "2025-12-01")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loans"));

        verify(libraryService, times(1)).saveLoan(any(LoanDTO.class));
    }

    @Test
    public void testReturnBook() throws Exception {
        long loanId = 25L;

        mockMvc.perform(post("/loans/return/" + loanId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loans"));

        verify(libraryService, times(1)).returnBook(loanId);
    }
}