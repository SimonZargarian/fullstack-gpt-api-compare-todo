package com.kokab.nrsedevgptapiordercompare.open_ai;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    private static final String API_KEY = "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";
    private static final Logger logger = LoggerFactory.getLogger(OpenAIService.class);


    public String compareOrders(String currentOrderDetails, String previousOrderDetails) {
        // Prepare the messages for the comparison task
        JSONArray messages = new JSONArray();

        // System message to set the role of the AI
        messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));

        String taskMessage = String.format("Compare the differences between the current-order and the previous-order, showcase discrepancies in the values of ID, UserId, Title, Is Completed \n" +
                        "\n" +
                        "Output: don't write comments, analysis or conclusion, just the detailed json object and arrays of all discrepancies. If there are no discrepancies, give no values. \n\nCurrent-order:\n%s\nPrevious-order:\n%s",
                currentOrderDetails, previousOrderDetails + "JSon structure:\n" +
                        "\n" +
                        "       \"discrepancies\": [\n" +
                        "       {\n" +
                        "       \"id\": {\n" +
                        "       \"previous\": ,\n" +
                        "       \"current\": \n" +
                        "       },\n" +
                        "       \"user_id\": {\n" +
                        "       \"previous\": ,\n" +
                        "       \"current\": \n" +
                        "       },\n" +
                        "       \"title\": {\n" +
                        "       \"previous\": ,\n" +
                        "       \"current\": \n" +
                        "       },\n" +
                        "       \"is_completed\": {\n" +
                        "       \"previous\":,\n" +
                        "       \"current\": \n" +
                        "       }\n" +
                        "       },\n" +
                        "       }\n");
        messages.put(new JSONObject().put("role", "user").put("content", taskMessage));


        // User message with the task
       /* String taskMessage = String.format("Please compare the following two orders and identify " +
                "any discrepancies in item userid, id titles, or is completed: true or false.\n\nCurrent order:\n%s\nPrevious order:\n%s",
                currentOrderDetails, previousOrderDetails);
        messages.put(new JSONObject().put("role", "user").put("content", taskMessage));*/

        logger.info("Task message: {}", taskMessage);
        try {
            HttpResponse<String> response = Unirest.post("https://api.openai.com/v1/chat/completions")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .body(new JSONObject()
                            .put("model", "gpt-3.5-turbo")
                            .put("messages", messages)
                            // .put("max_tokens", 150)
                            //.put("temperature", 0.7)
                            .toString())
                    .asString();

            logger.info("Response status: {}", response.getStatus());
            logger.info("Response body: {}", response.getBody());
            //logger.info("Response body: {}", response.getHeaders());

            JSONObject jsonResponse = new JSONObject(response.getBody());

            // Log the entire JSON response
            logger.info("OpenAI response: {}", jsonResponse.toString(4)); // Pretty print the JSON

            JSONArray choices = jsonResponse.getJSONArray("choices");
           /* if (!choices.isEmpty()) {
                // Extracting the messages array from the first choice
                JSONArray responseMessages = choices.getJSONObject(0).getJSONArray("messages");
                if (!messages.isEmpty()) {
                    // Assuming the last message in the array is the AI's response
                    JSONObject lastMessage = responseMessages.getJSONObject(responseMessages.length() - 1);
                    // Check if the last message's role is 'assistant' to ensure it's the AI's response
                    if ("assistant" .equals(lastMessage.getString("role"))) {
                        String messageContent = lastMessage.getString("content");

                        // Extracting and logging the specific answer/message
                        logger.info("OpenAI answer: {}", messageContent);

                        return messageContent;
                    }
                }
            }*/
            // Assuming the rest of your try block is correct and jsonResponse has been populated
         /*   if (!choices.isEmpty()) {
                JSONArray responseMessages = choices.getJSONObject(0).getJSONArray("messages");
                if (responseMessages != null && responseMessages.length() > 0) {
                    // In the given response structure, 'messages' is not an array directly under 'choices'.
                    // The correct structure has 'message' as an object within each 'choice'.
                    // So, we adjust the code to directly access 'message' object.

                    JSONObject lastMessage = choices.getJSONObject(0).getJSONObject("message");
                    if ("assistant".equals(lastMessage.getString("role"))) {
                        String messageContent = lastMessage.getString("content");
                        logger.info("OpenAI answer: {}", messageContent);
                        return messageContent;
                    }
                }
            }*/

            if (jsonResponse.has("choices") && !jsonResponse.getJSONArray("choices").isEmpty()) {
                JSONObject firstChoice = jsonResponse.getJSONArray("choices").getJSONObject(0);

                if (firstChoice.has("message")) {
                    JSONObject message = firstChoice.getJSONObject("message");
                    if ("assistant".equals(message.getString("role"))) {
                        String messageContent = message.getString("content");
                        logger.info("OpenAI assistant message: {}", messageContent);
                        return messageContent; // Return the assistant's message content directly
                    }
                }
            }

            return "No response from GPT-3.5-turbo.";
        } catch (UnirestException e) {
            e.printStackTrace();
            return "Error calling OpenAI API";
        } // Missing closing brace for try block added here
    } // Missing closing brace for method added here
}
