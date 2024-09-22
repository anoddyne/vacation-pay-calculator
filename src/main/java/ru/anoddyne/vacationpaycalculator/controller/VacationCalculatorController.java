package ru.anoddyne.vacationpaycalculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anoddyne.vacationpaycalculator.model.VacationRequest;
import ru.anoddyne.vacationpaycalculator.service.VacationCalculatorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
public class VacationCalculatorController {
    private final VacationCalculatorService calculatorService;

    public VacationCalculatorController(VacationCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> calculateVacationPay(@RequestParam double averageSalary,
                                                  @RequestParam int vacationDays,
                                                  @RequestParam(required = false) String startDate) {
        if (averageSalary <= 0 || vacationDays <= 0) {
            return ResponseEntity.badRequest().body("Ошибка при вводе средней зарплаты или отпускных дней.");
        }

        LocalDate parsedStartDate = null;
        if (startDate != null && !startDate.isEmpty()) {
            try {
                parsedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest().body("Некорректный формат даты или введена неверная дата. Используйте формат YYYY-MM-DD.");
            }
        }

        VacationRequest request = new VacationRequest(averageSalary, vacationDays, parsedStartDate);
        double result = calculatorService.calculateVacationPay(request);
        return ResponseEntity.ok(String.format("Сумма отпускных: %.2f", result));
    }

}
