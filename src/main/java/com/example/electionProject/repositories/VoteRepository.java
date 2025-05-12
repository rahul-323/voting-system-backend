package com.example.electionProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electionProject.entites.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
