package com.example.electionProject.entites;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteID;

    private Long userID;
    private Long candidateID;
    private Long electionID;
    private String voteDate;
}

