package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import java.time.LocalDate;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Manager;

public class ManagerDTO {
	
    private Long id;
    private String name;
    private String dob;
    
    public ManagerDTO() {}
    
    public ManagerDTO(long id, String name, LocalDate dob) {
    	this();
    	setId(id);
    	setName(name);
    	if (dob!=null) setDob(dob.toString());
	}

    public static ManagerDTO createDTOfromEntity(Manager manager) {
		return new ManagerDTO(manager.getId(), manager.getName(), manager.getDob());
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
    
}
