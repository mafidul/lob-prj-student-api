package com.org.lob.std.service.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.org.lob.std.service.validation.ValidCommunication;
import com.org.lob.std.service.validation.ValidEmailId;
import com.org.lob.std.service.validation.ValidName;

@ValidEmailId.List({ @ValidEmailId(field = "name", fieldMatch = "subject"),
		@ValidEmailId(field = "password", fieldMatch = "confirmPassword", message = "Password doesn't match please check your password") })
public class StudentModel {

	private Long studentId;
	@ValidName
	private String name;
	@Positive
	private Long marks;
	@Positive
	private Long rollNo;
	private String subject;

	private String password;

	private String confirmPassword;

	@NotEmpty(message = "Communication type is required")
	@ValidCommunication(values = { "email1", "phoneno1", "email2", "phoneno2" })
	private String communicationType;

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

	public String getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "StudentModel [studentId=" + studentId + ", name=" + name + ", marks=" + marks + ", rollNo=" + rollNo
				+ ", subject=" + subject + ", communicationType=" + communicationType + "]";
	}
}
