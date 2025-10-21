package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import org.junit.jupiter.api.Test;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Manager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ManagerDTOTest {
    @Test
    void testNoArgsConstructorAndSetters() {
        ManagerDTO dto = new ManagerDTO();
        dto.setId(1L);
        dto.setName("Alice");
        dto.setDob("1990-05-10");

        assertEquals(1L, dto.getId());
        assertEquals("Alice", dto.getName());
        assertEquals("1990-05-10", dto.getDob());
    }

    @Test
    void testAllArgsConstructor_withDob() {
        LocalDate dob = LocalDate.of(1985, 12, 25);
        ManagerDTO dto = new ManagerDTO(2L, "Bob", dob);

        assertEquals(2L, dto.getId());
        assertEquals("Bob", dto.getName());
        assertEquals("1985-12-25", dto.getDob());
    }

    @Test
    void testAllArgsConstructor_withoutDob() {
        ManagerDTO dto = new ManagerDTO(3L, "Charlie", null);

        assertEquals(3L, dto.getId());
        assertEquals("Charlie", dto.getName());
        assertNull(dto.getDob(), "DOB should remain null when input dob == null");
    }

    @Test
    void testCreateDTOfromEntity() {
        // Arrange
        Manager manager = new Manager();
        manager.setId(10L);
        manager.setName("David");
        manager.setDob(LocalDate.of(1992, 3, 15));

        // Act
        ManagerDTO dto = ManagerDTO.createDTOfromEntity(manager);

        // Assert
        assertEquals(10L, dto.getId());
        assertEquals("David", dto.getName());
        assertEquals("1992-03-15", dto.getDob());
    }

    @Test
    void testCreateDTOfromEntity_withNullDob() {
        Manager manager = new Manager();
        manager.setId(11L);
        manager.setName("Emma");
        manager.setDob(null);

        ManagerDTO dto = ManagerDTO.createDTOfromEntity(manager);

        assertEquals(11L, dto.getId());
        assertEquals("Emma", dto.getName());
        assertNull(dto.getDob());
    }
}