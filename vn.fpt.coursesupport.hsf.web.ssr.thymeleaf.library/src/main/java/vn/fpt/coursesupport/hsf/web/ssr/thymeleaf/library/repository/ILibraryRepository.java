package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Library;
import java.util.List;


public interface ILibraryRepository extends JpaRepository<Library, Long> {
	
	List<Library> findByName(String name);
}
