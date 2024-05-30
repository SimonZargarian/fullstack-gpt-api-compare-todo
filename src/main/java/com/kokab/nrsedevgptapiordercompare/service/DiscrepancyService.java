package com.kokab.nrsedevgptapiordercompare.service;

import com.fasterxml.jackson.databind.ObjectMapper; // Make sure to import this
import com.kokab.nrsedevgptapiordercompare.mapper.DiscrepancyMapper;
import com.kokab.nrsedevgptapiordercompare.model.dto.DiscrepanciesResponse;
import com.kokab.nrsedevgptapiordercompare.model.entity.Discrepancy;
import com.kokab.nrsedevgptapiordercompare.repository.DiscrepancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiscrepancyService {

    @Autowired
    private DiscrepancyRepository discrepancyRepository;

    @Autowired
    private DiscrepancyMapper discrepancyMapper; // Autowired mapper

    @Transactional
    public void saveDiscrepancies(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String cleanedJsonResponse = cleanJsonResponse(jsonResponse);

            // Assuming DiscrepanciesResponse contains a List<DiscrepancyDTO>
            DiscrepanciesResponse response = mapper.readValue(cleanedJsonResponse, DiscrepanciesResponse.class);

            response.getDiscrepancies().forEach(discrepancyDTO -> {
                Discrepancy discrepancy = discrepancyMapper.discrepancyDTOToDiscrepancy(discrepancyDTO);
                discrepancyRepository.save(discrepancy);
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Consider logging this exception or handling it appropriately
        }
    }

    public List<Discrepancy> getAllMembersNearYou(){
        return discrepancyRepository.findAllOrderByDesc();
    }



    private String cleanJsonResponse(String jsonResponse) {
        String cleaned = jsonResponse.trim();
        if (cleaned.startsWith("```") && cleaned.endsWith("```")) {
            cleaned = cleaned.substring(3, cleaned.length() - 3).trim();
        }
        int jsonStartIndex = cleaned.indexOf('{');
        return jsonStartIndex > 0 ? cleaned.substring(jsonStartIndex) : cleaned;
    }
}
