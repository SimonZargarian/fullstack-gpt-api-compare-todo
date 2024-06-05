package com.kokab.nrsedevgptapiordercompare.service;

import com.kokab.nrsedevgptapiordercompare.model.entity.Discrepancy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DiscrepancyService {
    @Transactional
    void saveDiscrepancies(String jsonResponse);

    List<Discrepancy> getAllMembersNearYou();
}
