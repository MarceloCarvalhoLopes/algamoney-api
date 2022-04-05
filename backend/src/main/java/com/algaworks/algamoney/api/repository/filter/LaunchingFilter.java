package com.algaworks.algamoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LaunchingFilter {

	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateOf;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateUntil;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDateOf() {
		return dueDateOf;
	}

	public void setDueDateOf(LocalDate dueDateOf) {
		this.dueDateOf = dueDateOf;
	}

	public LocalDate getDueDateUntil() {
		return dueDateUntil;
	}

	public void setDueDateUntil(LocalDate dueDateUntil) {
		this.dueDateUntil = dueDateUntil;
	}
	

	
}
