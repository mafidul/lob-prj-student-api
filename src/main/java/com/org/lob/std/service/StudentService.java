package com.org.lob.std.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.org.lob.std.service.model.StudentModel;
import com.org.lob.std.service.model.StudentSearchRequest;

public interface StudentService {

	StudentModel getStudent(Long studentid);

	StudentModel createStudent(StudentModel studentModel);

	StudentModel updateStudent(StudentModel studentModel);

	Page<StudentModel> search(StudentSearchRequest searchRequest, PageRequest pageRequest);

	void deleteStudent(Long studentid);
}
