package com.org.lob.std.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.org.lob.std.exception.ApplicationException;
import com.org.lob.std.repository.StudentRepository;
import com.org.lob.std.repository.entity.Student;
import com.org.lob.std.service.mapper.StudentMapper;
import com.org.lob.std.service.model.StudentModel;
import com.org.lob.std.service.model.StudentSearchRequest;
import com.org.lob.std.service.specification.StudentSpecification;
import com.org.lob.std.service.validation.CommunicationValidator;

@Service
public class DefaultStudentService implements StudentService {

	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;
	private static final Logger LOGGER = LoggerFactory.getLogger(CommunicationValidator.class);

	public DefaultStudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
	}

	@Override
	public StudentModel getStudent(Long studentId) {
		LOGGER.info("Generated query from studentRepository.findByStudentId {}"+ studentRepository.findByStudentIdIn(Arrays.asList(1L,2L)));
		LOGGER.info("Total Number of student from student table {}"+ studentRepository.getStudentCount().size());
		LOGGER.info("Total Number of student from student table using JPQL {}"+ studentRepository.getStudentDetails(studentId, null).size());
		Student student = getOrThrow(studentId);
		return studentMapper.toModel(student);
	}

	@Override
	public StudentModel createStudent(StudentModel studentModel) {
		LOGGER.info("Generated query from  studentRepository.findByStudentId {}"+ studentRepository.findByStudentIdAndRollNo(studentModel.getStudentId(), studentModel.getRollNo()));
		Student createStudent = studentMapper.toEntity(studentModel);
		Student saveStudent = studentRepository.save(createStudent);
		return studentMapper.toModel(saveStudent);
	}

	@Override
	public Page<StudentModel> search(StudentSearchRequest studentSearchRequest, PageRequest pageRequest) {
		Page<Student> page = studentRepository.findAll(new StudentSpecification(studentSearchRequest), pageRequest);
		List<StudentModel> pageContent = studentMapper.toModel(page);
		return new PageImpl<>(pageContent, pageRequest, page.getTotalElements());
	}

	@Override
	public StudentModel updateStudent(StudentModel studentModel) {
		Student student = getOrThrow(studentModel.getStudentId());

		if (StringUtils.hasText(studentModel.getName())) {
			student.setName(studentModel.getName());
		}
		if (StringUtils.hasText(Long.toString(studentModel.getMarks()))) {
			student.setMarks(studentModel.getMarks());
		}
		if (StringUtils.hasText(Long.toString(studentModel.getRollNo()))) {
			student.setRollNo(studentModel.getRollNo());
		}
		if (StringUtils.hasText(studentModel.getSubject())) {
			student.setSubject(studentModel.getSubject());
		}

		Student saveStudent = studentRepository.save(student);
		return studentMapper.toModel(saveStudent);
	}

	private Student getOrThrow(Long studentId) {
		Optional<Student> studentModel = studentRepository.findById(studentId);
		if (studentModel.isEmpty()) {
			throw ApplicationException.invalidRecord("Invalid Record, Please provide Valid one");
		}
		return studentModel.get();
	}

	@Override
	public void deleteStudent(Long studentId) {
		studentRepository.deleteById(studentId);
	}
}
