package ru.ryashina.calculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HolidayPayServiceImpl implements HolidayPayService {

	@Override
	public String calculateHolidayPayAmountOfDays(Double salary, Integer days) {
	
			double holidayPay = (salary / (12 * 29.3)) * days;
			double tax = holidayPay * 13 / 100; // НДФЛ 13%
			double result = holidayPay - tax;
		
		return String.format("%.2f", result);
	}

	@Override
	public String calculateHolidayPayPeriod(Double salary, LocalDate dayOn, LocalDate dayOff) {

		//Список дат отпуска
		List<LocalDate> duration = dayOn.datesUntil(dayOff.plusDays(1)).collect(Collectors.toList());

		//количество календарных дней отпуска, включая дату окончания отпуска
		long amountOfDays = ChronoUnit.DAYS.between(dayOn, dayOff) + 1;

		//празники 2023 году
		List<LocalDate> holidays = List.of(
				LocalDate.of(2023, 1, 1),
				LocalDate.of(2023, 1, 2),
				LocalDate.of(2023, 1, 3),
				LocalDate.of(2023, 1, 4),
				LocalDate.of(2023, 1, 5),
				LocalDate.of(2023, 1, 6),
				LocalDate.of(2023, 1, 7),
				LocalDate.of(2023, 1, 8),
				LocalDate.of(2023, 2, 23),
				LocalDate.of(2023, 3, 8),
				LocalDate.of(2023, 5, 1),
				LocalDate.of(2023, 5, 9),
				LocalDate.of(2023, 6, 12),
				LocalDate.of(2023, 11, 4));

		//количество праздников, пришедшихся на отпуск
		int count = Stream.concat(duration.stream(), holidays.stream()).collect(Collectors.toList()).size() -
				Stream.concat(duration.stream(), holidays.stream()).collect(Collectors.toSet()).size();

		//количество календарных дней отпуска за вычетом праздников,
		//согласно ст.120 ТК РФ отпуск считается в календарных днях,
		//оплата начисляется за все дни, за исключением официальных праздников,перечисленных в списке holidays
		int days = (int) amountOfDays - count;

		double holidayPay = (salary / (12 * 29.3)) * days;
		double tax = holidayPay * 13 / 100; // НДФЛ 13%
		double result = holidayPay - tax;

		return String.format("%.2f", result);
	}
}
