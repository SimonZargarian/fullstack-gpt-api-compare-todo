package com.kokab.nrsedevgptapiordercompare.controller;

import com.kokab.nrsedevgptapiordercompare.model.entity.Discrepancy;
import com.kokab.nrsedevgptapiordercompare.service.DiscrepancyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DiscrepancyController {

    @Autowired
    DiscrepancyServiceImpl discrepancyServiceImpl;

    @GetMapping(value = "/discrepancies")
    public List<Discrepancy>  getAllDiscrepancies(){

        return discrepancyServiceImpl.getAllMembersNearYou();
    }

}
