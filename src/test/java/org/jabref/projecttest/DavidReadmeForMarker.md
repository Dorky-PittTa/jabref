# Test Case: `testParseHighlight`

- **Goal**: Verify if the `parse` method of `FileAnnotationType` correctly identifies the `HIGHLIGHT` annotation type as supported.
  
- **Input Space Partitioning Characteristics**:
  - **Interface-based characteristic**: Validate that the `parse` method can handle valid annotation types and return the correct `FileAnnotationType`.
  - **Functionality-based characteristic**: Ensure that the `isMarkedFileAnnotationType` method correctly identifies `HIGHLIGHT` as a marked annotation type.

## Input Domain Modelling:
1. **Identify testable functions**: 
   - `FileAnnotationType.parse(PDAnnotation annotation)`
   - `FileAnnotationType.isMarkedFileAnnotationType(String annotationType)`
  
2. **Parameters**:
   - `PDAnnotation annotation` (mocked to return `"HIGHLIGHT"`).
   
3. **Return types**: 
   - `FileAnnotationType` for `parse`.
   - `boolean` for `isMarkedFileAnnotationType`.

## Model Input Domain:
- **Annotation Type**: `"HIGHLIGHT"` (valid and supported).

## Combine Partitions:
- Use **BCC (Base Choice Coverage)** for this case.

## Test Values:
- **annotation**: A mocked `PDAnnotation` that returns `"HIGHLIGHT"`.

## Expected Behavior:
- The `parse` method should return `FileAnnotationType.HIGHLIGHT`.
- The `isMarkedFileAnnotationType` method should return `true`.

---

# Test Case: `testParseKnownButUnsupported`

- **Goal**: Verify if the `parse` method can identify the `TEXT` annotation type, which is valid but not supported.

- **Input Space Partitioning Characteristics**:
  - **Interface-based characteristic**: Validate that the `parse` method can handle valid annotation types and return the correct `FileAnnotationType`.
  - **Functionality-based characteristic**: Ensure that the `isMarkedFileAnnotationType` method correctly identifies `TEXT` as not a marked annotation type.

## Input Domain Modelling:
1. **Identify testable functions**: 
   - `FileAnnotationType.parse(PDAnnotation annotation)`
   - `FileAnnotationType.isMarkedFileAnnotationType(String annotationType)`

2. **Parameters**:
   - `PDAnnotation annotation` (mocked to return `"TEXT"`).

3. **Return types**: 
   - `FileAnnotationType` for `parse`.
   - `boolean` for `isMarkedFileAnnotationType`.

## Model Input Domain:
- **Annotation Type**: `"TEXT"` (valid but unsupported).

## Combine Partitions:
- Use **BCC (Base Choice Coverage)** for this case.

## Test Values:
- **annotation**: A mocked `PDAnnotation` that returns `"TEXT"`.

## Expected Behavior:
- The `parse` method should return `FileAnnotationType.TEXT`.
- The `isMarkedFileAnnotationType` method should return `false`.

---

# Test Case: `testParseUnknown`

- **Goal**: Verify if the `parse` method can handle an unknown annotation type and return `UNKNOWN`.

- **Input Space Partitioning Characteristics**:
  - **Interface-based characteristic**: Validate that the `parse` method can handle invalid annotation types and return the appropriate `FileAnnotationType`.
  - **Functionality-based characteristic**: Ensure that the `isMarkedFileAnnotationType` method correctly identifies unknown annotation types as not marked.

## Input Domain Modelling:
1. **Identify testable functions**: 
   - `FileAnnotationType.parse(PDAnnotation annotation)`
   - `FileAnnotationType.isMarkedFileAnnotationType(String annotationType)`

2. **Parameters**:
   - `PDAnnotation annotation` (mocked to return `"IMG"`).

3. **Return types**: 
   - `FileAnnotationType` for `parse`.
   - `boolean` for `isMarkedFileAnnotationType`.

## Model Input Domain:
- **Annotation Type**: `"IMG"` (unknown type).

## Combine Partitions:
- Use **BCC (Base Choice Coverage)** for this case.

## Test Values:
- **annotation**: A mocked `PDAnnotation` that returns `"IMG"`.

## Expected Behavior:
- The `parse` method should return `FileAnnotationType.UNKNOWN`.
- The `isMarkedFileAnnotationType` method should return `false`.
