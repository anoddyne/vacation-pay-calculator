package ru.anoddyne.vacationpaycalculator.model;

import java.time.LocalDate;

public class VacationRequest {
    private double averageSalary;
    private int vacationDays;
    private LocalDate startDate;

    public VacationRequest(double averageSalary, int vacationDays, LocalDate startDate) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.startDate = startDate;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
