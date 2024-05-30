package com.kokab.nrsedevgptapiordercompare.service;

import com.kokab.nrsedevgptapiordercompare.model.Item;
import com.kokab.nrsedevgptapiordercompare.model.OrderDetails;
import com.kokab.nrsedevgptapiordercompare.open_ai.OpenAIService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIService.class);

    public String orderDetailsToJsonFormat(OrderDetails orderDetails) {
        StringBuilder detailsBuilder = new StringBuilder();
        // detailsBuilder.append("[\n"); // Start of the JSON array

        int itemCount = orderDetails.getItems().size();
        int index = 0;

        for (Item item : orderDetails.getItems()) {
            detailsBuilder
                    .append("{\n") // Start of the JSON object
                    .append("\"user_id\": ").append(item.getUserId()).append(",\n")
                    .append("\"id\": ").append(item.getId()).append(",\n")
                    .append("\"title\": \"").append(item.getTitle()).append("\",\n")
                    .append("\"is_completed\": ").append(item.isCompleted()).append("\n")
                    .append("},").append("\n");


            index++;
            if (index < itemCount) {
                detailsBuilder.append(",\n");
            } else {
                detailsBuilder.append("\n");
            }
        }

        logger.info("JSON body:\n {}", detailsBuilder.toString());

        return detailsBuilder.toString();
    }

    public OrderDetails fetchCurrentOrder(String orderId) {
        String url = "https://jsonplaceholder.typicode.com/todos/" + orderId;
        HttpResponse<String> response = Unirest.get(url).asString();
        JSONObject jsonObject = new JSONObject(response.getBody());

        Item item = new Item(
                jsonObject.getInt("userId"),
                jsonObject.getInt("id"),
                jsonObject.getString("title"),
                jsonObject.getBoolean("completed")
        );


        return new OrderDetails(List.of(item));
    }

    public OrderDetails fetchPreviousSuccessfulOrder(String orderId) {
        String url = "https://jsonplaceholder.typicode.com/todos/" + orderId;
        HttpResponse<String> response = Unirest.get(url).asString();
        JSONObject jsonObject = new JSONObject(response.getBody());

        Item item = new Item(
                jsonObject.getInt("userId"),
                jsonObject.getInt("id"),
                jsonObject.getString("title"),
                jsonObject.getBoolean("completed")
        );

        return new OrderDetails(List.of(item));
    }
}
