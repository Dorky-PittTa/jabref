/* Copyright (C) 2024 Sirawich Anantapong ,Supitsara Sareecam - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package org.jabref.projecttest;

import org.jabref.model.strings.UnicodeToReadableCharMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnicodeToReadableCharMapTest {
    UnicodeToReadableCharMap unicodeMap = new UnicodeToReadableCharMap();

//    Goals: Verify that the `UnicodeToReadableCharMap` class correctly converts specific Unicode characters to their readable equivalents, and returns `null` for unmapped or non-Unicode characters.
    @Test
    public void testMappedEnglishCharacter() {
        // Test mapped English character 'À'
        assertEquals("A", unicodeMap.get("\u00C0")); // Unicode 'À'
    }

    @Test
    public void testMappedNonEnglishCharacter() {
        // Test mapped non-English character 'ß'
        assertEquals("ss", unicodeMap.get("\u00DF")); // Unicode 'ß'
    }

    @Test
    public void testUnmappedEnglishCharacter() {
        // Test unmapped regular ASCII character 'A'
        assertNull(unicodeMap.get("A")); // Regular 'A'
    }

    @Test
    public void testUnmappedNonEnglishCharacter() {
        // Test mapped non-English character 'ü'
        assertEquals("ue", unicodeMap.get("\u00FC")); // Unicode 'ü'
    }

    @Test
    public void testControlCharacterOrSymbol() {
        // Test unmapped symbol '☃'
        assertNull(unicodeMap.get("\u2603")); // Unicode snowman
    }

    @Test
    public void testEmptyString() {
        // Test empty string input
        assertNull(unicodeMap.get("")); // Empty string
    }
}
