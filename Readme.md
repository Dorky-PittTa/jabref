## test if string isUnicode

src\test\java\org\jabref\model\strings\isUnicodeTest.java
@test
public classs isUnicode extends HashMap<String, String> {

### Test Case: `testSimpleInterestCalculation`

- **Goal**: Test the correctness of the simple interest calculation.
- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**: Validate that the inputs (principal, rate, time) are positive and non-zero.
    - **Functionality-based characteristic**: Ensure the formula is implemented correctly (`SI = P * R * T`).
- **Input Domain Modelling**:
    1. **Identify testable function**: `simpleInterest(double principal, double rate, int time)`
    2. **Parameters**: Principal (double), rate (double), time (int).
       **Return type**: double.
       **Return values**: The expected interest.
       **Exception behavior**: Throws `IllegalArgumentException` for negative values.
    3. **Model input domain**:
        - Principal: Positive doubles (e.g., 1000.0, 5000.0).
        - Rate: Positive doubles (e.g., 0.05, 0.1).
        - Time: Positive integers (e.g., 2, 5).
    4. **Combine partitions**: Use ACoC (All Combinations Coverage) for this case.
    5. **Test values**:
        - Principal: 1000.0, Rate: 0.05, Time: 2.
        - Expected value: 100.0.

### Test Case: `testNegativePrincipalThrowsException`

- **Goal**: Ensure that the system throws an `IllegalArgumentException` when the principal is negative.
- **Input Space Partitioning Characteristics**:
    - **Interface-based characteristic**: Validate that principal values are non-negative.
    - **Functionality-based characteristic**: Handle invalid inputs.
- **Input Domain Modelling**:
    1. **Identify testable function**: `simpleInterest(double principal, double rate, int time)`
    2. **Parameters**: Principal (double), rate (double), time (int).
       **Return type**: Exception.
       **Exception behavior**: Throws `IllegalArgumentException` for negative principal.
    3. **Model input domain**:
        - Principal: Invalid values (e.g., -1000).
        - Rate: Positive doubles (e.g., 0.05).
        - Time: Positive integers (e.g., 2).
    4. **Combine partitions**: Use ECC (Each Choice Coverage) for this case.
    5. **Test values**:
        - Principal: -1000, Rate: 0.05, Time: 2.
        - Expected result: `IllegalArgumentException` thrown.
