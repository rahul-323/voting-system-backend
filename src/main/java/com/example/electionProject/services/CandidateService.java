package com.example.electionProject.services;

import java.util.List;

import com.example.electionProject.entites.Candidate;

public interface CandidateService {
    Candidate createCandidate(Candidate candidate);
    Candidate getCandidateById(Long id);
    List<Candidate> getAllCandidates();
    Candidate updateCandidate(Long id, Candidate candidate);
    void deleteCandidate(Long id);
}

