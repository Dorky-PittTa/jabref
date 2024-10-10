package org.jabref.model.strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnicodeToReadableCharMapTest {

    @Test
    public void testUnicodeConversion() {
        UnicodeToReadableCharMap unicodeMap = new UnicodeToReadableCharMap();
        // Test cases for Unicode characters that should be converted
        assertEquals("A", unicodeMap.get("\u00C0")); // À
        assertEquals("Ae", unicodeMap.get("\u00C4")); // Ä
        assertEquals("ss", unicodeMap.get("\u00DF")); // ß
        assertEquals("Y", unicodeMap.get("\u0178")); // Ÿ
        assertEquals("ae", unicodeMap.get("\u00E6")); // æ
        assertEquals("O", unicodeMap.get("\u00D3")); // Ó
        assertEquals("ue", unicodeMap.get("\u00FC")); // ü

        // Test cases for characters that should not be converted
        assertNull(unicodeMap.get("A")); // Regular ASCII 'A'
        assertNull(unicodeMap.get("Z")); // Regular ASCII 'Z'
        assertNull(unicodeMap.get("1")); // Digit '1'
        assertNull(unicodeMap.get("@")); // Special symbol '@'

        // Test case for unmapped Unicode
        assertNull(unicodeMap.get("\u00FF")); // 'ÿ', which is not mapped in the test class
    }

    @Test
    public void testIsUnicode() {
        UnicodeToReadableCharMap unicodeMap = new UnicodeToReadableCharMap();

        // Helper method to check if a string contains Unicode characters
        assertTrue(isUnicode("\u00C0"));  // Unicode 'À'
        assertTrue(isUnicode("\u00FC"));  // Unicode 'ü'
        assertTrue(isUnicode("\u1EF9"));  // Unicode 'ỹ'

        // Test with non-Unicode ASCII characters
        assertFalse(isUnicode("A"));      // Regular ASCII 'A'
        assertFalse(isUnicode("B"));      // Regular ASCII 'B'
        assertFalse(isUnicode("1"));      // Regular digit '1'
        assertFalse(isUnicode("!"));      // Special symbol '!'
    }

    // A method to check if string contains unicode character
    private boolean isUnicode(String str) {
        for (char c : str.toCharArray()) {
            if (c > 127) {
                return true; // Non-ASCII character (likely Unicode)
            }
        }
        return false; // All ASCII characters
    }
}
