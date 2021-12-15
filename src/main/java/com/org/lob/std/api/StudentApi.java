package com.org.lob.std.api;

import static com.org.lob.support.Constants.STUDENT_REQUEST_MAPPING;
import static com.org.lob.support.Constants.STUDENT_REQUEST_PAGENUMBER;
import static com.org.lob.support.Constants.STUDENT_REQUEST_PAGESIZE;
import static com.org.lob.support.Constants.STUDENT_REQUEST_SORTBY;
import static com.org.lob.support.Constants.STUDENT_REQUEST_SORTDIRECTION;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.org.lob.std.service.StudentService;
import com.org.lob.std.service.model.StudentModel;
import com.org.lob.std.service.model.StudentSearchRequest;

@RestController
@RequestMapping(STUDENT_REQUEST_MAPPING)
public class StudentApi {

	private static final String SORT_DIRECTION_ASC = "asc";
	private final StudentService studentService;

	public StudentApi(StudentService studentService) {
		this.studentService = studentService;
	}

	
	@GetMapping(path = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<StudentModel> getStudent(@PathVariable Long studentId) {
		return ResponseEntity.ok(studentService.getStudent(studentId));
	}

	@GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Page<StudentModel>> search(
			@RequestParam(name = STUDENT_REQUEST_PAGENUMBER, required = true) @Positive Integer pageNumber,
			@RequestParam(name = STUDENT_REQUEST_PAGESIZE, required = true)  @Positive Integer pageSize,
			@RequestParam(name = STUDENT_REQUEST_SORTBY, required = false) String sortBy,
			@RequestParam(name = STUDENT_REQUEST_SORTDIRECTION, required = false) String sortDirection,
			@RequestParam Map<String, String> requestParam) {
		StudentSearchRequest searchRequest = new StudentSearchRequest(requestParam);
		PageRequest pageRequest = pageRequest(pageNumber, pageSize, sortBy, sortDirection);
		Page<StudentModel> page = studentService.search(searchRequest, pageRequest);
		return ResponseEntity.ok(page);	
	}

	private PageRequest pageRequest(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		if(StringUtils.hasText(sortBy)) {
			Direction direction = StringUtils.hasText(sortDirection) && SORT_DIRECTION_ASC.equalsIgnoreCase(sortDirection)
					? Direction.ASC
					: Direction.DESC;
			return PageRequest.of(pageNumber, pageSize, Sort.by(new Order(direction, sortBy)));
		}
		return PageRequest.of(pageNumber, pageSize);
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<StudentModel> createStudent( @Valid @RequestBody StudentModel studentModel, UriComponentsBuilder ucb) {
		StudentModel create = studentService.createStudent(studentModel);

		return ResponseEntity.created(ucb
									.path(STUDENT_REQUEST_MAPPING + "/{studentid}")
									.buildAndExpand(create.getStudentId())
									.toUri())
							  .body(create);
	}

	@PutMapping(path = "/{studentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<StudentModel> updateStudent(@PathVariable Long studentId, @Valid @RequestBody StudentModel studentModel) {
		studentModel.setStudentId(studentId);
		StudentModel update = studentService.updateStudent(studentModel);
		return ResponseEntity.ok(update);
	}

	@DeleteMapping(path = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.ok(null);
	}
}