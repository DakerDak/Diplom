package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
    int buttonLogOut = R.id.authorization_image_button;
    int logOut = android.R.id.title;

    public void authorizationExit() {
        Allure.step("Загрузилась страница с иконкой Разлогин");
        onView((withId(buttonLogOut))).check(matches(isDisplayed()));
    }
    public void clickExitAuthorization() {
        Allure.step("Нажимаем на кнопку Разлогина");
        onView((withId(buttonLogOut))).perform(click());
    }

    public void clickLogOut() {
        Allure.step("Нажимаеm на Log out");
        onView((withId(logOut))).perform(click());
    }
}
