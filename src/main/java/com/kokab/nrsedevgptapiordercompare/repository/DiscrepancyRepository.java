package com.kokab.nrsedevgptapiordercompare.repository;

import com.kokab.nrsedevgptapiordercompare.model.entity.Discrepancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscrepancyRepository extends JpaRepository<Discrepancy, Long> {

    @Query("SELECT m FROM Discrepancy m ORDER BY m.id DESC")
    List<Discrepancy> findAllOrderByDesc();

}
