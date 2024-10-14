# Test Suite 10: FileAnnotationTypeTest

## Goal
Verify that the `FileAnnotationType` enum can correctly identify and handle various file annotation types.

## Testable Functions
- **parse(PDAnnotation annotation)**: This method should determine the `FileAnnotationType` based on a given `PDAnnotation` subtype. If the type is unsupported, it should return `UNKNOWN`.
- **isMarkedFileAnnotationType(String annotationType)**: This method should check if the given annotation type is a supported marked `FileAnnotationType`.

## Parameters
- **PDAnnotation annotation**: The raw PDF annotation whose subtype will be evaluated.
- **String annotation type**: A type descriptor to check against the supported marked file annotation types.

## Return Types
- `parse` returns the corresponding `FileAnnotationType`.
- `isMarkedFileAnnotationType` returns a boolean indicating whether the type is marked.

## Return Values
- The `parse` method is expected to return the appropriate `FileAnnotationType`, or `UNKNOWN` if the type is not defined.
- The `isMarkedFileAnnotationType` method returns `true` if the annotation type is supported and linked; otherwise, it returns `false`.

## Exceptional Behavior
- When an unsupported annotation type is passed to the `parse` method, it logs a warning and returns `UNKNOWN`.

## Input Space Partitioning Characteristics
### Interface-based characteristic:
- **C1**: Valid/Invalid annotation subtypes.

### Functionality-based characteristic:
- **C2**: Supported/Unsupported annotation types.

## Model Input Domain
- **Valid Annotations**: Annotations that match the defined `FileAnnotationType` values.
- **Invalid Annotations**: Annotations that do not match any defined `FileAnnotationType`.

## Combine Partitions
Using BCC (Base Choice Coverage):
- **Mapped/Unmapped**: {True, False} (represents linked annotation types)
- **Supported/Unsupported**: {True, False} (indicates if the annotation type is recognized)

## Test Values and Expected Results

### Base Choice: (Annotation Type, Supported) & (Unknown, unsupported)
- Number of Tests: 1 + ((2-1) + (2-1)) = 3 tests

| Test Case | Annotation Type | Expected Result |
|-----------|-----------------|------------------|
| 1         | HIGHLIGHT       | Supported (True) |
| 2         | TEXT            | Not supported (False) |
| 3         | UNKNOWN          | Not supported (False) |

## Test Cases

| Test Cases                                     |  Expected Outcome  |
|------------------------------------------------|--------------------|
|- **Test case 1**: `testParseHighlight`           |  (HIGHLIGHT, TURE)  |
|- **Test case 2**: `testParseKnownButUnsupported` |  (TEXT, FALSE)  |
|- **Test case 3**: `testParseUnknown`             |  (UNKNOWN, FALSE)  |
