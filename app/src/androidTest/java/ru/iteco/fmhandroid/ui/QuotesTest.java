package ru.iteco.fmhandroid.ui;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import page.Authorization;
import page.MainPage;
import page.QuotesPage;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Раздел Цитаты")
public class QuotesTest {

    QuotesPage quotesPage = new QuotesPage();
    Authorization authorization = new Authorization();
    MainPage mainPage = new MainPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void testAuthorization() {
        try {
            authorization.WaitForLoading();
            authorization.clickLoginField();
            authorization.enterTextLoginField();
            authorization.clickPasswordField();
            authorization.enterTextPasswordField();
            authorization.clickSingIn();
            authorization.WaitForLoading();
            mainPage.authorizationExit();

        } catch (Exception e) {

        }
    }


    @Test
    @DisplayName("Развертывания цитаты")
    public void deploymentQuotes() {
        authorization.WaitForLoading();
        quotesPage.clickQuotesButton();
        quotesPage.clickExpandQuote();
        quotesPage.checkTextQuote();

    }


}
