package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import org.junit.jupiter.api.Test;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Book;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Person;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDTOTest {
    @Test
    public void testCreateDTOFromEntity_ShouldMapBasicFields() {
        // Arrange
        Person author = new Person();
        author.setId(1L);
        author.setName("Nguyen Nhat Anh");
        author.setDob(LocalDate.of(1955, 5, 7));
        author.setNationality("Vietnam");

        Book b1 = new Book("Cho toi xin mot ve di tuoi tho", 5);
        b1.setId(100L);

        Book b2 = new Book("Mat biec", 3);
        b2.setId(200L);

        // Nếu Person có getWrittenBooks()
        author.getWrittenBooks().addAll(Arrays.asList(b1, b2));

        // Act
        AuthorDTO dto = AuthorDTO.createDTOfromEntity(author);

        // Assert
        assertEquals(1L, dto.getId());
        assertEquals("Nguyen Nhat Anh", dto.getName());
        assertEquals("1955-05-07", dto.getDob());
        assertEquals("Vietnam", dto.getNationality());

        assertEquals(2, dto.getBookIdList().size());
        assertTrue(dto.getBookIdList().containsAll(Arrays.asList(100L, 200L)));
        assertEquals(
                Arrays.asList("Cho toi xin mot ve di tuoi tho", "Mat biec"),
                dto.getBookNameList()
        );
    }

    @Test
    public void testAddBookId_ShouldNotAddDuplicate() {
        AuthorDTO dto = new AuthorDTO();
        dto.addBookId(1L);
        dto.addBookId(1L); // duplicate

        assertEquals(1, dto.getBookIdList().size(), "Duplicate ID should not be added twice");
    }

    @Test
    public void testAddBookName_ShouldAlwaysAdd() {
        AuthorDTO dto = new AuthorDTO();
        dto.addBookName("Book A");
        dto.addBookName("Book A"); // duplicate allowed

        assertEquals(2, dto.getBookNameList().size());
    }

    @Test
    public void testDefaultConstructor_ShouldInitializeLists() {
        AuthorDTO dto = new AuthorDTO();
        assertNotNull(dto.getBookIdList());
        assertNotNull(dto.getBookNameList());
        assertTrue(dto.getBookIdList().isEmpty());
        assertTrue(dto.getBookNameList().isEmpty());
    }
}