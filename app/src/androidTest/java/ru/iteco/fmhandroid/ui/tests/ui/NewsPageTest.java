package ru.iteco.fmhandroid.ui.tests.ui;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

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
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage.FilterForm;
import ru.iteco.fmhandroid.ui.steps.Authorization;
import ru.iteco.fmhandroid.ui.steps.NewsActions;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private Authorization auth = new Authorization();
    private NewsPage newsPage;
    private ControlPanelPage controlPanelPage;
    private FilterForm filterForm;
    @Before
    public void setUp() {
        auth.tryLogIn();
        newsPage = new OpenPage().news();
        controlPanelPage = new ControlPanelPage();
        filterForm = newsPage.new FilterForm();
    }


    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "AppBar на странице \"Новости\"")
    @Test
    @Description(value = "Тест проверяет отображение AppBar панели на странице \"Новости\"")
    public void shouldCheckAppBarOnNewsIsDisplayed() {
        newsPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "Лого AppBar на странице \"Новости\"")
    @Test
    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на странице \"Новости\"")
    public void shouldCheckAppBarLogoOnNewsIsDisplayed() {
        new AppBarPanel().mainImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Заголовок")
    @Test
    @Description(value = "Тест проверяет отображение заголовка страницы новостей")
    public void shouldCheckNewsTitleIsDisplayed() {
        newsPage.title.checkWithTimeout(matches(isDisplayed()));
    }


    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Список новостей")
    @Test
    @Description(value = "Тест проверяет отображение списка новостей страницы новости")
    public void shouldCheckNewsListIsDisplayed() {
        NewsActions newsActions = new NewsActions();
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);
        newsActions.addNewsFirst(newsTitle);

        newsPage.newsList.checkWithTimeout(matches(isDisplayed()));

        newsActions.deleteNewsWithTitle(newsTitle);
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Кнопки управления")
    @Test
    @Description(value = "Тест проверяет отображение кнопок управления новостями")
    public void shouldCheckControlButtonsIsDisplayed() {
        newsPage.sortButton.checkWithTimeout(matches(isDisplayed()));
        newsPage.filterButton.checkWithTimeout(matches(isDisplayed()));
        newsPage.editButton.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Отображение элементов новости")
    @Test()
    @Description(value = "Тест проверяет отображение элементов новости")
    public void shouldCheckNewNewsElementsIsDisplayed() {
        NewsActions newsActions = new NewsActions();

        String category = "Зарплата";
        String title = DataGenerator.RandomString.getRandomRuString(5);
        String date = DataGenerator.getCurrentDate();
        String time = DataGenerator.getCurrentTime();
        String description = DataGenerator.RandomString.getRandomRuString(10);

        newsActions.addNews(category, title, date, time, description);

        controlPanelPage.scrollToNewsWithTitle(title);
        controlPanelPage.newsWithTitle(title).checkWithTimeout(matches(isDisplayed()));
        newsPage.iconCategoryNewsWithTitle(title).checkWithTimeout(matches(isDisplayed()));
        newsPage.dropButtonNewsWithTitle(title).checkWithTimeout(matches(isDisplayed()));
        newsPage.descriptionNewsWithTitle(title).checkWithTimeout(matches(not(isDisplayed())));

        newsActions.deleteNewsWithTitle(title);
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы фильтра")
    public void shouldCheckFilterFormElementsIsDisplayed() {
        String formTitle = "Фильтровать новости";
        String categoryHint = "Категория";
        String dateFormat = "ДД.ММ.ГГГГ";
        String acceptButton = "Фильтровать";
        String cancelButton = "Отмена";

        newsPage.clickOnFilterButton();

        filterForm.title.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(formTitle)));
        filterForm.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryHint)));
        filterForm.dateStartField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        filterForm.dateEndField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        filterForm.acceptButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(acceptButton)));
        filterForm.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
    }
    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение категорий формы фильтра")
    public void shouldCheckFilterFormCategoriesIsDisplayed() {
        String birthday = "День рождения";
        String salary = "Зарплата";
        String tradeUnion = "Профсоюз";
        String holiday = "Праздник";
        String massage = "Массаж";
        String gratitude = "Благодарность";
        String helpIsNeeded = "Нужна помощь";

        newsPage.clickOnFilterButton();
        filterForm.clickOnCategoryField();

        filterForm.category(birthday).checkWithTimeout(matches(isDisplayed()));
        filterForm.category(salary).checkWithTimeout(matches(isDisplayed()));
        filterForm.category(tradeUnion).checkWithTimeout(matches(isDisplayed()));
        filterForm.category(holiday).checkWithTimeout(matches(isDisplayed()));
        filterForm.category(massage).checkWithTimeout(matches(isDisplayed()));
        filterForm.category(gratitude).checkWithTimeout(matches(isDisplayed()));
        filterForm.category(helpIsNeeded).checkWithTimeout(matches(isDisplayed()));

        CustomViewAction.returnBack();
    }
}
