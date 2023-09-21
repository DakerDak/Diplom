package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static page.ChildAtPosition.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class QuotesPage {
    int quotesButton = R.id.our_mission_image_button;
    int fieldQuotesText = R.id.our_mission_item_description_text_view;

    public void clickQuotesButton() {
        Allure.step("Нажимает на кнопку Бабочка");
        onView(withId(quotesButton)).perform(click());

    }

    public void clickExpandQuote() {
        Allure.step("Выбираем цитату нажимаем на стрелку вниз");
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.our_mission_item_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    public void checkTextQuote() {
        Allure.step("Текст цитаты разворачивается");
        onView(allOf(withId(fieldQuotesText), isDisplayed()));
    }
}
