package ru.iteco.fmhandroid.ui.matchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;

public class CustomViewAction {
    public static void returnBack() {
        Allure.step("Клик по кнопке кнопке \"Назад\" устройства");
        onView(isRoot()).perform(ViewActions.pressBack());
    }


    public static ViewAction clickOnViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View newView = view.findViewById(id);
                newView.performClick();
            }
        };
    }



    // To pause execution
    public static void stopExecutionForSeconds(final int seconds) {
        onView(isRoot()).perform(loopMainAtSeconds(seconds));
    }
    public static ViewAction loopMainAtSeconds(final int sec) {
        long milliseconds = sec * 1000;
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(milliseconds);
            }
        };
    }
}
