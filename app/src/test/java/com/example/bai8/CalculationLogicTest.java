// app/src/test/java/com/example/bai8/CalculationLogicTest.java
package com.example.bai8;

import org.junit.Before; // Import Before
import org.junit.Test;   // Import Test

import static org.junit.Assert.*; // Import static các hàm assert

public class CalculationLogicTest {

    private CalculationLogic calculator;

    @Before // Phương thức này chạy trước mỗi @Test method
    public void setUp() {
        calculator = new CalculationLogic();
    }

    //--- Test cho hàm add ---
    @Test
    public void add_twoPositiveNumbers_returnsCorrectSum() {
        assertEquals("5 + 3 should be 8", 8, calculator.add(5, 3));
    }

    @Test
    public void add_positiveAndNegativeNumber_returnsCorrectSum() {
        assertEquals("7 + (-3) should be 4", 4, calculator.add(7, -3));
    }

    @Test
    public void add_twoNegativeNumbers_returnsCorrectSum() {
        assertEquals("-2 + (-4) should be -6", -6, calculator.add(-2, -4));
    }

    @Test
    public void add_withZero_returnsNumberItself() {
        assertEquals("9 + 0 should be 9", 9, calculator.add(9, 0));
        assertEquals("0 + 9 should be 9", 9, calculator.add(0, 9));
    }

    //--- Test cho hàm subtract ---
    @Test
    public void subtract_positiveNumbers_returnsCorrectDifference() {
        assertEquals("10 - 4 should be 6", 6, calculator.subtract(10, 4));
    }

    @Test
    public void subtract_toGetNegativeResult_returnsCorrectDifference() {
        assertEquals("3 - 5 should be -2", -2, calculator.subtract(3, 5));
    }

    //--- Test cho hàm multiply ---
    @Test
    public void multiply_twoPositiveNumbers_returnsCorrectProduct() {
        assertEquals("6 * 7 should be 42", 42, calculator.multiply(6, 7));
    }

    @Test
    public void multiply_byZero_returnsZero() {
        assertEquals("8 * 0 should be 0", 0, calculator.multiply(8, 0));
        assertEquals("0 * 8 should be 0", 0, calculator.multiply(0, 8));
    }

    @Test
    public void multiply_withNegativeNumber_returnsCorrectProduct() {
        assertEquals("5 * (-4) should be -20", -20, calculator.multiply(5, -4));
    }


    //--- Test cho hàm divide ---
    @Test
    public void divide_validInputs_returnsCorrectQuotient() {
        // Khi so sánh float/double, cần có một delta (sai số cho phép)
        assertEquals("20 / 4 should be 5.0", 5.0f, calculator.divide(20, 4), 0.0001f);
    }

    @Test
    public void divide_resultsInFraction_returnsCorrectQuotient() {
        assertEquals("5 / 2 should be 2.5", 2.5f, calculator.divide(5, 2), 0.0001f);
    }

    @Test(expected = IllegalArgumentException.class) // JUnit 4 cách kiểm tra exception
    public void divide_byZero_throwsIllegalArgumentException() {
        calculator.divide(10, 0);
        // Nếu không có exception, test này sẽ fail
    }

    // Cách khác để test exception (linh hoạt hơn nếu muốn kiểm tra message)
    @Test
    public void divide_byZero_throwsExceptionWithMessage() {
        try {
            calculator.divide(10, 0);
            fail("Expected an IllegalArgumentException to be thrown"); // Nếu không ném lỗi, fail test
        } catch (IllegalArgumentException e) {
            assertEquals("Thông báo lỗi phải khớp", "Cannot divide by zero", e.getMessage());
        }
    }

    //--- Test cho hàm findUCLN ---
    @Test
    public void findUCLN_twoPositiveNumbers_returnsCorrectUCLN() {
        assertEquals("UCLN(54, 24) should be 6", 6, calculator.findUCLN(54, 24));
        assertEquals("UCLN(24, 54) should be 6", 6, calculator.findUCLN(24, 54));
    }

    @Test
    public void findUCLN_oneNumberIsMultipleOfOther_returnsSmallerNumber() {
        assertEquals("UCLN(12, 36) should be 12", 12, calculator.findUCLN(12, 36));
    }

    @Test
    public void findUCLN_primeNumbers_returnsOne() {
        assertEquals("UCLN(7, 13) should be 1", 1, calculator.findUCLN(7, 13));
    }

    @Test
    public void findUCLN_withOneAsInput_returnsOne() {
        assertEquals("UCLN(1, 100) should be 1", 1, calculator.findUCLN(1, 100));
    }

    @Test
    public void findUCLN_sameNumbers_returnsNumberItself() {
        assertEquals("UCLN(15, 15) should be 15", 15, calculator.findUCLN(15, 15));
    }

    @Test
    public void findUCLN_withZeroInputs_shouldBeHandled() {
        // Dựa trên logic hiện tại của findUCLN trong CalculationLogic:
        // findUCLN(0, 5) -> 5
        // findUCLN(5, 0) -> 5
        // findUCLN(0, 0) -> 0
        assertEquals("UCLN(0, 5) should be 5 (abs value)", 5, calculator.findUCLN(0, 5));
        assertEquals("UCLN(5, 0) should be 5 (abs value)", 5, calculator.findUCLN(5, 0));
        assertEquals("UCLN(0, 0) should be 0", 0, calculator.findUCLN(0, 0));
    }

    @Test
    public void findUCLN_withNegativeInputs_shouldUseAbsoluteValues() {
        // UCLN(-54, 24) = UCLN(54, 24) = 6
        // UCLN(54, -24) = UCLN(54, 24) = 6
        // UCLN(-54, -24) = UCLN(54, 24) = 6
        assertEquals("UCLN(-54, 24) should be 6", 6, calculator.findUCLN(-54, 24));
        assertEquals("UCLN(54, -24) should be 6", 6, calculator.findUCLN(54, -24));
        assertEquals("UCLN(-54, -24) should be 6", 6, calculator.findUCLN(-54, -24));
    }

    // Nếu bạn quyết định findUCLN ném lỗi cho đầu vào <= 0:
    // @Test(expected = IllegalArgumentException.class)
    // public void findUCLN_withNegativeInput_throwsIllegalArgumentException() {
    //     calculator.findUCLN(-10, 5);
    // }
    //
    // @Test(expected = IllegalArgumentException.class)
    // public void findUCLN_withZeroInput_throwsIllegalArgumentException() {
    //     calculator.findUCLN(0, 5);
    // }
}