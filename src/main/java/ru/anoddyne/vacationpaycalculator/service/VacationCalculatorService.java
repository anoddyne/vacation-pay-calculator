package ru.anoddyne.vacationpaycalculator.service;

import org.springframework.stereotype.Service;
import ru.anoddyne.vacationpaycalculator.model.VacationRequest;
import ru.anoddyne.vacationpaycalculator.serviceUtils.HolidayAPIRequester;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class VacationCalculatorService {
    private static final double AVERAGE_DAYS_PER_MONTH = 29.3;
    private static final DateTimeFormatter API_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");


    private int countPaidDays(LocalDate startDate, int vacationDays) {
        int paidDays = 0;
        LocalDate endDate = startDate.plusDays(vacationDays - 1);
        String apiRequest = String.format("getdata?date1=%s&date2=%s",
                startDate.format(API_DATE_FORMAT),
                endDate.format(API_DATE_FORMAT));

        String apiResponse = String.valueOf(HolidayAPIRequester.sendRequestAndGetAnswer(apiRequest));


        if (apiResponse != null) {
            for (char dayType : apiResponse.toCharArray()) {
                if (dayType == '0') {
                    paidDays++;
                }
            }
        } else {
            paidDays = vacationDays;
        }
        return paidDays;
    }

    public double calculateVacationPay(VacationRequest request) {
        double averageDailySalary = request.getAverageSalary() / AVERAGE_DAYS_PER_MONTH;

        if (request.getStartDate() != null) {
            int paidDays = countPaidDays(request.getStartDate(), request.getVacationDays());
            return averageDailySalary * paidDays;
        } else {
            return averageDailySalary * request.getVacationDays();
        }
    }

}
