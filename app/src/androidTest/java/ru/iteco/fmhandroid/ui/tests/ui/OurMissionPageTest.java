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
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.OurMissionPage;
import ru.iteco.fmhandroid.ui.steps.Authorization;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class OurMissionPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private Authorization auth = new Authorization();
    private OurMissionPage ourMissionPage;

    @Before
    public void setUp() {
        auth.tryLogIn();
        ourMissionPage = new OpenPage().ourMission();
    }


    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "AppBar на странице \"Тематические цитаты\"")
    @Test
    @Description(value = "Тест проверяет отображение AppBar панели на странице \"Тематические цитаты\"")
    public void shouldCheckAppBarOnOurMissionIsDisplayed() {
        ourMissionPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "Лого AppBar на странице \"Тематические цитаты\"")
    @Test
    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на странице \"Тематические цитаты\"")
    public void shouldCheckAppBarLogoOnOurMissionIsDisplayed() {
        new AppBarPanel().mainImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Тематические цитаты\"")
    @Story(value = "Заголовок")
    @Test
    @Description(value = "Тест проверяет отображение заголовка страницы тематических цитат")
    public void shouldCheckOurMissionTitleIsDisplayed() {
        String ourMissionTitle = "Главное - жить любя";

        ourMissionPage.title.checkWithTimeout(matches(isDisplayed()))
               .check(matches(withText(ourMissionTitle)));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Тематические цитаты\"")
    @Story(value = "Список цитат")
    @Test
    @Description(value = "Тест проверяет отображение списка цитат странице тематических цитат")
    public void shouldCheckQuotesListIsDisplayed() {
        ourMissionPage.quotesList.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Тематические цитаты\"")
    @Story(value = "Количество цитат в списке")
    @Test
    @Description(value = "Тест проверяет количество цитат на странице тематических цитат")
    public void shouldCheckNumberOfQuotes() {
        int quotesCount = 8;

        ourMissionPage.quotesList.checkWithTimeout(matches(recyclerViewMatcher(quotesCount)));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Тематические цитаты\"")
    @Story(value = "Отображение элементов цитаты")
    @Test
    @Description(value = "Тест проверяет отображение элементов первой в списке цитаты")
    public void shouldCheckFirstQuoteElementsIsDisplayed() {
        int numberOfQuote = 1;

        ourMissionPage.titleOfQuote(numberOfQuote).checkWithTimeout(matches(isDisplayed()));
        ourMissionPage.iconOfQuote(numberOfQuote).checkWithTimeout(matches(isDisplayed()));
        ourMissionPage.dropButtonOfQuote(numberOfQuote).checkWithTimeout(matches(isDisplayed()));
        ourMissionPage.descriptionOfQuote(numberOfQuote).checkWithTimeout(matches(not(isDisplayed())));

        ourMissionPage.clickOnQuote(numberOfQuote);
        ourMissionPage.descriptionOfQuote(numberOfQuote).checkWithTimeout(matches(isDisplayed()));

    }
}
