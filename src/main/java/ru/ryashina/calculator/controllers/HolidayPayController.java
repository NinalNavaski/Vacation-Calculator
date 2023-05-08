package ru.ryashina.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ryashina.calculator.service.HolidayPayService;

@Controller
public class HolidayPayController {
	
	@Autowired
	private HolidayPayService holidayPayService;
	
	@GetMapping(value = "/calculaсte", params = {"salary", "days"})
	public String calculateWithTwoParam(@RequestParam(value = "salary") Double salary, 
			                @RequestParam(value = "days") Integer days, Model model) {
		
		model.addAttribute("message", holidayPayService.calculateHolidayPayAmountOfDays(salary, days));
		
		return "calculate";
	}
}
