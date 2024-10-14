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

//Goal: Verify how different combinations of search flags are correct.
class SearchFlagCombTest {

    SearchGroup searchGroup;

    @Test
    public void testFlag1() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.of(SearchFlags.REGULAR_EXPRESSION));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.REGULAR_EXPRESSION));
        assertFalse(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }

    @Test
    public void testFlag2() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.of(SearchFlags.FULLTEXT));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }

    @Test
    public void testFlag3() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.of(SearchFlags.CASE_SENSITIVE));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.CASE_SENSITIVE));
        assertFalse(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }

    @Test
    public void testFlag4() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.of(SearchFlags.FULLTEXT,SearchFlags.CASE_SENSITIVE));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.CASE_SENSITIVE));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }

    @Test
    public void testFlag5() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.of(SearchFlags.REGULAR_EXPRESSION,SearchFlags.FULLTEXT));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.REGULAR_EXPRESSION));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }

    @Test
    public void testFlag6() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.of(SearchFlags.REGULAR_EXPRESSION,SearchFlags.CASE_SENSITIVE));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.REGULAR_EXPRESSION));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.CASE_SENSITIVE));
        assertFalse(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }

    @Test
    public void testFlag7() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.of(SearchFlags.REGULAR_EXPRESSION,SearchFlags.FULLTEXT,SearchFlags.CASE_SENSITIVE));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.REGULAR_EXPRESSION));
        assertTrue(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }

    @Test
    public void testFlag8() {
        searchGroup = new SearchGroup("TestName", GroupHierarchyType.INDEPENDENT, "Moon", EnumSet.noneOf(SearchFlags.class));
        assertFalse(searchGroup.getSearchFlags().contains(SearchFlags.REGULAR_EXPRESSION));
        assertFalse(searchGroup.getSearchFlags().contains(SearchFlags.CASE_SENSITIVE));
        assertFalse(searchGroup.getSearchFlags().contains(SearchFlags.FULLTEXT));
    }
}
