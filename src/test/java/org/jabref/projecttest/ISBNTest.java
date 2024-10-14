/* Copyright (C) 2024 Supitsara Sareecam - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package org.jabref.projecttest;

import org.jabref.model.entry.Month;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MonthTest {

    @Test
    void testValidFullMonthName() {
        // Input: Full month name "January"
        String input = "January";
        Optional<Month> result = Month.parse(input);

        // Expect January (1st month)
        assertTrue(result.isPresent());
        assertEquals(Month.JANUARY, result.get());
    }

    @Test
    void testValidShortMonthName() {
        // Input: Short month name "jan"
        String input = "jan";
        Optional<Month> result = Month.parse(input);

        // Expect January (1st month)
        assertTrue(result.isPresent());
        assertEquals(Month.JANUARY, result.get());
    }

    @Test
    void testValidNumericMonth() {
        // Input: Numeric month "1"
        String input = "1";
        Optional<Month> result = Month.parse(input);

        // Expect January (1st month)
        assertTrue(result.isPresent());
        assertEquals(Month.JANUARY, result.get());
    }

    @Test
    void testValidTwoDigitNumericMonth() {
        // Input: Numeric month "01"
        String input = "01";
        Optional<Month> result = Month.parse(input);

        // Expect January (1st month)
        assertTrue(result.isPresent());
        assertEquals(Month.JANUARY, result.get());
    }

    @Test
    void testInvalidMonthName() {
        // Input: Invalid month name "InvalidMonth"
        String input = "InvalidMonth";
        Optional<Month> result = Month.parse(input);

        // Expect empty Optional
        assertFalse(result.isPresent());
    }

    @Test
    void testValidGermanMonthName() {
        // Input: German month name "Januar"
        String input = "Januar";
        Optional<Month> result = Month.parse(input);

        // Expect January (1st month)
        assertTrue(result.isPresent());
        assertEquals(Month.JANUARY, result.get());
    }

    @Test
    void testValidGermanShortMonthName() {
        // Input: German short month name "Februar"
        String input = "Februar";
        Optional<Month> result = Month.parse(input);

        // Expect February (2nd month)
        assertTrue(result.isPresent());
        assertEquals(Month.FEBRUARY, result.get());
    }

    @Test
    void testInvalidNumericMonth() {
        // Input: Invalid numeric month "13"
        String input = "13";
        Optional<Month> result = Month.parse(input);

        // Expect empty Optional
        assertFalse(result.isPresent());
    }

    @Test
    void testZeroNumericMonth() {
        // Input: Invalid numeric month "00"
        String input = "00";
        Optional<Month> result = Month.parse(input);

        // Expect empty Optional
        assertFalse(result.isPresent());
    }

    @Test
    void testEmptyString() {
        // Input: Empty string
        String input = "";
        Optional<Month> result = Month.parse(input);

        // Expect empty Optional
        assertFalse(result.isPresent());
    }

    @Test
    void testNullString() {
        // Input: Null string
        String input = null;
        Optional<Month> result = Month.parse(input);

        // Expect empty Optional
        assertFalse(result.isPresent());
    }
}
