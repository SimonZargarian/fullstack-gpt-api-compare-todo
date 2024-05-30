package com.kokab.nrsedevgptapiordercompare.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscrepanciesResponse {
    private List<DiscrepancyDTO> discrepancies;
}