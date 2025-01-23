package ru.iteco.fmhandroid.ui.tests.functional;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.matchers.ToastMatcher;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.steps.Authorization;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotOnFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private Authorization authorization = new Authorization();
    private AuthorizationPage authPage;

    @Before
    public void initialize() {
        authorization.tryLogOut();
        authPage = new AuthorizationPage();
    }


    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Невалидная авторизация")
    @Test(expected = NullPointerException.class)
    @Description(value = "Тест проверяет, что отображается сообщение и вход в приложение не происходит, если поля логина и пароля пустые")
    public void testToastMessageDisplayedWithEmptyFields() {
        String expectedToastMessage = " Логин и пароль не могут быть пустыми ";
        // authPage.clickOnEnterButton();
        new Authorization().loginEmptyUser();

        ToastMatcher.checkToastMessageIsDisplayed(expectedToastMessage);
        authPage.title.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Невалидная авторизация. Негативный тест, ожидающий исключение 'NullPointerException.class'.")
    @Test(expected = NullPointerException.class)
    @Description(value = "Тест проверяет, что отображается сообщение и вход в приложение не происходит при использовании невалидных данных")
    public void testToastMessageDisplayedForInvalidLogin() {
        String expectedToastMessage = "Не правильный логин или пароль";
        new Authorization().invalidLogin();

        ToastMatcher.checkToastMessageIsDisplayed(expectedToastMessage);
        authPage.title.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Валидная авторизация")
    @Test
    @Description(value = "Тест проверяет успешный вход в приложение с корректными данными")
    public void testValidAuthorization() {
        new Authorization().validLogin();

        new MainPage().appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Выход из аккаунта")
    @Test
    @Description(value = "Тест проверяет процесс выхода из аккаунта приложения")
    public void testCancelAuthorization() {
        new Authorization().validLogin();
        AppBarPanel appBarPanel = new AppBarPanel();

        appBarPanel.clickOnAuthButton();
        appBarPanel.clickOnLogOutButton();
        authPage.title.checkWithTimeout(matches(isDisplayed()));
    }
}