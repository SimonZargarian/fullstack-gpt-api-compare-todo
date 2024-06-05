package com.kokab.nrsedevgptapiordercompare.service;

import com.kokab.nrsedevgptapiordercompare.model.OrderDetails;

public interface OrderService {
    String orderDetailsToJsonFormat(OrderDetails orderDetails);

    OrderDetails fetchCurrentOrder(String orderId);

    OrderDetails fetchPreviousSuccessfulOrder(String orderId);
}
