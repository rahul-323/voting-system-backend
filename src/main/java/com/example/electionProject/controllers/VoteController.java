package com.example.electionProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.electionProject.entites.Vote;
import com.example.electionProject.services.VoteService;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@PostMapping
	public Vote createVote(@RequestBody Vote vote) {
		return voteService.createVote(vote);
	}

	@GetMapping("/{id}")
	public Vote getVoteById(@PathVariable Long id) {
		return voteService.getVoteById(id);
	}

	@GetMapping
	public List<Vote> getAllVotes() {
		return voteService.getAllVotes();
	}

	@DeleteMapping("/{id}")
	public void deleteVote(@PathVariable Long id) {
		voteService.deleteVote(id);
	}
}
