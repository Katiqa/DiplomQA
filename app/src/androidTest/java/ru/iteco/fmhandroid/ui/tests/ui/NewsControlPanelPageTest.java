package ru.iteco.fmhandroid.ui.tests.ui;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.matchers.CustomViewAction.returnBack;

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
import ru.iteco.fmhandroid.ui.steps.Authorization;
import ru.iteco.fmhandroid.ui.steps.NewsActions;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsControlPanelPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private Authorization auth = new Authorization();
    private AppBarPanel appBarPanel;
    private ControlPanelPage controlPanelPage;
    private ControlPanelPage.FilterFormControlPanel filterFormControlPanel;
    private ControlPanelPage.CreateEditForm createEditForm;
    @Before
    public void setUp() {
        auth.tryLogIn();
        appBarPanel = new AppBarPanel();
        controlPanelPage = new OpenPage().controlPanel();
        filterFormControlPanel = controlPanelPage.new FilterFormControlPanel();
        createEditForm = controlPanelPage.new CreateEditForm();
    }


    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "AppBar на странице \"Панель управления\"")
    @Test
    @Description(value = "Тест проверяет отображение AppBar панели на странице \"Панель управления\"")
    public void shouldCheckAppBarOnControlPanelIsDisplayed() {
        controlPanelPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "Лого AppBar на странице \"Панель управления\"")
    @Test
    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на странице \"Панель управления\"")
    public void shouldCheckAppBarLogoOnControlPanelIsDisplayed() {
        appBarPanel.mainImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Заголовок")
    @Test
    @Description(value = "Тест проверяет отображение заголовка на странице \"Панель управления\"")
    public void shouldCheckControlPanelTitleIsDisplayed() {
        controlPanelPage.title.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Кнопки управления")
    @Test
    @Description(value = "Тест проверяет отображение кнопок управления на странице \"Панель управления\"")
    public void shouldCheckControlButtonsIsDisplayed() {
        controlPanelPage.sortButton.checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.filterButton.checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.addNewsButton.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Элементы скрытой новости")
    @Test
    @Description(value = "Тест проверяет отображение элементов первой в списке нераскрытой новости")
    public void shouldCheckHiddenFirstNewsElementsIsDisplayed() {
        NewsActions newsActions = new NewsActions();
        String title = DataGenerator.RandomString.getRandomRuString(5);
        newsActions.addNewsFirst(title);
        int numberOfNews = 1;
        String publicationTextField = "Дата публикации";
        String creationTextField = "Дата создания";
        String authorTextField = "Автор";

        controlPanelPage.titleOfNews(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.publicationTextField(numberOfNews).checkWithTimeout(matches(isDisplayed())).
                check(matches(withText(publicationTextField)));
        controlPanelPage.publicationDateField(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.creationTextField(numberOfNews).checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(creationTextField)));
        controlPanelPage.creationDateField(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.authorTextField(numberOfNews).checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(authorTextField)));
        controlPanelPage.authorNameField(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.publicationStatus(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.deleteNewsButton(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.editNewsButton(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.dropButtonOfNews(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        controlPanelPage.iconOfNews(numberOfNews).checkWithTimeout(matches(isDisplayed()));

        controlPanelPage.descriptionOfNews(numberOfNews).checkWithTimeout(matches(not(isDisplayed())));

        newsActions.deleteNewsWithTitle(title);
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы фильтра")
    public void shouldCheckFilterFormElementsOnControlPanelIsDisplayed() {
        String formTitle = "Фильтровать новости";
        String categoryHint = "Категория";
        String dateFormat = "ДД.ММ.ГГГГ";
        String acceptButton = "Фильтровать";
        String cancelButton = "Отмена";
        String checkboxActive = "Активна";
        String checkboxNotActive = "Не активна";

        controlPanelPage.clickOnFilterButton();

        filterFormControlPanel.title.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(formTitle)));
        filterFormControlPanel.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryHint)));
        filterFormControlPanel.dateStartField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        filterFormControlPanel.dateEndField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        filterFormControlPanel.acceptButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(acceptButton)));
        filterFormControlPanel.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
        filterFormControlPanel.checkboxActive.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(checkboxActive)));
        filterFormControlPanel.checkboxNotActive.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(checkboxNotActive)));
    }
    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение категорий формы фильтра")
    public void shouldCheckFilterFormOnControlCategoriesIsDisplayed() {
        String birthday = "День рождения";
        String salary = "Зарплата";
        String tradeUnion = "Профсоюз";
        String holiday = "Праздник";
        String massage = "Массаж";
        String gratitude = "Благодарность";
        String helpIsNeeded = "Нужна помощь";

        controlPanelPage.clickOnFilterButton();
        filterFormControlPanel.clickOnCategoryField();

        filterFormControlPanel.category(birthday).checkWithTimeout(matches(isDisplayed()));
        filterFormControlPanel.category(salary).checkWithTimeout(matches(isDisplayed()));
        filterFormControlPanel.category(tradeUnion).checkWithTimeout(matches(isDisplayed()));
        filterFormControlPanel.category(holiday).checkWithTimeout(matches(isDisplayed()));
        filterFormControlPanel.category(massage).checkWithTimeout(matches(isDisplayed()));
        filterFormControlPanel.category(gratitude).checkWithTimeout(matches(isDisplayed()));
        filterFormControlPanel.category(helpIsNeeded).checkWithTimeout(matches(isDisplayed()));

        CustomViewAction.returnBack();
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма редактирования новости")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы редактирования новости")
    public void shouldCheckEditFormElementsOnControlPanelIsDisplayed() {
        NewsActions newsActions = new NewsActions();
        String title = DataGenerator.RandomString.getRandomRuString(5);
        newsActions.addNewsFirst(title);
        String appBarTopLine = "Редактирование";
        String appBarBottomLine = "Новости";

        String categoryField = "Категория";
        String titleField = "Заголовок";
        String publicationDateField = "Дата публикации";
        String publicationTimeField = "Время";
        String descriptionField = "Описание";
        String saveButton = "Сохранить";
        String cancelButton = "Отмена";

        controlPanelPage.clickOnEditButtonNewsWithTitle(title);

        appBarPanel.topLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarTopLine)));
        appBarPanel.bottomLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarBottomLine)));

        createEditForm.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryField)));
        createEditForm.titleField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(titleField)));
        createEditForm.publicationDateField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationDateField)));
        createEditForm.publicationTimeField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationTimeField)));
        createEditForm.descriptionField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(descriptionField)));
        createEditForm.switcher.checkWithTimeout(matches(isDisplayed()));
        createEditForm.saveButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(saveButton)));
        createEditForm.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
        returnBack();
        newsActions.deleteNewsWithTitle(title);
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма создания новости")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы создания новости")
    public void shouldCheckCreateFormElementsOnControlPanelIsDisplayed() {
        String appBarTopLine = "Создание";
        String appBarBottomLine = "Новости";

        String categoryField = "Категория";
        String titleField = "Заголовок";
        String publicationDateField = "Дата публикации";
        String publicationTimeField = "Время";
        String descriptionField = "Описание";
        String saveButton = "Сохранить";
        String cancelButton = "Отмена";

        controlPanelPage.clickOnAddNewsButton();

        appBarPanel.topLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarTopLine)));
        appBarPanel.bottomLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarBottomLine)));

        createEditForm.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryField)));
        createEditForm.titleField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(titleField)));
        createEditForm.publicationDateField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationDateField)));
        createEditForm.publicationTimeField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationTimeField)));
        createEditForm.descriptionField.checkWithTimeout(matches(isDisplayed())).
                check(matches(withHint(descriptionField)));
        createEditForm.switcher.checkWithTimeout(matches(isDisplayed()));
        createEditForm.saveButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(saveButton)));
        createEditForm.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
    }
}
