package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import page.Authorization;
import page.ClaimsPage;
import page.Geteri;
import page.MainPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic("Раздел Сlaims")
public class ClaimsTest {
    Authorization authorization = new Authorization();
    ClaimsPage claimsPage = new ClaimsPage();
    Geteri geteri = new Geteri();
    MainPage mainPage = new MainPage();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void testAuthorization() {
        try {authorization.WaitForLoading();
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
    @DisplayName("Фильтр Претензии")
    public void filterTest() {
        authorization.WaitForLoading();
        claimsPage.clickAllClaims();
        claimsPage.clickFilterClaimList();
        claimsPage.checkFilterInProgress();
        claimsPage.clickOk();
        authorization.WaitForLoading();
        claimsPage.clickRevealClaims();
        authorization.WaitForLoading();
        claimsPage.checkStatusClaims();
    }

    @Test
    @DisplayName("Добавление претензии с текущим временем и датой")
    public void addNewClaimsTest() {
        String description = geteri.getRandomText();
        String titleText = geteri.getRandomText();
        authorization.WaitForLoading();
        claimsPage.clickAddNewClaims();
        claimsPage.clickTitleField();
        claimsPage.inputTextInTitle(titleText);
        claimsPage.clickChooseAuthor();
        claimsPage.chooseAuthor();
        claimsPage.addChooseCorrectDate();
        claimsPage.addRightTime();
        claimsPage.addDescriptionClaims(description);
        claimsPage.saveButton();
        claimsPage.clickAllClaims();
        claimsPage.searchClaim(titleText);

    }

    @Test
    @DisplayName("Добавление претензии с некоррктным временем")
    public void addNewClaimsIncorrectTime() {
        authorization.WaitForLoading();
        claimsPage.clickAddNewClaims();
        claimsPage.addIncorrectTime();
        claimsPage.checkErrorMessageText();




    }
}

