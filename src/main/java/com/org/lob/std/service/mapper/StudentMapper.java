package com.org.lob.std.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.org.lob.std.repository.entity.Student;
import com.org.lob.std.service.model.StudentModel;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	Student toEntity(StudentModel source);

	StudentModel toModel(Student student);

	List<StudentModel> toModel(Page<Student> page);
}
