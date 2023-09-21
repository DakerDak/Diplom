package page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static io.qameta.allure.kotlin.Allure.step;
import static page.ChildAtPosition.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.core.IsInstanceOf;


import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ClaimsPage {
    int allClaims = R.id.all_claims_text_view;
    int filterClaimListMenu = R.id.filters_material_button;
    int filterIn_progress = R.id.item_filter_in_progress;
    int addNewClaims = R.id.add_new_claim_material_button;
    int titleField = R.id.title_edit_text;
    int dateField = R.id.date_in_plan_text_input_edit_text;
    int timeField = R.id.time_in_plan_text_input_edit_text;
    int okButtonDataTime = android.R.id.button1;
    int descriptionField = R.id.description_edit_text;
    int saveButton = R.id.save_button;

    int claimsList = R.id.claim_list_recycler_view;
    String errorMessageTextInvalidTime = "Enter a valid time";

    //
    public void clickAllClaims() {
        Allure.step("Нажимаем на кнопку All Claims");
        onView((withId(allClaims))).perform(click());
    }

    public void clickFilterClaimList() {
        Allure.step("Нажимаем на кнопку Фильтры");
        onView((withId(filterClaimListMenu))).perform(click());
    }

    public void checkFilterInProgress() {
        Allure.step("Выбираем необходимый фильтр");
        onView(allOf(withId(filterIn_progress), withText("In progress"),
                childAtPosition(childAtPosition(
                        withClassName(is("android.widget.ScrollView")),
                        0), 2)))
                .perform(scrollTo(), click());
    }

    public void clickOk() {
        Allure.step("Нажимаем Ок");
        onView((withId(R.id.claim_list_filter_ok_material_button))).perform(click());
    }

    public void clickRevealClaims() {
        Allure.step("Расскрываем новости");
        onView(allOf(withId(R.id.claim_list_recycler_view),
                childAtPosition(
                        withId(R.id.all_claims_cards_block_constraint_layout),
                        4))).perform(actionOnItemAtPosition(0, click()));
    }

    public void checkStatusClaims() {
        Allure.step("Проверяем статус Новостей");
        onView((withId(R.id.status_label_text_view))).check(matches(isDisplayed()));
    }

    public void clickAddNewClaims() {
        Allure.step("Нажимаем на кнопку добавить претензию");
        onView((withId(addNewClaims))).perform(click());
    }

    public void clickTitleField() {
        Allure.step("Нажимаем на кнопку Title");
        onView((withId(titleField))).perform(click());
    }


    public void inputTextInTitle(String titleText) {
        Allure.step("В поле Title вводим наименовании претензии");
        onView(
                allOf(withId(titleField),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                0),
                        isDisplayed()))
                .perform(replaceText(titleText), closeSoftKeyboard());
    }

    public void clickChooseAuthor() {
        Allure.step("Нажимаем на кнопку выбрать автора");
        onView((withId(R.id.executor_drop_menu_auto_complete_text_view))).perform(click());
    }

    public void chooseAuthor() {
        Allure.step("Выбираем автора");
        onView(withText("Ivanov Ivan Ivanovich"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }

    public void addChooseCorrectDate() {
        Allure.step("Выбираетм корректную дату");
        onView((withId(dateField))).perform(click());
        onView((withId(okButtonDataTime))).perform(click());
    }

    public void addRightTime() {
        Allure.step("Выбираетм корректное время");
        onView((withId(timeField))).perform(click());
        onView((withId(okButtonDataTime))).perform(click());
    }

    public void addIncorrectTime() {
        Allure.step("Выбираем некорректное время");
        onView((withId(timeField))).perform(click());

        onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")),
                        isDisplayed()))
                .perform(click());


        onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.TextInputTimePickerView")),
                                                1)),
                                0),
                        isDisplayed()))
                .perform(replaceText("99"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());
    }

    public void addDescriptionClaims(String description) {
        Allure.step("Вводим описание претензии");
        onView(withId(descriptionField))
                .perform(replaceText(description), closeSoftKeyboard());
    }

    public void saveButton() {
        Allure.step("Нажимаем Save");
        onView((withId(saveButton))).perform(scrollTo(), click());

    }

    public void searchClaim(String titleText) {
        Allure.step("Добавленная претензии найдена");
        onView(ViewMatchers.withId(claimsList))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(titleText)))).check(matches(isDisplayed()));
    }

    public void checkErrorMessageText() {
        Allure.step("Появляется ошибка  " + errorMessageTextInvalidTime);
        onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Enter a valid time"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()))
                .check(matches(withText(errorMessageTextInvalidTime)));

    }

}
