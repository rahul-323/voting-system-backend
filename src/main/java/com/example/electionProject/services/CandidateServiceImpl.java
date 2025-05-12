package com.example.electionProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.electionProject.entites.Candidate;
import com.example.electionProject.repositories.CandidateRepository;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository repository;

	@Override
	public Candidate createCandidate(Candidate candidate) {
		return repository.save(candidate);
	}

	@Override
	public Candidate getCandidateById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Candidate> getAllCandidates() {
		return repository.findAll();
	}

	@Override
	public Candidate updateCandidate(Long id, Candidate candidate) {
		candidate.setCandidateID(id);
		return repository.save(candidate);
	}

	@Override
	public void deleteCandidate(Long id) {
		repository.deleteById(id);
	}
}
