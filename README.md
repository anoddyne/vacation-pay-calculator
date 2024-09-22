# Калькулятор отпускных (Vacation Pay Calculator)

Этот проект представляет собой микросервис на Spring Boot для расчета отпускных выплат. 

## API

### Расчет отпускных

```
GET http://localhost:8080/calculate?averageSalary={averageSalary}&vacationDays={vacationDays}&startDate={startDate}
```

Параметры:
- `averageSalary` (обязательный): Средняя зарплата за 12 месяцев (число > 0)
- `vacationDays` (обязательный): Количество дней отпуска (целое число > 0)
- `startDate` (опциональный): Дата начала отпуска в формате YYYY-MM-DD

Пример запроса:
```
GET http://localhost:8080/calculate?averageSalary=30000&vacationDays=14&startDate=2024-09-01
```

Используемые технологии: Java 11, Spring Boot 2.7.14
