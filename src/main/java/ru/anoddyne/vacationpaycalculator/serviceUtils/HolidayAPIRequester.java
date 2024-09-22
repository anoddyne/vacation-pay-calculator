package ru.anoddyne.vacationpaycalculator.serviceUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HolidayAPIRequester {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://isdayoff.ru/api/";

    public static ResponseEntity<String> sendRequestAndGetAnswer(String request) {
        try {
            String response = restTemplate.getForObject(API_URL + request, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при получении информации о праздниках.");
        }
    }
}
