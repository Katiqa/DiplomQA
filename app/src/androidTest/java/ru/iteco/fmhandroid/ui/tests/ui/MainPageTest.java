package ru.iteco.fmhandroid.ui.tests.ui;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.matchers.CustomViewMatcher.recyclerViewMatcher;

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
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.steps.Authorization;
import ru.iteco.fmhandroid.ui.steps.NewsActions;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private Authorization auth = new Authorization();
    private MainPage mainPage;

    @Before
    public void setUp() {
        auth.tryLogIn();
        mainPage = new MainPage();
    }


    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "AppBar на главной странице")
    @Test
    @Description(value = "Тест проверяет отображение AppBar панели на главной странице")
    public void shouldCheckAppBarOnMainIsDisplayed() {
        mainPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "Лого AppBar на главной странице")
    @Test
    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на главной странице")
    public void shouldCheckAppBarLogoOnMainIsDisplayed() {
        new AppBarPanel().mainImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "\"Главная\" страница")
    @Story(value = "Блок новостей на главной странице")
    @Test
    @Description(value = "Тест проверяет отображение блока новостей на главной странице")
    public void shouldCheckNewsContainerIsDisplayed() {
        mainPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "\"Главная\" страница")
    @Story(value = "Заголовок блока новостей")
    @Test
    @Description(value = "Тест проверяет отображение заголовка блока новостей на главной странице")
    public void shouldCheckNewsTitleIsDisplayed() {
        mainPage.newsContainerTitle.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "\"Главная\" страница")
    @Story(value = "Кнопка скрытия списка новостей")
    @Test
    @Description(value = "Тест проверяет отображение кнопки скрытия блока новостей на главной странице")
    public void shouldCheckDropButtonIsDisplayed() {
        mainPage.dropButton.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "\"Главная\" страница")
    @Story(value = "Кнопка \"Все новости\"")
    @Test
    @Description(value = "Тест проверяет отображение кнопки \"Все новости\" на главной странице")
    public void shouldCheckAllNewsIsDisplayed() {
        String allNewsButton = "Все новости";

        mainPage.allNewsButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(allNewsButton)));
    }
}


