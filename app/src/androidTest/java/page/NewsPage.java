package page;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import static kotlinx.coroutines.flow.FlowKt.withIndex;
import static page.ChildAtPosition.childAtPosition;

import android.view.View;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPage {


    int allNews = R.id.all_news_text_view;
    int filterButton = R.id.filter_news_material_button;
    int categoryField = com.google.android.material.R.id.text_input_end_icon;
    int filterButtonSort = R.id.filter_button;
    int titleFieldNews = R.id.news_item_title_text_view;
    int editingNews = R.id.edit_news_material_button;
    int addNews = R.id.add_news_image_view;
    int categoryFieldNews = com.google.android.material.R.id.text_input_end_icon;
    int dataFieldNews = R.id.news_item_publish_date_text_input_edit_text;
    int okChoiсeButton = android.R.id.button1;
    int timeFieldNews = R.id.news_item_publish_time_text_input_edit_text;
    int descriptionField = R.id.news_item_description_text_input_edit_text;
    int saveNewButton = R.id.save_button;
    int viewingNew = R.id.news_item_material_card_view;
    int descriptionFieldNews = R.id.news_item_description_text_view;


    public void clickAllNews() {
        Allure.step("Нажимаем на кнопку All News");
        onView(withId(allNews)).perform(click());
    }

    public void clickFilterButton() {
        Allure.step("Нажимаем на кнопку Фильтры");
        onView(withId(filterButton)).perform(click());
    }

    public void chooseNewsCategory(String heading) {
        Allure.step("Выбираем необходимую категорию");

        onView(
                allOf(withId(categoryField), withContentDescription("Show dropdown menu")))
                .perform(click());

        onView(withText(heading))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

    }

    public void clickFilterButtonSort() {
        Allure.step("Нажимаем на кнопку Filter");
        onView(withId(filterButtonSort)).perform(click());

    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public void searchNewsCategory(String title, int position) {
        Allure.step("Выбранная категория отобразилась");

        onView(
                allOf(withIndex(withId(titleFieldNews), position),
                        isDisplayed()))
                .check(matches(withText(title)));
    }

    public void clickEditingNews() {
        Allure.step("Нажимаем на кнопку Редактирование новостей");
        onView(withId(editingNews)).perform(click());
    }

    public void clickAddNews() {

        Allure.step("Нажимаем на кнопку Добавить новость");
        onView(withId(addNews)).perform(click());
    }

    public void choiceCategory(String heading) {
        Allure.step("Выбираем необходимую категорию");
        onView(allOf(withId(categoryFieldNews), withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText(heading))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }

    public void addDataСorrect() {
        Allure.step("Выбираем корректную дату");
        onView((withId(dataFieldNews))).perform(click());
        onView((withId(okChoiсeButton))).perform(click());
    }

    public void addTimeСorrect() {
        Allure.step("Выбираем корректное время");
        onView((withId(timeFieldNews))).perform(click());
        onView((withId(okChoiсeButton))).perform(click());
    }

    public void addDescription(String description) {
        Allure.step("Вводим описание новости");
        onView(withId(descriptionField))
                .perform(replaceText(description), closeSoftKeyboard());
    }

    public void saveNew() {
        Allure.step("Нажимаем Save");
        onView((withId(saveNewButton))).perform(scrollTo(), click());
    }

    public void searchAddNew(String description, int position) {
        Allure.step("Добавленная новость найдена");
        onView(withIndex(withId(viewingNew), position)).check(matches(isDisplayed()));
        onView(allOf(withId(descriptionFieldNews), withText(description)));
    }

    public void addTimeIncorrect() {
        Allure.step("Ввод некорректного времени");
        onView((withId(timeFieldNews))).perform(click());
        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.TextInputTimePickerView")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("88"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());
    }

    public void checkErrorMessageText() {
        Allure.step("Появляется ошибка  " + "Enter a valid time");
        ViewInteraction textView = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Enter a valid time"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Enter a valid time")));
    }
}
