package com.example.electionProject.services;

import java.util.List;

import com.example.electionProject.entites.Vote;

public interface VoteService {
    Vote createVote(Vote vote);
    Vote getVoteById(Long id);
    List<Vote> getAllVotes();
    void deleteVote(Long id);
}

