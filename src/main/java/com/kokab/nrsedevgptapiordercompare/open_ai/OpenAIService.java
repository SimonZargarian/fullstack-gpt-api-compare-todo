package com.kokab.nrsedevgptapiordercompare.open_ai;

public interface OpenAIService {
    String compareOrders(String currentOrderDetails, String previousOrderDetails);
}
