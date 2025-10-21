package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import org.junit.jupiter.api.Test;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Membership;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {
    // ----- Helper class -----
    static class DummyMembership extends Membership {
        private final int code;
        private final int nbLoans;
        private final long price;

        public DummyMembership(int code, int nbLoans, long price) {
            super(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));
            this.code = code;
            this.nbLoans = nbLoans;
            this.price = price;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public int getNbLoans() {
            return nbLoans;
        }

        @Override
        public long getPrice() {
            return price;
        }

        @Override
        public Membership upgrade() {
            return new DummyMembership(code + 1, nbLoans + 1, price + 100);
        }
    }

    // ----- Tests -----

    @Test
    void testGettersAndSetters() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("Alice");
        dto.setDob("1999-01-01");
        dto.setNationality("Vietnam");
        dto.setBalance(50000L);
        dto.setBanned(true);
        dto.setMembership(2);

        assertEquals(1L, dto.getId());
        assertEquals("Alice", dto.getName());
        assertEquals("1999-01-01", dto.getDob());
        assertEquals("Vietnam", dto.getNationality());
        assertEquals(50000L, dto.getBalance());
        assertTrue(dto.isBanned());
        assertEquals(2, dto.getMembership());
    }

    @Test
    void testCreateDTOfromEntity() {
        // Arrange
        DummyMembership membership = new DummyMembership(3, 10, 100000);
        User user = new User();
        user.setId(100L);
        user.setName("Bob");
        user.setDob(LocalDate.of(1990, 4, 15));
        user.setNationality("USA");
        user.setBalance(120000L);
        user.setBanned(false);
        user.setMembership(membership);

        // Act
        UserDTO dto = UserDTO.createDTOfromEntity(user);

        // Assert
        assertEquals(100L, dto.getId());
        assertEquals("Bob", dto.getName());
        assertEquals("1990-04-15", dto.getDob());
        assertEquals("USA", dto.getNationality());
        assertEquals(120000L, dto.getBalance());
        assertFalse(dto.isBanned());
        assertEquals(3, dto.getMembership());
    }

    @Test
    void testCreateDTOfromEntity_withBannedUser() {
        DummyMembership membership = new DummyMembership(1, 5, 50000);
        User user = new User();
        user.setId(200L);
        user.setName("Charlie");
        user.setDob(LocalDate.of(1985, 12, 5));
        user.setNationality("UK");
        user.setBalance(0L);
        user.setBanned(true);
        user.setMembership(membership);

        UserDTO dto = UserDTO.createDTOfromEntity(user);

        assertEquals(200L, dto.getId());
        assertEquals("Charlie", dto.getName());
        assertEquals("1985-12-05", dto.getDob());
        assertEquals("UK", dto.getNationality());
        assertEquals(0L, dto.getBalance());
        assertTrue(dto.isBanned());
        assertEquals(1, dto.getMembership());
    }
}