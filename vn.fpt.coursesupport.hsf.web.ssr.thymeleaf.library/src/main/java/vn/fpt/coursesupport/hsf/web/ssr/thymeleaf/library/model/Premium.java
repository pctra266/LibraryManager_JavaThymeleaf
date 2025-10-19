package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "premiums")
public class Premium extends Membership {
	
	public static final int code = 2;
	
	public Premium() {}
	
	public Premium(LocalDate creationDate, LocalDate expiredDate) {
		super(creationDate, expiredDate);
	}
	
    @Override
    public int getNbLoans() {
        return 50;
    }

	@Override
	public long getPrice() {
		return 200;
	}

	@Override
	public Membership upgrade() {
		return null;
	}

	@Override
	public final int getCode() {
		return code;
	}
	
	
}
