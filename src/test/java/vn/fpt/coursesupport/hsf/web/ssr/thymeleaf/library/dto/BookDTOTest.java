package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import org.junit.jupiter.api.Test;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Book;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Person;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookDTOTest {

    @Test
    public void testCreateDTOFromEntity_ShouldMapAllFields() {
        // Arrange
        Book book = new Book("Dac nhan tam", 10);
        book.setId(99L);
        book.setPublisher("NXB Tre");
        book.setPublishDate(LocalDate.of(2010, 1, 1));

        Person p1 = new Person();
        p1.setId(1L);
        p1.setName("Dale Carnegie");

        Person p2 = new Person();
        p2.setId(2L);
        p2.setName("Nguyen Nhat Anh");

        // Thêm tác giả
        book.getAuthors().addAll(Arrays.asList(p1, p2));

        // Act
        BookDTO dto = BookDTO.createDTOfromEntity(book);

        // Assert
        assertEquals(99L, dto.getId());
        assertEquals("Dac nhan tam", dto.getName());
        assertEquals("2010-01-01", dto.getPublishDate());
        assertEquals("NXB Tre", dto.getPublisher());
        assertEquals(10, dto.getAvailable());

        assertEquals(2, dto.getAuthorIdList().size());
        assertTrue(dto.getAuthorIdList().containsAll(Arrays.asList(1L, 2L)));

        assertEquals(
                Arrays.asList("Dale Carnegie", "Nguyen Nhat Anh"),
                dto.getAuthorNameList()
        );
    }

    @Test
    public void testAddAuthorId_ShouldAllowDuplicates() {
        BookDTO dto = new BookDTO();
        dto.addAuthorId(1L);
        dto.addAuthorId(1L); // duplicates allowed
        assertEquals(2, dto.getAuthorIdList().size());
    }

    @Test
    public void testAddAuthorName_ShouldAlwaysAdd() {
        BookDTO dto = new BookDTO();
        dto.addAuthorName("Author A");
        dto.addAuthorName("Author A");
        assertEquals(2, dto.getAuthorNameList().size());
    }

    @Test
    public void testDefaultConstructor_ShouldInitializeLists() {
        BookDTO dto = new BookDTO();
        assertNotNull(dto.getAuthorIdList());
        assertNotNull(dto.getAuthorNameList());
        assertTrue(dto.getAuthorIdList().isEmpty());
        assertTrue(dto.getAuthorNameList().isEmpty());
    }
}