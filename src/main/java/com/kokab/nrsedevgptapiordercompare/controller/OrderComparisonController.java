package com.kokab.nrsedevgptapiordercompare.controller;


import com.kokab.nrsedevgptapiordercompare.model.OrderDetails;
import com.kokab.nrsedevgptapiordercompare.open_ai.OpenAIService;
import com.kokab.nrsedevgptapiordercompare.service.DiscrepancyService;
import com.kokab.nrsedevgptapiordercompare.service.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OrderComparisonController {

    @Autowired
    private OpenAIService openAIService;

    @Autowired
    private OrderServiceImp orderService; // Assuming this is the interface OrderServiceImp implements

    @Autowired
    private DiscrepancyService discrepancyService;

    @PostMapping("/verify-order/{currentOrderId}/{correctOrderId}")
    public String verifyOrder(@PathVariable String currentOrderId, @PathVariable String correctOrderId) {
        OrderDetails currentOrder = orderService.fetchCurrentOrder(currentOrderId);
        OrderDetails previousOrder = orderService.fetchPreviousSuccessfulOrder(correctOrderId);

        String currentOrderDetails = orderService.orderDetailsToJsonFormat(currentOrder);
        String previousOrderDetails = orderService.orderDetailsToJsonFormat(previousOrder);

        // Since compareOrders now uses messages for GPT-3.5-turbo, directly pass order details
        String comparisonResult = openAIService.compareOrders(currentOrderDetails, previousOrderDetails);

        // Call saveDiscrepancies to save the result into the database
        discrepancyService.saveDiscrepancies(comparisonResult);


        System.out.println("Comparing order:" + comparisonResult);

        // Instead of printing, return the result
        return comparisonResult;


    }
}
