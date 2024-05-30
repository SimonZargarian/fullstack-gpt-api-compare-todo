package com.kokab.nrsedevgptapiordercompare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsCompletedDTO {
    private boolean previous;
    private boolean current;
}
