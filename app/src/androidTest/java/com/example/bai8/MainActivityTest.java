package com.example.bai8;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAddition() {
        onView(withId(R.id.num1)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(typeText("3"), closeSoftKeyboard());
        onView(withId(R.id.btnTong)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Kết quả là 8")));
    }

    @Test
    public void testSubtraction() {
        onView(withId(R.id.num1)).perform(replaceText("9"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText("4"), closeSoftKeyboard());
        onView(withId(R.id.btnHieu)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Kết quả là 5")));
    }

    @Test
    public void testMultiplication() {
        onView(withId(R.id.num1)).perform(replaceText("6"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText("7"), closeSoftKeyboard());
        onView(withId(R.id.btnTich)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Kết quả là 42")));
    }

    @Test
    public void testDivision() {
        onView(withId(R.id.num1)).perform(replaceText("8"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText("2"), closeSoftKeyboard());
        onView(withId(R.id.btnThuong)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Kết quả là 4.0")));
    }

    @Test
    public void testDivisionByZero() {
        onView(withId(R.id.num1)).perform(replaceText("10"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText("0"), closeSoftKeyboard());
        onView(withId(R.id.btnThuong)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Cannot divide by zero")));
    }

    @Test
    public void testUCLN() {
        onView(withId(R.id.num1)).perform(replaceText("36"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText("60"), closeSoftKeyboard());
        onView(withId(R.id.btnUCLN)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Kết quả là 12")));
    }

    @Test
    public void testUCLNInvalidInput() {
        onView(withId(R.id.num1)).perform(replaceText("-5"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText("15"), closeSoftKeyboard());
        onView(withId(R.id.btnUCLN)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("UCLN yêu cầu số dương")));
    }

    @Test
    public void testEmptyInput() {
        onView(withId(R.id.num1)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.btnTong)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Vui lòng nhập đủ hai số")));
    }

    @Test
    public void testInvalidNumberInput() {
        onView(withId(R.id.num1)).perform(replaceText("abc"), closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(replaceText("123"), closeSoftKeyboard());
        onView(withId(R.id.btnTong)).perform(click());
        onView(withId(R.id.kq)).check(matches(withText("Đầu vào không hợp lệ (không phải số)")));
    }
}
