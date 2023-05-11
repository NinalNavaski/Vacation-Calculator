package ru.ryashina.calculator.service;


import java.time.LocalDate;

public interface HolidayPayService {
	
	String calculateHolidayPayAmountOfDays(Double salary, Integer days);

	String calculateHolidayPayPeriod(Double salary, LocalDate dayOn, LocalDate dayOff);
	
}
