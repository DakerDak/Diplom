package page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;


import static page.ChildAtPosition.childAtPosition;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class Authorization {
    int loginField = R.id.login_text_input_layout;
    int passwordField = R.id.password_text_input_layout;
    int signButton = R.id.enter_button;
    String errorMessageDataWrong = "Wrong login or password";
    String emptyMessageOutputData = "Login and password cannot be empty";


    public void WaitForLoading() {
        long endTime = (System.currentTimeMillis() + 8000);
        while (System.currentTimeMillis() < endTime) ;
    }

    public void clickLoginField() {
        Allure.step("Кликаем на поле Логин");
        onView((withId(loginField))).perform(click());
    }

    public void clickPasswordField() {
        Allure.step("Кликаем на поле Пароль");
        onView((withId(passwordField))).perform(click());
    }


    public void enterTextLoginField() {
        Allure.step("Вводим в поле Логин - валидный логин");
        onView(allOf(childAtPosition(childAtPosition(withId(loginField), 0), 0),
                isDisplayed()))
                .perform(replaceText("login2"), closeSoftKeyboard());
    }

    public void enterTextPasswordField() {
        Allure.step("Вводим в поле Пароль - валидный пароль");
        onView(allOf(childAtPosition(childAtPosition(withId(passwordField),
                                0),
                        0),
                isDisplayed()))
                .perform(replaceText("password2"), closeSoftKeyboard());
    }

    public void clickSingIn() {
        Allure.step("Нажимаетм SingIn");
        onView((withId(signButton))).perform(click());
    }

    public void enterErrorTextPasswordField() {
        Allure.step("Вводим в поле Пароль - не валидный пароль");
        onView(allOf(childAtPosition(childAtPosition(withId(passwordField),
                                0),
                        0),
                isDisplayed()))
                .perform(replaceText("pass123"), closeSoftKeyboard());
    }

    public void errorOutputData() {
        Allure.step("Появляется ошибка  " + errorMessageDataWrong);
        onView(allOf(withContentDescription(errorMessageDataWrong), isDisplayed()));
    }

    public void enterErrorTextLoginField() {
        Allure.step("Вводим в поле Логин - невалидный логин");
        onView(allOf(childAtPosition(childAtPosition(withId(loginField), 0), 0),
                isDisplayed()))
                .perform(replaceText("log123"), closeSoftKeyboard());
    }

    public void emptyOutputDataMessage() {
        Allure.step("Появляется ошибка  " + emptyMessageOutputData);
        onView(allOf(withContentDescription(emptyMessageOutputData), isDisplayed()));
    }
}



