package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Person;

public interface IAuthorRepository extends JpaRepository<Person, Long>{

}
