package com.example.electionProject.entites;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Election {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long electionID;

	@NotBlank
	private String title;

	@NotBlank
	private String startDate;

	@NotBlank
	private String endDate;

	private String status;

	@PrePersist
	@PreUpdate
	public void setStatus() {
		
		LocalDate today = LocalDate.now();
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);

		if (today.isBefore(start)) {
			this.status = "Upcoming";
		} else if (today.isAfter(end)) {
			this.status = "Completed";
		} else {
			this.status = "Active";
		}
	}
}
