package com.spring.orm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {
	
	private HibernateTemplate hibernateTemplate;
	
	//save student method
	@Transactional
	public int insert(Student student) {
		//insert
		Integer i=(Integer)this.hibernateTemplate.save(student);
		return i;
		
	}
	//get the single data(object)
	public Student getStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class,studentId);
		return student;
		
	}
	
	//get all student(all rows)
	public List<Student>getAllstudent()
	{
		List<Student> students = this.hibernateTemplate.loadAll(Student.class);
		return students;
	}
	@Transactional
	//delete the data
	public void deleteStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class,studentId);
		this.hibernateTemplate.delete(student);
		
	}
	@Transactional
	 //update
	public void updateStudent(Student student)
	{
		this.hibernateTemplate.update(student);
	}
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	

}
