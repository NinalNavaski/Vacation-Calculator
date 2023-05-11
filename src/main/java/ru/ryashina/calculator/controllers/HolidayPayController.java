package ru.ryashina.calculator.controllers;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.ryashina.calculator.service.HolidayPayService;

@RestController
@Validated
public class HolidayPayController {
	
	@Autowired
	private HolidayPayService holidayPayService;
	
	@GetMapping(value = "/calculaсte", params = {"salary", "days"})
	public String calculateWithTwoParam(@RequestParam(value = "salary")
	                                    @Min(0) Double salary,
										@RequestParam(value = "days") @Min(0) Integer days) {

		return holidayPayService.calculateHolidayPayAmountOfDays(salary, days);
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

}
