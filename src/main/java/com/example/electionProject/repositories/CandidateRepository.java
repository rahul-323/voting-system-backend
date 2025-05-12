package com.example.electionProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electionProject.entites.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}

