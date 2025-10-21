package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import org.junit.jupiter.api.Test;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Book;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Loan;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanDTOTest {
    @Test
    void testNoArgsConstructorAndSetters() {
        LoanDTO dto = new LoanDTO();
        dto.setId(1L);
        dto.setUserId(2L);
        dto.setBookId(3L);
        dto.setUserName("Alice");
        dto.setBookName("Java Programming");
        dto.setCreationDate("2025-01-01");
        dto.setDueDate("2025-02-01");
        dto.setReturnDate("2025-02-15");

        assertEquals(1L, dto.getId());
        assertEquals(2L, dto.getUserId());
        assertEquals(3L, dto.getBookId());
        assertEquals("Alice", dto.getUserName());
        assertEquals("Java Programming", dto.getBookName());
        assertEquals("2025-01-01", dto.getCreationDate());
        assertEquals("2025-02-01", dto.getDueDate());
        assertEquals("2025-02-15", dto.getReturnDate());
    }

    @Test
    void testAllArgsConstructor() {
        LoanDTO dto = new LoanDTO(
                10L, 20L, 30L, "Bob", "Clean Code",
                "2025-03-01", "2025-03-10", "2025-03-15"
        );

        assertEquals(10L, dto.getId());
        assertEquals(20L, dto.getUserId());
        assertEquals(30L, dto.getBookId());
        assertEquals("Bob", dto.getUserName());
        assertEquals("Clean Code", dto.getBookName());
        assertEquals("2025-03-01", dto.getCreationDate());
        assertEquals("2025-03-10", dto.getDueDate());
        assertEquals("2025-03-15", dto.getReturnDate());
    }

    @Test
    void testCreateDTOfromEntity_withReturnedDate() {
        // Arrange: tạo dữ liệu giả cho Loan entity
        User user = new User();
        user.setId(100L);
        user.setName("Charlie");

        Book book = new Book("Design Patterns", 5);
        book.setId(200L);

        Loan loan = new Loan();
        loan.setId(300L);
        loan.setUser(user);
        loan.setBook(book);
        loan.setCreationDate(LocalDate.of(2025, 1, 10));
        loan.setDueDate(LocalDate.of(2025, 1, 20));
        loan.setReturnedDate(LocalDate.of(2025, 1, 18));

        // Act
        LoanDTO dto = LoanDTO.createDTOfromEntity(loan);

        // Assert
        assertEquals(300L, dto.getId());
        assertEquals(100L, dto.getUserId());
        assertEquals(200L, dto.getBookId());
        assertEquals("Charlie", dto.getUserName());
        assertEquals("Design Patterns", dto.getBookName());
        assertEquals("2025-01-10", dto.getCreationDate());
        assertEquals("2025-01-20", dto.getDueDate());
        assertEquals("2025-01-18", dto.getReturnDate());
    }

    @Test
    void testCreateDTOfromEntity_withoutReturnedDate() {
        User user = new User();
        user.setId(10L);
        user.setName("Daisy");

        Book book = new Book("Effective Java", 3);
        book.setId(20L);

        Loan loan = new Loan();
        loan.setId(30L);
        loan.setUser(user);
        loan.setBook(book);
        loan.setCreationDate(LocalDate.of(2025, 2, 1));
        loan.setDueDate(LocalDate.of(2025, 2, 10));
        loan.setReturnedDate(null); // chưa trả sách

        LoanDTO dto = LoanDTO.createDTOfromEntity(loan);

        assertEquals("", dto.getReturnDate()); // Khi null -> ""
    }
}