package com.kokab.nrsedevgptapiordercompare.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Discrepancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private int previousId;
    private int currentId;

    private int previousUserId;
    private int currentUserId;

    private String previousTitle;
    private String currentTitle;

    private boolean previousIsCompleted;
    private boolean currentIsCompleted;

    // Additional setters if not covered by Lombok @Data
}
