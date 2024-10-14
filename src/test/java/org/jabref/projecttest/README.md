# Unit Test Documentation
#### Unnamed_404
Documented by Sareecam

---
### Project: JabRef
- **Build**: Gradle
- **Package Name**: `projecttest`
### List of Test Suites in the Package
1. `PagingTest`: ACoC*, (ECC)
2. `PaginationTest`: M-BCC*, (BCC)
3. `SearchFlagCombTest`: M-BCC*
4. `CitationKeyTest`: ACoC*
5. `UnicodeMapTest`: PWC*
6. `UpdateEntrytest`: BCC*
7. `MonthTest`: ECC*
8. `ISBNTest`: ECC*
9. `SearchQueryTest`: PWC*
10. `FileAnnotationTypeTest`: BCC*

---

## Test Suite 1: `PagingTest`
**Goal**: Verify that the `Page` class correctly stores
and returns the content, and ensures the content is unchangeable.
- **Testable Function**: `getContent` method in the `Page` class.
- **Parameter**: The method uses the variable from `Page`'s parameter.
    - `query` *(String)*
    - `pageNumber` *(int)*
    - `content` *(Collection of type `T`)*
    - However, the only field that affects the method is `content`.
- **Return Type**: `Collection<T>`
- **Return Values**:
    - A non-empty collection of content exists on the page.
    - An empty collection if the content on the page is empty.
- **Exceptional Behaviour**: With `Collections.unmodifiableCollection()`, any attempt to modify the content
  should throw an `UnsupportedOperationException`.


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: The number of items inside the collection
    - **Functionality-based characteristic**:
        - C2: Existence of duplicate items inside the collection

### Model Input Domain:
1. **Content Size Domain**:
    - Empty collection `(size = 0)`
    - Single-element collection `(size = 1)`
    - Multiple-element collection `(size > 1)`
2. **Duplication Domain**:
    - No content duplicated: `(["Moon", "Sun", "Star"])`
    - Content duplicated: `(["Moon", "Sun", "Sun"])`
3. **Modification Behaviour**:
    - Attempt to modify the collection after retrieval should throw `UnsupportedOperationException`.

### Combine Partitions:
#### Using **ACoC (All Combinations Coverage)**:
- Content size: {0, 1, >1}.
- Duplicated content: {True, False}.

**Test Values and Expected Values**
- Number of Tests: 3*2 = 6
- However, it is not possible to have duplicated items with the number of item lower than 2.
  Number of Real Tests: 4

  | **Test Case** | **Size** | **Collection Value**  | **Duplication** | **Expected Value** |
    |---------------|----------|-----------------------|-----------------|--------------------|
  | 1             | 0        | []                    | False           | Empty collection   |
  | 2             | 1        | ["Moon"]              | False           | Moon               |
  | 3             | 3        | ["Moon","Sun","Star"] | False           | Moon, Sun, Star    |
  | 4             | 3        | ["Moon","Sun","Sun"]  | True            | Moon, Sun, Sun     |

- Test case 1: `EmptyCollectionTest`
- Test case 2: `SingleCollectionTest`
- Test case 3: `MultipleCollectionTest`
- Test case 4: `DuplicatedCollectionTest`

#### Using **ECC (Each Choice Coverage)**:
- Content size: {3}
- Duplicated content: {True, False}.

**Test Values and Expected Values**
- Number of Tests: Highest = 3
- Real Number of Tests: 2


| **Test Case** | **Size** | **Collection Value** | **Duplication** | **Expected Value** | 
  |---------------|----------|----------------------|-----------------|--------------------|
| 1             | 1        | ["Moon"]             | False           | Moon               |
| 2             | 3        | ["Moon","Sun","Sun"] | True            | Moon, Sun, Sun     |
- The test domain are already covered by ACoC

---

## Test Suite 2: `PaginationTest`
- **Goal**: Verify that the `Page` class correctly returns page numbers.
- **Testable Function**: `getPageNumber` method in the `Page` class.
- **Parameter**: The method uses the variable from `Page`'s parameter.
    - `query` *(String)*
    - `pageNumber` *(int)*
    - `content` *(Collection of type `T`)*
    - However, the only field that affects the method is `pageNumber`.
- **Return Type**: `int`
- **Return Values**:
    - Only a positive integer.
- **Exceptional Behaviour**:
    - Any illegal page number
      should throw a `IllegalArgumentException`.


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: The value of the page number
    - **Functionality-based characteristic**:
        - C2: The existence of the content on the page

### Model Input Domain:
1. **Page Number Value**:
    - Negative number: `(num < 0)`
    - 1 or above: `(num >= 1)`
    - No page: `(num = 0)`
2. **Correct Number**:
    - The page is empty: `(True)`
    - The page is not empty (has content): `(False)`
3. **Illegal Value**:
    - Illegal number should throw `IllegalArgumentException`.

### Combine Partitions:
#### Using **M-BCC (Multiple Base Choice Coverage)**:
- Content size: {<0, 0, >=1}.
- Empty page: {True,False}.

**Test Values and Expected Values**
- Base tests: (int = -1, empty = True), (int = 0, empty = False)
- Number of Tests: 2+((3-2)+(2-2)) = 3


| **Test Case** | **Value Domain** | **pageNumber** | **Empty Content** | **Expected Result** | 
|---------------|------------------|----------------|-------------------|---------------------|
| 1 (base)      | negative         | -1             | True              | Exception           |
| 2 (base)      | zero             | 0              | False             | No page             |
| 3             | 1 or above       | 3              | False             | 3                   |

- Test case 1: `testNegativePageNumber`
- Test case 2: `testPageNumberZero`
- Test case 3: `testPositivePageNumber`

#### Using **BCC (Base Choice Coverage)**:
- Content size: {<0, 0, >=1}.
- Empty page: {True,False}.

**Test Values and Expected Values**
- Base tests: (int = 3, empty = True)
- Number of Tests: 1+((3-1)+(2-1)) = 4

| **Test Case** | **Value Domain** | **pageNumber** | **Empty Content** | **Expected Result** | 
|---------------|------------------|----------------|-------------------|---------------------|
| 1 (base)      | Positive         | 3              | True              | 3                   |
| 2             | zero             | 0              | False             | No page             |
| 3             | Negative         | -1             | False             | Exception           |
| 4             | Positive         | 3              | False             | 3                   |

- Test case 4: `testNoContentPageNumber`
- Test case 5: `testNegWithContentPageNumber`
- Other domains are already covered by MBCC.

---
## Test Suite 3: `SearchFlagCombTest`
- **Goal**: Verify how different combinations of search flags in `SearchGroup` class are correct when enabled multiple flags.
- **Testable Function**: `getSearchFlags` in the `SearchGroup` class.
- **Parameter**: The method uses the variable from `SearchGroup`'s parameter.
    - `name` *String*
    - `context` *GroupHierarchyType*
    - `searchExpression` *String*
    - `searchFlags` *EnumSet\<SearchFlags>*
    -  However, the only field that affects the method is `searchFlags`.
- **Return Type**: `EnumSet\<SearchFlags>`
- **Return Values**:
    - The method is expected to return the enabled search flags of a `SearchQuery` object.
- **Exceptional Behaviour**: -


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: The type of search flags
    - **Functionality-based characteristic**:
        - C2: The existence of Fulltext flag,
          as the flag is for an advanced search that searches beyond the content and tends to face many errors.

### Model Input Domain:
1. **Flag Type Combination**:
    - Flag for regex search: `(REGULAR_EXPRESSION)`
    - Flag for advance search: `(FULLTEXT)`
    - Flag for case-sensitive search: `(CASE_SENSITIVE)`
    - Two flags Combinations:
        - `(REGULAR_EXPRESSION,FULLTEXT)`
        - `(REGULAR_EXPRESSION,CASE_SENSITIVE)`
        - `(FULLTEXT,CASE_SENSITIVE)`
    - Three flags Combination: `(REGULAR_EXPRESSION,FULLTEXT,CASE_SENSITIVE)`
    - No flag: `()`
2. **Fulltext Enabled**:
    - No Fulltext: `False`
    - Contain Fulltext: `True`

### Combine Partitions:
#### Using **M-BCC (Multiple Base Choice Coverage)**:
- Flag Type: {Regex, Fulltext, Sensitive}.
- Flag Enum Length: {0,1,2,3}.

**Test Values and Expected Values**
- Base tests: (type = {Regex}, contain = False), (type = {Fulltext}, contain = True)
- Number of Tests: 2+((8-2)+(2-2)) = 8

| **Test Case** | **Type**                   | **Contain Fulltext** | **Expected Result**      | 
|---------------|----------------------------|----------------------|--------------------------|
| 1 (base)      | {Regex}                    | False                | Regex                    |
| 2 (base)      | {Fulltext}                 | True                 | Fulltext                 |
| 3             | {Sensitive}                | False                | Sensitive                |
| 4             | {Fulltext,Sensitive}       | True                 | Fulltext,Sensitive       |
| 5             | {Regex,Fulltext}           | True                 | Regex,Fulltext           |
| 6             | {Regex,Sensitive}          | False                | Regex,Sensitive          |
| 7             | {Regex,Fulltext,Sensitive} | True                 | Regex,Fulltext,Sensitive |
| 8             | {}                         | False                | No flag                  |

- Test case 1: `testFlag1`
- Test case 2: `testFlag2`
- Test case 3: `testFlag3`
- Test case 4: `testFlag4`
- Test case 5: `testFlag5`
- Test case 6: `testFlag6`
- Test case 7: `testFlag7`
- Test case 8: `testFlag8`

---

## Test Suite 4: `CitationKeyTest`
- **Goal**: Verify that the `Citation` class correctly handles valid and invalid citation keys by checking with `BibDatabase` class and returns the expected lookup results.
- **Testable Function**: `getLookupResult` in the `Citation` class.
- **Parameter**: The method uses the variable from `Citation`'s parameter.
    - `citationKey` *String*
    -  The citation key will be used in `CitationLookupResult` to look up databases.
- **Return Type**: `Optional<CitationLookupResult> db`
- **Return Values**:
    - The method is expected to return the database using the citation key.
      However, the test used `citation.getLookupResult().isPresent()` which return a boolean value.
- **Exceptional Behaviour**: -


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: The type of citation key
    - **Functionality-based characteristic**:
        - C2: The existence of citation key

### Model Input Domain:
1. **Key Type**:
    - Valid key
    - Invalid key
2. **Key Exist**:
    - No key: `False`
    - Has a key: `True`

### Combine Partitions:
#### Using **ACoC (All Combinations Coverage)**:
- Key Type: {valid, invalid}.
- Key Existence: {True,False}.

**Test Values and Expected Values**
- Number of Tests: 2*2 = 4 tests
- However, when there is a key, the key existence can only be true.
- Real Number of Tests: 3 tests

| **Test Case** | **Type** | **Exist** | **Expected Result**    | 
|---------------|----------|-----------|------------------------|
| 1             | Valid    | True      | Can find db (True)     |
| 2             | Invalid  | True      | Cannot find db (False) |
| 3             | No key   | False     | Cannot find db (False) |

- Test case 1: `testValidKey`
- Test case 2: `testInvalidKey`
- Test case 3: `testNoKey`

---

## Test Suite 5: `UnicodeMapTest`
- **Goal**: Verify that the `UnicodeToReadableCharMap` class correctly converts specific Unicode characters to their readable equivalents, and returns `null` for unmapped or non-Unicode characters.
- **Testable Function**: `get` method that is an extension of `HashMap` from `java.util.HashMap`.
- **Parameter**: The method uses the variable from `HashMap`'s parameter.
    - `string` *String*
- **Return Type**: `String`
- **Return Values**:
    - The method is expected to return the string that is mapped in Unicode.
      This string will then be used to check with `assertEquals` which returns a boolean value.
- **Exceptional Behaviour**: -

- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: Mapped/Unmapped characters
    - **Functionality-based characteristic**:
        - C2: English/Non-English alphabets

### Model Input Domain:
1. **Mapped/Unmapped**:
    - Mapped key: `True` (e.g. A, Oe, ij, ss)
    - Unmapped key: `False` (e.g. @, 1, æ, empty string)
2. **English/Non-English**:
    - English: `True` (e.g. A, O, u)
    - Non-english: `False` (e.g. æ, Ỹ, À)

### Combine Partitions:
#### Using **PWC (Pair-Wise Coverage)**:
- Mapped/Unmapped: {True, False}.
- English/Non-english: {True,False}.

**Test Values and Expected Values**
- Number of Tests: 2*2 = 4 tests

| **Test Case** | **Mapped/Unmapped**          | **English/Non-english** | **Expected Result** | 
|---------------|------------------------------|-------------------------|---------------------|
| 1             | Mapped (A)                   | True                    | True (\u00C0)       |
| 2             | Mapped (ae)                  | False                   | True (\u00E6)       |
| 3             | Unmapped (Regular ASCII 'A') | True                    | False  (Null)       |
| 4             | Unmapped (@)                 | False                   | False  (Null)       |

- Test case 1: `testMapEng`
- Test case 2: `testMapNoEng`
- Test case 3: `testUnmapEng`
- Test case 4: `testUnmapNoEng`

---

## Test Suite 6: `UpdateEntryTest`
- **Goal**: Verify that the `SearchGroup` class with `BibEntry` object can correctly update its entry.
- **Testable Function**: `updateMatches` method should update the entry and `searchGroup.contains()`
  will be used to check if the update is successful.
- **Parameter**: The `updateMatches` method uses the object from `BibEntry` class.
    - `entry` *BibEntry*
    - `matched` *boolean*
- **Return Type**: The `updateMatches` does not return a value.
  But `searchGroup.contains()` returns a `boolean`
- **Return Values**:
    - The method is expected to return True if the group contains the entry, and False if it does not.
- **Exceptional Behaviour**: -


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: Add/Remove entry
    - **Functionality-based characteristic**:
        - C2: Existing/Non-existing entry

### Model Input Domain:
1. **Add/Remove**:
    - Add entry: This will add the entry to the group.
    - Remove entry: This will remove the entry from the group.
2. **Existing/Non-Existing**:
    - Existing entry: Has some entries
    - Non-existing entry: Contains no entry

### Combine Partitions:
#### Using **BCC (Base Choice Coverage)**:
- Mapped/Unmapped: {True, False}.
- English/Non-english: {True,False}.

**Test Values and Expected Values**
- Base Choice: (Add, Existing)
- Number of Tests: 1+((2-1)+(2-1)) = 3 tests

| **Test Case** | **Add/Remove**   | **Existing/Non-existing** | **Expected Result** | 
|---------------|------------------|---------------------------|---------------------|
| 1             | Add              | Existing                  | Contain (True)      |
| 2             | Remove           | Existing                  | Not contain (False) |
| 3             | Remove           | Non-existing              | Not contain (False) |

- Test case 1: `testAddEntry`
- Test case 2: `testRemoveEntry`
- Test case 3: `testRemoveNoEntry`

---

## Test Suite 7: `MonthTest`
- **Goal**: Verify that the `Month` enum class work correctly when parsed.
- **Testable Function**: `parse` method inside `Month`.
- **Parameter**: The `getMatches` method uses the parameter from `DatabaseSearcher` class.
    - `value` *String*
    - However, `get()` and `isPresent()` are used to test the correctness of the result.
- **Return Type**:  `Optional<Month>`
- **Return Values**:
    - The month corresponding to the input.
- **Exceptional Behaviour**: -


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: Input type (full, short, germanShort, number, invalidName, invalidNum, empty)
    - **Functionality-based characteristic**:
        - C2: Input type (Has the same domain as the interface, but adds the possibility of null input)

### Model Input Domain:
1. **Input Type**:
    - Full: This parses the full name of a month.
    - Short: This parses the short name of a month.
    - German: This parses the German name of a month.
    - Number: This parses the number of a month.
    - Invalid name: This parses the invalid name of months.
    - Invalid num: This parses the invalid number of months.
    - Empty: This parses the empty string.
    - Null: This has null input

### Combine Partitions:
#### Using **ECC (Each Choice Coverage)**:
- Type: {"January", "jan", "Januar", "1", "InvalidMonth", "13", "", null}.

**Test Values and Expected Values**
- Number of Tests: Highest = 8 tests

| **Test Case** | **Input**       | **Expected Result** |
|---------------|-----------------|---------------------|
| 1             | "January"       | January (1st month) |
| 2             | "jan"           | January (1st month) | 
| 3             | "1"             | January (1st month) | 
| 4             | "InvalidMonth"d | empty Optional      | 
| 5             | "Januar"        | January (1st month) | 
| 6             | "13"            | empty Optional      | 
| 7             | ""              | empty Optional      | 
| 8             | Null            | empty Optional      | 

- Test case 1: `testValidFullMonthName`
- Test case 2: `testValidShortMonthName`
- Test case 2: `testValidNumericMonth`
- Test case 2: `testInvalidMonthName`
- Test case 2: `testValidGermanMonthName`
- Test case 2: `testInvalidNumericMonth`
- Test case 2: `testEmptyString`
- Test case 2: `testNullString`

---

## Test Suite 8: `ISBNTest`
- **Goal**: Verify that the `Month` enum class work correctly when parsed.
- **Testable Function**: `parse` method inside `Month`.
- **Parameter**: The `getMatches` method uses the parameter from `DatabaseSearcher` class.
    - `value` *String*
    - However, `get()` and `isPresent()` are used to test the correctness of the result.
- **Return Type**:  `Optional<Month>`
- **Return Values**:
    - The month corresponding to the input.
- **Exceptional Behaviour**: -


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: Input type
            - Valid ISBN-10
            - Valid ISBN-13
            - Invalid ISBN-10
            - Invalid ISBN-13
            - ISBN with hyphens
            - Empty string
            - Null string
    - **Functionality-based characteristic**:
        - C2: Input type (Has the same domain as the interface)

### Model Input Domain:
1. **Input Type**:
    - Valid ISBN-10
    - Valid ISBN-13
    - Invalid ISBN-10
    - Invalid ISBN-13
    - ISBN with hyphens
    - Empty string
    - Null string

(Checksum rule for 10: The checksum is calculated by multiplying each of the first 9 digits by decreasing numbers from 10 to 2, summing the results, and then checking if the last digit (or 'X') satisfies the formula so that the sum is divisible by 11.)

(Checksum rule for 13: The checksum is calculated by multiplying the digits alternately by 1 and 3, summing the results, and checking if the last digit satisfies the condition that the total sum is divisible by 10.)

### Combine Partitions:
#### Using **ECC (Each Choice Coverage)**:
- Type:
    - "123456789X"
    - "9781234567897"
    - "1234567890"
    - "9781234567890"
    - "978-1-2345-6789-7"
    - ""
    - null

**Test Values and Expected Values**
- Number of Tests: Highest = 11 tests

| **Test Case** | **Input**           | **Expected Result**        |
|---------------|---------------------|----------------------------|
| 1             | "123456789X"        | 123456789X                 |
| 2             | "9781234567897"     | 9781234567897              | 
| 3             | "1234567890"        | not present                | 
| 4             | "9781234567890"     | not present                | 
| 5             | "978-1-2345-6789-7" | 9781234567897              | 
| 6             | ""                  | not present                | 
| 7             | null                | Throw NullPointerException | 

- Test case 1: `testValidISBN10`
- Test case 2: `testValidISBN13`
- Test case 3: `testInvalidISBN10`
- Test case 4: `testInvalidISBN13`
- Test case 5: `testISBNWithHyphens`
- Test case 6: `testEmptyString`
- Test case 7: `testNullString`

---

## Test Suite 9: `SearchQueryTest`
Please read https://docs.jabref.org/finding-sorting-and-cleaning-entries/search for how to use the search expression.

- **Goal**: Verify that the `SearchQuery` class can handle the difference query input from users and progress with regex search setting to perform library search.
- **Testable Function**: `isValid()`, `getSearchExpression()`,`getJavaScriptPatternForWords()`||`getPatternForWords()` method from the `SearchQuery` class and pattern-related functions..
- **Parameter**: The method uses the variable from `SeachQuery`parameter.
    - `query` *(String)*
    - `searchFlags` *(EnumSet<SearchFlags>)*
- **Return Type**:
- `String` from `getSearchExpression()`.
- `Optional<Pattern>` from pattern generation.
- `boolean` from `isValid()`and `isPresent()`.
- **Return Values**:
    - The expected return values vary based on the `flags` and `query`. Validity is checked with `assertTrue/assertFalse`, and patterns or words are compared using `assertEquals`.
- **Exceptional Behaviour**: -

- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - C1: Query Type (Simple/Invalid/Special)
    - **Functionality-based characteristic**:
        - C2: Regex Flag (True/False)

### Model Input Domain:
1. **Query Type**:
    - Simple: True (e.g. "destiny child", "apple")
    - Wrong: False (e.g. "open bracket [", "open] bracket")
    - Special: True (e.g. "word$", "a+b*c?d")
2. **Regex Flag**:
    - Regex enabled: True (SearchFlags.REGULAR_EXPRESSION)
    - Regex disabled: False (No flags or other flags)

### Combine Partitions:
#### Using **PWC (Pair-Wise Coverage)** :
- Query Type: {Simple, Wrong, Special}.
- Regex Flag: {True, False}.

**Test Values and Expected Values**
- Number of Tests: 3*2 = 6 tests


| **Test Case** | **Query Type** | **Regex flag** | **Expected Result**                     | 
|---------------|----------------|----------------|-----------------------------------------|
| 1             | Simple         | False          | Pattern: (child),(destiny)              |
| 2             | Simple         | True           | Pattern: (destiny child)                |
| 3             | Wrong          | False          | Invalid query, empty Pattern words      |
| 4             | Wrong          | True           | Invalid query, Pattern: (open] bracket) |
| 5             | Special        | False          | Pattern: (word)                         |
| 6             | Special        | True           | Pattern: (a+b*c?d)                      |

- Test case 1: `testSimpleQueryNoFlags`
- Test case 2: `testSimpleQueryRegFlag`
- Test case 3: `testWrongQueryNoFlags`
- Test case 4: `testWrongQueryRegFlag`
- Test case 5: `testSpecialQueryNoFlags`
- Test case 6: `testSpecialQueryRegFlag`

---
## Test Suite 10: `FileAnnotationTypeTest`

- **Goal**: Verify that the `FileAnnotationType` enum can correctly identify and handle various file annotation types.
- **Testable Functions**
    - **parse(PDAnnotation annotation)**: This method should determine the `FileAnnotationType` based on a given `PDAnnotation` subtype. If the type is unsupported, it should return `UNKNOWN`.
    - **isMarkedFileAnnotationType(String annotationType)**: This method should check if the given annotation type is a supported marked `FileAnnotationType`.
- **Parameters**:
    - **PDAnnotation annotation**: The raw PDF annotation whose subtype will be evaluated.
    - **String annotation type**: A type descriptor to check against the supported marked file annotation types.
- **Return Types**:
    - `parse` returns the corresponding `FileAnnotationType`.
    - `isMarkedFileAnnotationType` returns a boolean indicating whether the type is marked.
- **Return Values**:
    - The `parse` method is expected to return the appropriate `FileAnnotationType`, or `UNKNOWN` if the type is not defined.
    - The `isMarkedFileAnnotationType` method returns `true` if the annotation type is supported and linked; otherwise, it returns `false`.
- **Exceptional Behavior**:
    - When an unsupported annotation type is passed to the `parse` method, it logs a warning and returns `UNKNOWN`.


- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**:
        - **C1**: Valid/Invalid annotation subtypes.
    - **Functionality-based characteristic**:
        - **C2**: Supported/Unsupported annotation types.

## Model Input Domain
- **Valid Annotations**: Annotations that match the defined `FileAnnotationType` values.
- **Invalid Annotations**: Annotations that do not match any defined `FileAnnotationType`.

## Combine Partitions
Using BCC (Base Choice Coverage):
- **Mapped/Unmapped**: {True, False} (represents linked annotation types)
- **Supported/Unsupported**: {True, False} (indicates if the annotation type is recognized)

### Test Values and Expected Results
- Base Choice: (Annotation Type, Supported) & (Unknown, unsupported)
- Number of Tests: 1 + ((2-1) + (2-1)) = 3 tests

| Test Case | Annotation Type | Expected Result       |
|-----------|-----------------|-----------------------|
| 1         | HIGHLIGHT       | Supported (True)      |
| 2         | TEXT            | Not supported (False) |
| 3         | UNKNOWN         | Not supported (False) |

### Test Cases
| Test Cases                                        | Expected Outcome    |
|---------------------------------------------------|---------------------|
| - **Test case 1**: `testParseHighlight`           | (HIGHLIGHT, TURE)   |
| - **Test case 2**: `testParseKnownButUnsupported` | (TEXT, FALSE)       |
| - **Test case 3**: `testParseUnknown`             | (UNKNOWN, FALSE)    |

---
