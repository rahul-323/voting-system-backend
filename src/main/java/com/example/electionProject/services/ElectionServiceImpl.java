package com.example.electionProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.electionProject.entites.Election;
import com.example.electionProject.repositories.ElectionRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    private ElectionRepository repository;

    @Override
    public Election createElection(Election election) {
        return repository.save(election);
    }

    @Override
    public Election getElectionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Election> getAllElections() {
        return repository.findAll();
    }

    @Override
    public Election updateElection(Long id, Election election) {
        election.setElectionID(id);
        return repository.save(election);
    }

    @Override
    public void deleteElection(Long id) {
        repository.deleteById(id);
    }
    @Override
    public List<Election> getElectionsByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public Map<String, Long> getElectionStats() {
        return repository.countElectionsByStatus().stream()
            .collect(Collectors.toMap(
                arr -> (String) arr[0],
                arr -> (Long) arr[1]
            ));
    }
}
