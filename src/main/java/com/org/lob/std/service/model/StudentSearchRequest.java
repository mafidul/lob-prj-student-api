package com.org.lob.std.service.model;

import java.util.Map;

import org.springframework.util.StringUtils;

public class StudentSearchRequest {

	private final Long studentId;
	private final String name;

	public StudentSearchRequest(Map<String, String> requestParam) {
		String idStr = requestParam.get("studentId");
		if (StringUtils.hasLength(idStr)) {
			this.studentId = Long.valueOf(idStr);
		} else {
			this.studentId = null;
		}
		this.name = requestParam.get("name");
	}

	public boolean studentIdFilteringRequired() {
		return studentId != null;
	}

	public boolean nameFilteringRequired() {
		return StringUtils.hasText(name);
	}

	public Long getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}
}