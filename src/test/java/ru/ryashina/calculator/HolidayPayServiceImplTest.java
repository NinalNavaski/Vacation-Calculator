package ru.ryashina.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ryashina.calculator.service.HolidayPayServiceImpl;

import java.time.LocalDate;

@SpringBootTest
public class HolidayPayServiceImplTest {

    HolidayPayServiceImpl holidayPayService = new HolidayPayServiceImpl();

    @Test
    void testCalculateHolidayPayAmountOfDays() {
    	assertEquals("41569,97", holidayPayService.calculateHolidayPayAmountOfDays(1200000.00, 14));
        assertEquals("26723,55", holidayPayService.calculateHolidayPayAmountOfDays(1080000.00, 10));
        assertEquals("14549,49", holidayPayService.calculateHolidayPayAmountOfDays(840000.00, 7));
    }

    @Test
    void testCalculateHolidayPayPeriod() {
        assertEquals("41569,97", holidayPayService.calculateHolidayPayPeriod(1200000.00,
                LocalDate.of(2023, 05, 29), LocalDate.of(2023, 06, 11)));
        assertEquals("26723,55", holidayPayService.calculateHolidayPayPeriod(1080000.00,
                LocalDate.of(2023, 06, 12), LocalDate.of(2023, 06, 22)));
        assertEquals("14549,49", holidayPayService.calculateHolidayPayPeriod(840000.00,
                LocalDate.of(2023, 01, 05), LocalDate.of(2023, 01, 15)));
    }

}
