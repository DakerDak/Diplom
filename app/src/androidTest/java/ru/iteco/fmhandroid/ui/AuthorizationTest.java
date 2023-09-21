package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Console;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import page.Authorization;
import page.MainPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Авторизация пользователя по логину и паролю")
public class AuthorizationTest {
    Authorization authorization = new Authorization();
    MainPage mainPage = new MainPage();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void testLogOut() {

        authorization.WaitForLoading();

        try {
            mainPage.clickExitAuthorization();
            mainPage.clickLogOut();
        } catch (Exception es) {

        }
    }

    @Test
    @DisplayName("Авторизация пользователя с корректными данными")
    public void authorizationCorrectDataTest() {
        authorization.WaitForLoading();
        authorization.clickLoginField();
        authorization.enterTextLoginField();
        authorization.clickPasswordField();
        authorization.enterTextPasswordField();
        authorization.clickSingIn();
        authorization.WaitForLoading();
        mainPage.authorizationExit();

    }


    @Test
    @DisplayName("Авторизация пользователя с неверным паролем")
    public void authorizationInvalidPasswordTest() {
        authorization.WaitForLoading();
        authorization.clickLoginField();
        authorization.enterTextLoginField();
        authorization.clickPasswordField();
        authorization.enterErrorTextPasswordField();
        authorization.clickSingIn();
        authorization.errorOutputData();

    }

    @Test
    @DisplayName("Авторизация пользователя с неверным логином")
    public void authorizationInvalidLoginTest() {
        authorization.WaitForLoading();
        authorization.clickLoginField();
        authorization.enterErrorTextLoginField();
        authorization.clickPasswordField();
        authorization.enterTextPasswordField();
        authorization.clickSingIn();
        authorization.errorOutputData();
    }

    @Test
    @DisplayName("Ввод несколько раз неверного пароля")
    public void authorizationWrongPasswordSeveralTimes() {
        authorization.WaitForLoading();
        authorization.clickLoginField();
        authorization.enterTextLoginField();
        authorization.clickPasswordField();
        authorization.enterErrorTextPasswordField();

        for (int i = 0; i < 5; i++) {
            authorization.clickSingIn();
        }
        authorization.errorOutputData();

    }

    @Test
    @DisplayName("Поле Пароль незаполнено")
    public void authorizationEmptyPasswordTest() {
        authorization.WaitForLoading();
        authorization.clickPasswordField();
        authorization.enterTextPasswordField();
        authorization.clickSingIn();
        authorization.emptyOutputDataMessage();
    }

    @Test
    @DisplayName("Поле Логин незаполнено")
    public void authorizationEmptyLoginTest() {
        authorization.WaitForLoading();
        authorization.clickLoginField();
        authorization.enterTextLoginField();
        authorization.clickSingIn();
        authorization.emptyOutputDataMessage();
    }

    @Test
    @DisplayName("Пустые логин и пароль")
    public void authorizationEmptyPasswordLoginTest() {
        authorization.WaitForLoading();
        authorization.clickSingIn();
        authorization.emptyOutputDataMessage();
    }

    @Test
    @DisplayName("Разлогин")
    public void logOutTest() {

        authorization.WaitForLoading();
        authorization.clickLoginField();
        authorization.enterTextLoginField();
        authorization.clickPasswordField();
        authorization.enterTextPasswordField();
        authorization.clickSingIn();
        authorization.WaitForLoading();
        mainPage.clickExitAuthorization();
        mainPage.clickLogOut();

    }

}
