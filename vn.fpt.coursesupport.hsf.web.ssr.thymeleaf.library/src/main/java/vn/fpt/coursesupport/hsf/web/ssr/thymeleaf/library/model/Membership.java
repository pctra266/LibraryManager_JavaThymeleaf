package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Membership {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected LocalDate creationDate;
    protected LocalDate expiredDate;
   
    public Membership() {}
    
    public Membership(LocalDate creationDate, LocalDate expiredDate) {
    	this();
    	setCreationDate(creationDate);
    	setExpiredDate(expiredDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
    
    public boolean isValid() {
    	return LocalDate.now().isAfter(creationDate) && LocalDate.now().isBefore(expiredDate);
    }
    
    public abstract int getCode();
    public abstract int getNbLoans();
    public abstract long getPrice();
    public abstract Membership upgrade();
}
