package com.kokab.nrsedevgptapiordercompare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscrepancyDTO {

    private IdDTO id;
    private UserIdDTO user_id;
    private TitleDTO title;
    private IsCompletedDTO is_completed;
}
