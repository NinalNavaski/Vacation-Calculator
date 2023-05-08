package ru.ryashina.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class HolidayPayServiceImpl implements HolidayPayService {

	@Override
	public String calculateHolidayPayAmountOfDays(Double salary, Integer days) {
		
		double holydayPay = (salary / (12 * 29.3)) * days;
		double tax = holydayPay * 13 / 100; // НДФЛ 13%
		double result = holydayPay - tax;
		
		return String.format("%.2f", result);
	}
	

}
