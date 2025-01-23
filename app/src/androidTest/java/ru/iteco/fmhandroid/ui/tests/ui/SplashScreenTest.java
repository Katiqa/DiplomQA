package ru.iteco.fmhandroid.ui.tests.ui;

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
import ru.iteco.fmhandroid.ui.pages.SplashScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class SplashScreenTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");

    private SplashScreen splashScreen;

    @Before
    public void setUp() {
        splashScreen = new SplashScreen();
    }


    @Epic(value = "Тестирование UI")
    @Feature(value = "Экран загрузки Splash Screen")
    @Story(value = "Изображение")
    @Test
    @Description(value = "Тест проверяет отображение изображения")
    public void shouldCheckSplashScreenImageIsDisplayed() {
        splashScreen.welcomeImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Экран загрузки Splash Screen")
    @Story(value = "Индикатор загрузки")
    @Test
    @Description(value = "Тест проверяет отображение индикатора загрузки")
    public void shouldCheckSplashScreenProgressIndicatorIsDisplayed() {
        splashScreen.progressIndicator.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Экран загрузки Splash Screen")
    @Story(value = "Текст")
    @Test
    @Description(value = "Тест проверяет отображение приветственной цитаты")
    public void shouldCheckSplashScreenTextViewIsDisplayed() {
        splashScreen.welcomeText.checkWithTimeout(matches(isDisplayed()));
    }
}
