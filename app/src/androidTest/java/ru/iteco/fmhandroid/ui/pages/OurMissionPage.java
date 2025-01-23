package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;

public class OurMissionPage {
    public TimeoutEspresso.TimedViewInteraction appBarPanel =
            onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_our_mission));
    public TimeoutEspresso.TimedViewInteraction title =
            onViewWithTimeout(withId(R.id.our_mission_title_text_view));
    public TimeoutEspresso.TimedViewInteraction quotesList =
            onViewWithTimeout(15000, withId(R.id.our_mission_item_list_recycler_view));


    public void clickOnQuote(int num) {
        int number = num -1;
        Allure.step("Клик по " + num + " цитате в списке");
        onViewWithTimeout(childAtPosition(withId(R.id.our_mission_item_list_recycler_view), number))
                .performWithTimeout(click());
    }

    public TimeoutEspresso.TimedViewInteraction descriptionOfQuote(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(withId(R.id.our_mission_item_description_text_view),
                withParent(withParent(childAtPosition(withId(R.id.our_mission_item_list_recycler_view), number)))));
    }

    public TimeoutEspresso.TimedViewInteraction iconOfQuote(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(withId(R.id.our_mission_item_image_view),
                withParent(withParent(childAtPosition(withId(R.id.our_mission_item_list_recycler_view), number)))));
    }

    public TimeoutEspresso.TimedViewInteraction dropButtonOfQuote(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(withId(R.id.our_mission_item_open_card_image_button),
                withParent(withParent(childAtPosition(withId(R.id.our_mission_item_list_recycler_view), number)))));
    }

    public TimeoutEspresso.TimedViewInteraction titleOfQuote(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(withId(R.id.our_mission_item_title_text_view),
                withParent(withParent(childAtPosition(withId(R.id.our_mission_item_list_recycler_view), number)))));
    }
}
