package com.org.lob.std.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.lob.std.repository.entity.Student;
import com.org.lob.std.repository.support.StudentRecord;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

	List<Student> findByStudentIdIn(Collection<Long> StudentId);

	List<Student> findByStudentIdAndRollNo(Long StudentId, Long rollNo);

	@Query(value = "SELECT count(*) AS cnt, s.subject AS subject, s.marks AS marks from STUDENT.student s where s.studentid > 50\r\n"
			+ "and s.marks > 91\r\n" + "GROUP BY s.subject, s.marks ;", nativeQuery = true)
	List<StudentRecord> getStudentCount();

	@Query("SELECT COUNT(*) from STUDENT student where student.studentId = :studentId and student.marks = :marks ")
	List<StudentRecord> getStudentDetails(@Param("studentId") Long studentId, @Param("marks") String marks);
}