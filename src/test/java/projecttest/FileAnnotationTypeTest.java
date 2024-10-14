/* Copyright (C) 2024 <ZISHUN GAO> - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */
package org.jabref.projecttest;

import org.jabref.model.pdf.FileAnnotationType;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

class FileAnnotationTypeTest {

    //Test case: 1
    //Test Case: testParseKnownAnnotationType
    //Goal: Verify that known annotation types (e.g., "Highlight") are correctly parsed by the `FileAnnotationType.parse()` method.
    @Test
    public void testParseKnownAnnotationType() {
        // Mock PDAnnotation with known subtype "Highlight"
        PDAnnotation annotationHighlight = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationHighlight.getSubtype()).thenReturn("Highlight");

        // Mock PDAnnotation with known subtype "Underline"
        PDAnnotation annotationUnderline = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationUnderline.getSubtype()).thenReturn("Underline");

        // Mock PDAnnotation with known subtype "Ink"
        PDAnnotation annotationInk = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationInk.getSubtype()).thenReturn("Ink");

        // Verify that the parse method correctly returns the corresponding FileAnnotationType
        assertEquals(FileAnnotationType.HIGHLIGHT, FileAnnotationType.parse(annotationHighlight));
        assertEquals(FileAnnotationType.UNDERLINE, FileAnnotationType.parse(annotationUnderline));
        assertEquals(FileAnnotationType.INK, FileAnnotationType.parse(annotationInk));
    }

    //Test case: 2
    //Test Case: testParseUnknownAnnotationType
    //Goal: Verify that the `FileAnnotationType.parse()` method returns `FileAnnotationType.UNKNOWN` for unknown annotation subtypes.
    @Test
    public void testParseUnknownAnnotationType() {
        // Mock PDAnnotation with an unknown subtype "UnknownType"
        PDAnnotation annotationUnknown = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationUnknown.getSubtype()).thenReturn("UnknownType");

        // Mock PDAnnotation with a null subtype
        PDAnnotation annotationNull = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationNull.getSubtype()).thenReturn(null);

        // Verify that the parse method correctly returns FileAnnotationType.UNKNOWN
        assertEquals(FileAnnotationType.UNKNOWN, FileAnnotationType.parse(annotationUnknown));
        assertEquals(FileAnnotationType.UNKNOWN, FileAnnotationType.parse(annotationNull));
    }

    //Test case: 3
    //Test Case: testParseHighlightAnnotation
    //Goal: Verify that the `FileAnnotationType.parse()` correctly handles the "Highlight" subtype.
    @Test
    public void testParseHighlightAnnotation() {
        // Mock PDAnnotation with subtype "Highlight"
        PDAnnotation annotationHighlight = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationHighlight.getSubtype()).thenReturn("Highlight");

        // Verify that the parse method returns HIGHLIGHT
        assertEquals(FileAnnotationType.HIGHLIGHT, FileAnnotationType.parse(annotationHighlight));
    }

    //Test case: 4
    //Test Case: testParseNullAnnotationType
    //Goal: Verify that `FileAnnotationType.parse()` handles null subtype properly.
    @Test
    public void testParseNullAnnotationType() {
        // Mock PDAnnotation with null subtype
        PDAnnotation annotationNull = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationNull.getSubtype()).thenReturn(null);

        // Verify that the parse method returns UNKNOWN for null subtypes
        assertEquals(FileAnnotationType.UNKNOWN, FileAnnotationType.parse(annotationNull));
    }

    //Test case: 5
    //Test Case: testParseUnderlineAnnotation
    //Goal: Verify that the `FileAnnotationType.parse()` correctly handles the "Underline" subtype.
    @Test
    public void testParseUnderlineAnnotation() {
        // Mock PDAnnotation with subtype "Underline"
        PDAnnotation annotationUnderline = Mockito.mock(PDAnnotation.class);
        Mockito.when(annotationUnderline.getSubtype()).thenReturn("Underline");

        // Verify that the parse method returns UNDERLINE
        assertEquals(FileAnnotationType.UNDERLINE, FileAnnotationType.parse(annotationUnderline));
    }
}
