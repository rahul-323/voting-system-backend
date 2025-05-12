package com.example.electionProject.services;


import java.util.List;
import java.util.Map;

import com.example.electionProject.entites.Election;

public interface ElectionService {
    Election createElection(Election election);
    Election getElectionById(Long id);
    List<Election> getAllElections();
    Election updateElection(Long id, Election election);
    void deleteElection(Long id);
    List<Election> getElectionsByStatus(String status);
    Map<String, Long> getElectionStats();
}
