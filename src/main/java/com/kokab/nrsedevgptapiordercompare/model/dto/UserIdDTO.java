package com.kokab.nrsedevgptapiordercompare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdDTO {
    private int previous;
    private int current;
    // Getters and Setters
}
