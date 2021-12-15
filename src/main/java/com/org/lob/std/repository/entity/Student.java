package com.org.lob.std.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="STUDENT")
public class Student extends Auditable{

	@Id
	@Column(name = "STUDENTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "MARKS")
	private Long marks;
	@Column(name = "ROLLNO")
	private Long rollNo;
	@Column(name = "SUBJECT")
	private String subject;
	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMarks() {
		return marks;
	}

	public void setMarks(Long marks) {
		this.marks = marks;
	}

	public Long getRollNo() {
		return rollNo;
	}

	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
