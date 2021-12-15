package com.org.lob.std.repository.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "COURSE")
public class Course extends Auditable {
	@Id
	@Column(name = "COURSE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_Id;
	@Column(name = "COURSEDETAILS")
	private String courseDetails;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENTID", referencedColumnName = "COURSE_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "STUDENTID"))
	private List<Student> students;

	public Long getCourse_Id() {
		return course_Id;
	}

	public void setCourse_Id(Long course_Id) {
		this.course_Id = course_Id;
	}

	public String getCourseDetails() {
		return courseDetails;
	}

	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
}