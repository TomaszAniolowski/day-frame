package com.arch.dayframe.model.bp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@Tag("BPSimpleTime")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BPSimpleTimeTest {

    @Test @Order(1)
    @DisplayName("01. new BPSimpleTime()")
    void testCreateDefaultBPSimpleTime() throws BreakPointException {
        Calendar currentTime = Calendar.getInstance();
        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = currentTime.get(Calendar.MINUTE);
        
        BPSimpleTime defaultSimpleTime = new BPSimpleTime();
        BPSimpleTime simpleTimeOfCurrentTime = new BPSimpleTime(currentHour, currentMinutes);
        
        assertNotNull(defaultSimpleTime);
        assertEquals(simpleTimeOfCurrentTime.getTime(), defaultSimpleTime.getTime());
    }

    @Test @Order(2)
    @DisplayName("02. new BPSimpleTime(9, 30)")
    void testCreateBPSimpleTimeWithPositiveHourAndPositiveMinute() throws BreakPointException {
        BPSimpleTime simpleTime = new BPSimpleTime(9, 30);

        assertNotNull(simpleTime);
        assertEquals("09:30", simpleTime.getTime());
    }

    @Test @Order(3)
    @DisplayName("03. new BPSimpleTime(-1, 30)")
    void testCreateBPSimpleTimeWithNegativeHourAndPositiveMinute() {
        Executable createSimpleTimeWithNegativeHour = () -> new BPSimpleTime(-1, 30);

        BreakPointException e = assertThrows(BreakPointException.class, createSimpleTimeWithNegativeHour);
        assertEquals(BreakPointException.ErrorCode.TIME_ERR, e.getErrorCode());
        assertEquals("Wrong break point time: [hour=-1]", e.getMessage());
    }

    @Test @Order(4)
    @DisplayName("04. new BPSimpleTime(9, -1)")
    void testCreateBPSimpleTimeWithPositiveHourAndNegativeMinute() {
        Executable createSimpleTimeWithNegativeMinute = () -> new BPSimpleTime(9, -1);

        BreakPointException e = assertThrows(BreakPointException.class, createSimpleTimeWithNegativeMinute);
        assertEquals(BreakPointException.ErrorCode.TIME_ERR, e.getErrorCode());
        assertEquals("Wrong break point time: [minute=-1]", e.getMessage());
    }

    @Test @Order(5)
    @DisplayName("05. new BPSimpleTime(-1, -1)")
    void testCreateBPSimpleTimeWithNegativeHourAndNegativeMinute() {
        Executable createSimpleTimeWithNegativeHour = () -> new BPSimpleTime(-1, -1);

        BreakPointException e = assertThrows(BreakPointException.class, createSimpleTimeWithNegativeHour);
        assertEquals(BreakPointException.ErrorCode.TIME_ERR, e.getErrorCode());
        assertEquals("Wrong break point time: [hour=-1]", e.getMessage());
    }

    @Test @Order(6)
    @DisplayName("06. new BPSimpleTime(24, 0)")
    void testCreateBPSimpleTimeWithTooHighHourAndCorrectMinute() {
        Executable createSimpleTimeWithTooHighHour = () -> new BPSimpleTime(24, 0);

        BreakPointException e = assertThrows(BreakPointException.class, createSimpleTimeWithTooHighHour);
        assertEquals(BreakPointException.ErrorCode.TIME_ERR, e.getErrorCode());
        assertEquals("Wrong break point time: [hour=24]", e.getMessage());
    }

    @Test @Order(7)
    @DisplayName("07. new BPSimpleTime(0, 60)")
    void testCreateBPSimpleTimeWithCorrectHourAndTooHighMinute() {
        Executable createSimpleTimeWithTooHighMinute = () -> new BPSimpleTime(0, 60);

        BreakPointException e = assertThrows(BreakPointException.class, createSimpleTimeWithTooHighMinute);
        assertEquals(BreakPointException.ErrorCode.TIME_ERR, e.getErrorCode());
        assertEquals("Wrong break point time: [minute=60]", e.getMessage());
    }

    @Test @Order(8)
    @DisplayName("08. new BPSimpleTime(24, 60)")
    void testCreateBPSimpleTimeWithTooHighHourAndTooHighMinute() {
        Executable createSimpleTimeWithTooHighMinute = () -> new BPSimpleTime(24, 60);

        BreakPointException e = assertThrows(BreakPointException.class, createSimpleTimeWithTooHighMinute);
        assertEquals(BreakPointException.ErrorCode.TIME_ERR, e.getErrorCode());
        assertEquals("Wrong break point time: [hour=24]", e.getMessage());
    }

    @Test @Order(9)
    @DisplayName("09. getTime(0, 0)")
    void testGetTimeOfSingleNumberHourAndSingleNumberMinute() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(0, 0);
        String time = simpleTime.getTime();

        assertEquals("00:00", time);
    }

    @Test @Order(10)
    @DisplayName("10. getTime(23, 0)")
    void testGetTimeOfDoubleNumberHourAndSingleNumberMinute() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(23, 0);
        String time = simpleTime.getTime();

        assertEquals("23:00", time);
    }

    @Test @Order(11)
    @DisplayName("11. getTime(0, 59)")
    void testGetTimeOfSingleNumberHourAndDoubleNumberMinute() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(0, 59);
        String time = simpleTime.getTime();

        assertEquals("00:59", time);
    }

    @Test @Order(12)
    @DisplayName("12. getTime(23, 59)")
    void testGetTimeOfDoubleNumberHourAndDoubleNumberMinute() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(23, 59);
        String time = simpleTime.getTime();

        assertEquals("23:59", time);
    }

    @Test @Order(13)
    @DisplayName("13. getHour()")
    void testGetHour() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(10, 30);
        int hour = simpleTime.getHour();

        assertEquals(10, hour);
    }

    @Test @Order(14)
    @DisplayName("14. getMinutes()")
    void testGetMinutes() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(10, 30);
        int hour = simpleTime.getMinutes();

        assertEquals(30, hour);
    }

    @Test @Order(15)
    @DisplayName("15. add() negative")
    void testAddNegativeMinutes() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        Executable addNegativeMinutes = () -> simpleTime.add(-1);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, addNegativeMinutes);
        assertEquals("Minutes parameter cannot be lower than 0", e.getMessage());

        String timeAfter = simpleTime.getTime();

        assertEquals(timeBefore, timeAfter);
    }

    @Test @Order(16)
    @DisplayName("16. add() positive, within the hour")
    void testAddMinutesWithinTheHour() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(1);
        String timeAfter = simpleTime.getTime();

        assertEquals("11:30", timeBefore);
        assertEquals("11:31", timeAfter);
    }

    @Test @Order(17)
    @DisplayName("17. add() positive, exceeding the hour, with a difference zero")
    void testAddMinutesExceedingTheHourDiffZero() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(30);
        String timeAfter = simpleTime.getTime();

        assertEquals("11:30", timeBefore);
        assertEquals("12:00", timeAfter);
    }

    @Test @Order(18)
    @DisplayName("18. add() positive, exceeding the hour, with a positive difference")
    void testAddMinutesExceedingTheHourDiffPositive() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(31);
        String timeAfter = simpleTime.getTime();

        assertEquals("11:30", timeBefore);
        assertEquals("12:01", timeAfter);
    }

    @Test @Order(19)
    @DisplayName("19. add() positive, exceeding the hour, with a difference exceeding a next hour")
    void testAddMinutesExceedingTheHourDiffExceedingNextHour() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(90);
        String timeAfter = simpleTime.getTime();

        assertEquals("11:30", timeBefore);
        assertEquals("13:00", timeAfter);
    }

    @Test @Order(20)
    @DisplayName("20. add() positive, exceeding the day, with a difference zero")
    void testAddMinutesExceedingTheDayDiffZero() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(12*60 + 30);
        String timeAfter = simpleTime.getTime();

        assertEquals("11:30", timeBefore);
        assertEquals("00:00", timeAfter);
    }

    @Test @Order(21)
    @DisplayName("21. add() positive, exceeding the day, with a positive difference")
    void testAddMinutesExceedingTheDayDiffPositive() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(12 * 60 + 31);
        String timeAfter = simpleTime.getTime();

        assertEquals("11:30", timeBefore);
        assertEquals("00:01", timeAfter);
    }

    @Test @Order(22)
    @DisplayName("22. add() positive, exceeding the day, with a difference exceeding a next day")
    void testAddMinutesExceedingTheDayDiffExceedingNextDay() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(12 * 60 + 24 * 60 + 30);
        String timeAfter = simpleTime.getTime();

        assertEquals("11:30", timeBefore);
        assertEquals("00:00", timeAfter);
    }

    @Test @Order(23)
    @DisplayName("23. add(Integer.MAX_VALUE) - performance test")
    void testAddMinutesPerformance() throws BreakPointException {
        SimpleTime simpleTime = new BPSimpleTime(11, 30);
        String timeBefore = simpleTime.getTime();

        simpleTime.add(Integer.MAX_VALUE);
        String timeAfter = simpleTime.getTime();

        // 2 147 483 647 min = 35 791 394 h 7 min = 1 491 308 d 2 h 7 min
        assertEquals("11:30", timeBefore);
        assertEquals("13:37", timeAfter);
    }

    @Test @Order(24)
    @DisplayName("24. compareTo() the same hour, the same minutes")
    void testCompareTheSameHourTheSameMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(10, 30);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(0, compareResult);
    }

    @Test @Order(25)
    @DisplayName("25. compareTo() the same hour, lower minutes")
    void testCompareTheSameHourLowerMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(10, 29);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(-1, compareResult);
    }

    @Test @Order(26)
    @DisplayName("26. compareTo() the same hour, higher minutes")
    void testCompareTheSameHourHigherMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(10, 31);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(1, compareResult);
    }

    @Test @Order(27)
    @DisplayName("27. compareTo() lower hour, the same minutes")
    void testCompareLowerHourTheSameMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(9, 30);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(-1, compareResult);
    }

    @Test @Order(28)
    @DisplayName("28. compareTo() lower hour, lower minutes")
    void testCompareLowerHourLowerMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(9, 29);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(-1, compareResult);
    }

    @Test @Order(29)
    @DisplayName("29. compareTo() lower hour, higher minutes")
    void testCompareLowerHourHigherMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(9, 31);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(-1, compareResult);
    }

    @Test @Order(30)
    @DisplayName("30. compareTo() higher hour, the same minutes")
    void testCompareHigherHourTheSameMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(11, 30);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(1, compareResult);
    }

    @Test @Order(31)
    @DisplayName("31. compareTo() higher hour, lower minutes")
    void testCompareHigherHourLowerMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(11, 29);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(1, compareResult);
    }

    @Test @Order(32)
    @DisplayName("32. compareTo() higher hour, higher minutes")
    void testCompareHigherHourHigherMinutes() throws BreakPointException {
        SimpleTime simpleTime1 = new BPSimpleTime(11, 31);
        SimpleTime simpleTime2 = new BPSimpleTime(10, 30);

        int compareResult = simpleTime1.compareTo(simpleTime2);
        assertEquals(1, compareResult);
    }
}