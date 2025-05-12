package com.example.electionProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.electionProject.entites.Election;
import com.example.electionProject.services.ElectionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/elections")
@CrossOrigin(origins = "*")
public class ElectionController {

	@Autowired
	private ElectionService electionService;

	@PostMapping
	public Election createElection(@RequestBody Election election) {
		return electionService.createElection(election);
	}

	@GetMapping("/{id}")
	public Election getElectionById(@PathVariable Long id) {
		return electionService.getElectionById(id);
	}

	@GetMapping
	public List<Election> getAllElections() {
		return electionService.getAllElections();
	}

	@PutMapping("/{id}")
	public Election updateElection(@PathVariable Long id, @RequestBody Election election) {
		return electionService.updateElection(id, election);
	}

	@DeleteMapping("/{id}")
	public void deleteElection(@PathVariable Long id) {
		electionService.deleteElection(id);
	}

	@GetMapping("/status/{status}")
	public List<Election> getElectionsByStatus(@PathVariable String status) {
		return electionService.getElectionsByStatus(status);
	}

	@GetMapping("/stats")
	public Map<String, Long> getElectionStats() {
		return electionService.getElectionStats();
	}
}
