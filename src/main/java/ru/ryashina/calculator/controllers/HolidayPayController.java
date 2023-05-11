package ru.ryashina.calculator.controllers;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.ryashina.calculator.service.HolidayPayService;

import java.time.LocalDate;

@RestController
@Validated
public class HolidayPayController {
	
	@Autowired
	private HolidayPayService holidayPayService;
	
	@GetMapping(value = "/calculate", params = {"salary", "days"})
	public String calculateWithTwoParam(@RequestParam(value = "salary") @Min(0) Double salary,
										@RequestParam(value = "days") @Min(0) Integer days) {

		return holidayPayService.calculateHolidayPayAmountOfDays(salary, days);
	}

	@GetMapping(value = "/calculate", params = {"salary", "dayOn", "dayOff"})
	public String calculateWithThreeParam(@RequestParam(value = "salary", required = false) @Min(0) Double salary,
										  @RequestParam(value = "dayOn", required = false)
										  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dayOn,
										  @RequestParam(value = "dayOff", required = false)
										  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dayOff) {

		return holidayPayService.calculateHolidayPayPeriod(salary, dayOn, dayOff);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		
	    return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	  }

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleNumberFormatException(NumberFormatException e) {

		return new ResponseEntity<>("parameter must be a number: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConversionFailedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleNumberFormatException(ConversionFailedException e) {

		return new ResponseEntity<>("parameter must be a format: yyyy-mm-dd: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
