/* Copyright (C) 2024 Sirawich Anantapong ,Supitsara Sareecam - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package org.jabref.projecttest;
//package org.jabref.model.groups;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.groups.GroupHierarchyType;
import org.jabref.model.groups.SearchGroup;
import org.jabref.model.search.SearchFlags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MatchEntryTest {

    private SearchGroup searchGroup;
    private BibEntry entry1;
    private BibEntry entry2;

    @BeforeEach
    public void setUp() {
        EnumSet<SearchFlags> searchFlags = EnumSet.noneOf(SearchFlags.class);
        searchGroup = new SearchGroup("TestGroup", GroupHierarchyType.INDEPENDENT, "title=java", searchFlags);

        // Use StandardField for BibEntry fields
        entry1 = new BibEntry().withField(StandardField.TITLE, "Introduction to Java").withField(StandardField.AUTHOR, "John Doe");
        entry2 = new BibEntry().withField(StandardField.TITLE, "Python Basics").withField(StandardField.AUTHOR, "Jane Doe");
    }

    @Test
    public void testMatchingEntries() {
        SearchGroup group = new SearchGroup("TestGroup", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.noneOf(SearchFlags.class));

        BibEntry entry1 = new BibEntry();
        entry1.setId("Entry1");
        BibEntry entry2 = new BibEntry();
        entry2.setId("Entry2");

        // No matches initially
        assertFalse(group.contains(entry1));
        assertFalse(group.contains(entry2));

        // Update matches and check
        group.updateMatches(entry1, true);
        group.updateMatches(entry2, true);
        assertTrue(group.contains(entry1));
        assertTrue(group.contains(entry2));

        // Remove a match
        group.updateMatches(entry1, false);
        assertFalse(group.contains(entry1));
        assertTrue(group.contains(entry2));
    }

    @Test
    public void testSearchExpressionMatches() {
        searchGroup.updateMatches(entry1, true);
        assertTrue(searchGroup.contains(entry1));
        assertFalse(searchGroup.contains(entry2));
    }

    @Test
    public void testRemoveMatchedEntry() {
        searchGroup.updateMatches(entry1, true);
        assertTrue(searchGroup.contains(entry1));

        // Now remove the match
        searchGroup.updateMatches(entry1, false);
        assertFalse(searchGroup.contains(entry1));
    }

    @Test
    public void testGetSearchExpression() {
        assertEquals("title=java", searchGroup.getSearchExpression());

        // Update search expression and verify the update
        searchGroup.setSearchExpression("author=jane");
        assertEquals("author=jane", searchGroup.getSearchExpression());
    }

    @Test
    public void testEqualsAndHashCode() {
        SearchGroup sameGroup = new SearchGroup("TestGroup", GroupHierarchyType.INDEPENDENT, "title=java", EnumSet.noneOf(SearchFlags.class));
        SearchGroup differentGroup = new SearchGroup("AnotherGroup", GroupHierarchyType.INDEPENDENT, "title=python", EnumSet.noneOf(SearchFlags.class));

        // Test equality
        assertEquals(searchGroup, sameGroup);
        assertNotEquals(searchGroup, differentGroup);

        // Test hashcode
        assertEquals(searchGroup.hashCode(), sameGroup.hashCode());
        assertNotEquals(searchGroup.hashCode(), differentGroup.hashCode());
    }

    @Test
    public void testIsDynamic() {
        assertTrue(searchGroup.isDynamic());
    }

    @Test
    public void testDeepCopy() {
        SearchGroup copy = (SearchGroup) searchGroup.deepCopy();
        assertNotSame(searchGroup, copy);
        assertEquals(searchGroup, copy);
    }

    @Test
    public void testSetMatchedEntries() {
        Set<String> matchedEntries = new HashSet<>();
        matchedEntries.add(entry1.getId());

        searchGroup.setMatchedEntries(matchedEntries);

        assertTrue(searchGroup.contains(entry1));
        assertFalse(searchGroup.contains(entry2));
    }

    @Test
    public void testSetSearchExpressionAndFlags() {
        EnumSet<SearchFlags> searchFlags = EnumSet.of(SearchFlags.CASE_SENSITIVE);

        searchGroup = new SearchGroup("NewGroup", GroupHierarchyType.INCLUDING, "author=doe", searchFlags);
        assertEquals("author=doe", searchGroup.getSearchExpression());
        assertEquals(searchFlags, searchGroup.getSearchFlags());
    }
}
