package com.example.electionProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.electionProject.entites.Vote;
import com.example.electionProject.repositories.VoteRepository;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote createVote(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote getVoteById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAllVotes() {
        return repository.findAll();
    }

    @Override
    public void deleteVote(Long id) {
        repository.deleteById(id);
    }
}
