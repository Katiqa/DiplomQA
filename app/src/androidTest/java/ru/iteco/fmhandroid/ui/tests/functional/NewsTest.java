package ru.iteco.fmhandroid.ui.tests.functional;

import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
import ru.iteco.fmhandroid.ui.matchers.ToastMatcher;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.steps.Authorization;
import ru.iteco.fmhandroid.ui.steps.NewsActions;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private final Authorization auth = new Authorization();
    private NewsPage newsPage;
    private ControlPanelPage controlPanelPage;
    private ControlPanelPage.CreateEditForm createEditForm;
    private NewsActions newsActions;

    @Before
    public void setUp() {
        auth.tryLogIn();
        newsPage = new OpenPage().news();
        controlPanelPage = new ControlPanelPage();
        createEditForm = controlPanelPage.new CreateEditForm();
        newsActions = new NewsActions();
    }


    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Добавление валидной новости")
    @Test
    @Description(value = "Тест проверяет возможность добавления корректно заполненной новости")
    public void shouldCheckAddNewNews() {
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);

        // Добавление корректной новости
        newsActions.addNewsFirst(newsTitle);

        // Проверка публикации новости
        controlPanelPage.scrollToNewsWithTitle(newsTitle);
        newsPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Удаление новости
        newsActions.deleteNewsWithTitle(newsTitle);
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Добавление не валидной новости")
    @Test(expected = NullPointerException.class)
    @Description(value = "Тест проверяет попытку добавления новости без заполнения полей")
    public void shouldCheckNotAddNewEmptyNews() {
        String toastMessage = "Заполните пустые поля";

        new OpenPage().controlPanel();
        controlPanelPage.clickOnAddNewsButton();
        createEditForm.clickOnSaveButton();

        ToastMatcher.checkToastMessageIsDisplayed(toastMessage);
        createEditForm.categoryFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.titleFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.publicationDateFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.publicationTimeFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.descriptionFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Редактирование новости")
    @Test
    @Description(value = "Тест проверяет изменение статуса новости")
    public void shouldCheckEditStatusNews() {
        String title = DataGenerator.RandomString.getRandomRuString(5);
        String statusActive = "Активна";
        String statusNotActive = "Не активна";

        // Добавление новости
        newsActions.addNewsFirst(title);

        // Проверка статуса добавленной новости
        controlPanelPage.scrollToNewsWithTitle(title);
        controlPanelPage.statusNewsWithTitle(title, statusActive)
                .checkWithTimeout(matches(isDisplayed()));

        // Изменение статуса добавленной новости
        newsActions.changeStatusNewsWithTitle(title);

        // Проверка изменения статуса добавленной новости
        controlPanelPage.scrollToNewsWithTitle(title);
        controlPanelPage.statusNewsWithTitle(title, statusNotActive)
                .checkWithTimeout(matches(isDisplayed()));

        // Удаление новости после теста
        newsActions.deleteNewsWithTitle(title);
    }


    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Фильтр новостей")
    @Test
    @Description(value = "Тест проверяет корректное отображение новостей с применением фильтра")
    public void shouldCheckFilterNews() {
        String categoryAnnouncement = "Объявление";
        String categorySalary = "Зарплата";
        String titleAnnouncement = DataGenerator.RandomString.getRandomRuString(5);
        String titleSalary = DataGenerator.RandomString.getRandomRuString(5);

        // Добавление новостей
        newsActions.addNewsFirst(titleAnnouncement);
        newsActions.addNewsSecond(categorySalary, titleSalary);

        // Фильтр по категории
        newsPage.filterNewsByCategory(categoryAnnouncement);

        // Проверка отображения новостей с категорией "объявление"
        controlPanelPage.scrollToNewsWithTitle(titleAnnouncement);
        controlPanelPage.newsWithTitle(titleAnnouncement).checkWithTimeout(matches(isDisplayed()));
        // Проверка отсутствия новостей с категорией "зарплата"
        newsPage.newsWithTitle(titleSalary).checkWithTimeout(doesNotExist());

        // Изменение фильтра по категории
        newsPage.filterNewsByCategory(categorySalary);

        // Проверка отображения новостей с категорией "зарплата"
        controlPanelPage.scrollToNewsWithTitle(titleSalary);
        controlPanelPage.newsWithTitle(titleSalary).checkWithTimeout(matches(isDisplayed()));
        // Проверка отсутствия новостей с категорией "объявление"
        controlPanelPage.newsWithTitle(titleAnnouncement).checkWithTimeout(doesNotExist());
    }


    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Удаление новости")
    @Test
    @Description(value = "Тест проверяет процесс удаления ранее добавленной новости")
    public void shouldCheckDeleteAddedNews() {
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);

        // Добавление корректной новости
        newsActions.addNewsFirst(newsTitle);

        // Проверка, что новость была добавлена в список
        controlPanelPage.scrollToNewsWithTitle(newsTitle);
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Удаление новости
        newsActions.deleteNewsWithTitle(newsTitle);

        // Проверка, что новость была удалена
        controlPanelPage.swipeRefresh();
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(doesNotExist());
    }

}