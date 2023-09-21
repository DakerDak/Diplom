package ru.iteco.fmhandroid.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import page.Authorization;
import page.Geteri;
import page.MainPage;
import page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Раздел News")
public class NewsTest {

    String heading = "Объявление";
    NewsPage newsPage = new NewsPage();
    Authorization authorization = new Authorization();
    Geteri geteri = new Geteri();
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
    @DisplayName("Проверка фильтра раздел Новости")
    public void filterNewsTest() {
        authorization.WaitForLoading();
        newsPage.clickAllNews();
        newsPage.clickFilterButton();
        newsPage.chooseNewsCategory(heading);
        newsPage.clickFilterButtonSort();
        newsPage.searchNewsCategory(heading, 0);

    }

    @Test
    @DisplayName("Добавление новости")
    public void addNewsTest() {
        String description = geteri.getRandomText();
        authorization.WaitForLoading();
        newsPage.clickAllNews();
        newsPage.clickEditingNews();
        newsPage.clickAddNews();
        newsPage.choiceCategory(heading);
        newsPage.addDataСorrect();
        newsPage.addTimeСorrect();
        newsPage.addDescription(description);
        newsPage.saveNew();
        newsPage.searchAddNew(description, 0);
    }

    @Test
    @DisplayName("Добавление новости с некорректным временем")
    public void addNewsIncorrectTime() {
        authorization.WaitForLoading();
        newsPage.clickAllNews();
        newsPage.clickEditingNews();
        newsPage.clickAddNews();
        newsPage.addTimeIncorrect();
        newsPage.checkErrorMessageText();
    }

}
