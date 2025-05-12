package com.example.electionProject.repositories;

import com.example.electionProject.entites.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    List<Election> findByStatus(String status);
    
    @Query("SELECT e.status, COUNT(e) FROM Election e GROUP BY e.status")
    List<Object[]> countElectionsByStatus();
}