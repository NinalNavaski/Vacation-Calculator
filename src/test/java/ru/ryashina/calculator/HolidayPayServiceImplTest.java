package ru.ryashina.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ryashina.calculator.service.HolidayPayServiceImpl;

@SpringBootTest
public class HolidayPayServiceImplTest {

    HolidayPayServiceImpl holidayPayService = new HolidayPayServiceImpl();

    @Test
    void testCalculateHolidayPayAmountOfDays() {
    	assertEquals("41569,97", holidayPayService.calculateHolidayPayAmountOfDays(1200000.00, 14));
        assertEquals("26723,55", holidayPayService.calculateHolidayPayAmountOfDays(1080000.00, 10));
        assertEquals("14549,49", holidayPayService.calculateHolidayPayAmountOfDays(840000.00, 7));
    }
}
