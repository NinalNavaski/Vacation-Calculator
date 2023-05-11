#Vacation Calculator
Microservice on SpringBoot + Java 11 with one API: GET "/calculate"  
The application has two methods:
- __first:__  The application accepts the average salary for 12 months (param - "salary") and the number of vacation days (param - "days") and responds with the amount of vacation pay
```
http://localhost:8080/calculate?salary=1200000&days=14
```
- __second:__ The application accepts the average salary for 12 months (param = "salary"), vacation start date (param = "dayOn") and end date (param = "dayOff") and returns the amount of vacation pay. Holidays are included in the calculation  
Date format = "yyyy-mm-dd"
```
http://localhost:8080/calculate?salary=1200000&dayOn=2023-05-15&dayOff=2023-05-28
```

# Technologies
- Spring Boot 2.7.11
- Maven


