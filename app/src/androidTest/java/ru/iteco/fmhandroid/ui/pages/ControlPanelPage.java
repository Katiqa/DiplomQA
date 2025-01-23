package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.matchers.CustomViewAction.clickOnViewWithId;
import static ru.iteco.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;

public class ControlPanelPage extends NewsPage {

    private String ControlPanelTitle = "Панель \n управления";

    public TimeoutEspresso.TimedViewInteraction title =
            onViewWithTimeout(withText(ControlPanelTitle));

    public TimeoutEspresso.TimedViewInteraction appBarPanel =
            onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_news_control_panel));
    public TimeoutEspresso.TimedViewInteraction addNewsButton =
            onViewWithTimeout(withId(R.id.add_news_image_view));
    public TimeoutEspresso.TimedViewInteraction swipeRefresh =
            onViewWithTimeout(withId(R.id.news_control_panel_swipe_to_refresh));
    public void swipeRefresh() {
        Allure.step("Обновление страницы свайпом вниз");
        swipeRefresh.performWithTimeout(swipeDown());
        CustomViewAction.stopExecutionForSeconds(1);
    }

    public void clickOnAddNewsButton() {
        Allure.step("Клик по кнопке добавления новости");
        addNewsButton.performWithTimeout(click());
    }

    public TimeoutEspresso.TimedViewInteraction statusNewsWithTitle(String title, String status) {
        return onViewWithTimeout(allOf(
                allOf(withId(R.id.news_item_published_text_view), withText(status)),
                hasSibling(allOf(
                        withId(R.id.news_item_title_text_view),
                        withText(title)))));
    }

    public TimeoutEspresso.TimedViewInteraction publicationTextField(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_publication_text_view),
                withParent(withParent(
                        childAtPosition(withId(R.id.news_list_recycler_view),
                                number)))));
    }

    public TimeoutEspresso.TimedViewInteraction publicationDateField(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_publication_date_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction creationTextField(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_creation_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction creationDateField(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_create_date_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction authorTextField(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_author_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction authorNameField(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_author_name_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction publicationStatus(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_published_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction deleteNewsButton(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.delete_news_item_image_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction editNewsButton(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.edit_news_item_image_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public void clickOnEditNewsButton(int num) {
        Allure.step("Клик по кнопке редактирования " + num + " новости");
        editNewsButton(num).performWithTimeout(click());
    }
    public void scrollToNewsWithTitle(String title) {
        swipeRefresh();
        try {
            new ControlPanelPage().newsList.perform(RecyclerViewActions.actionOnItem(
                    hasDescendant(withText(title)),
                    scrollTo()));
            Allure.step("Скролл к новости с заголовком: \"" + title + "\"");
        }
        catch (Exception e) {
        }
    }
    public void clickOnDeleteButtonNewsWithTitle(String title) {
        Allure.step("Клик по кнопке удаления новости с заголовком: \"" + title + "\"");
        new ControlPanelPage().newsList.perform(RecyclerViewActions.actionOnItem(
                hasDescendant(withText(title)),
                clickOnViewWithId(R.id.delete_news_item_image_view)));
    }
    public void clickOnEditButtonNewsWithTitle (String title) {
        Allure.step("Клик по кнопке редактирования новости с заголовком: \"" + title + "\"");
        new ControlPanelPage().newsList.perform(RecyclerViewActions.actionOnItem(
                hasDescendant(withText(title)),
                clickOnViewWithId(R.id.edit_news_item_image_view)));
    }
    public void clickOkDialogButton() {
        onViewWithTimeout(allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()))
                .inRoot(isDialog())
                .performWithTimeout(scrollTo(), click());
        Allure.step("Клик по кнопке \"ОК\" окна подтверждения");
    }



    public class FilterFormControlPanel extends NewsPage.FilterForm {
        public TimeoutEspresso.TimedViewInteraction checkboxActive =
                onViewWithTimeout(withId(R.id.filter_news_active_material_check_box));
        public TimeoutEspresso.TimedViewInteraction checkboxNotActive =
                onViewWithTimeout(withId(R.id.filter_news_inactive_material_check_box));
    }




    public class CreateEditForm {
        public TimeoutEspresso.TimedViewInteraction categoryField =
                onViewWithTimeout(withId(R.id.news_item_category_text_auto_complete_text_view));
        public TimeoutEspresso.TimedViewInteraction categoryFieldAlertIcon =
                onViewWithTimeout(allOf(
                        withId(R.id.news_item_category_text_input_layout),
                        withParent(
                                hasSibling(withId(R.id.news_item_category_text_auto_complete_text_view)))));

        public TimeoutEspresso.TimedViewInteraction titleField =
                onViewWithTimeout(withId(R.id.news_item_title_text_input_edit_text));
        public TimeoutEspresso.TimedViewInteraction titleFieldAlertIcon =
                onViewWithTimeout(allOf(
                        withId(R.id.news_item_title_text_input_layout),
                        withParent(
                                withParent(
                                        hasSibling(withId(R.id.news_item_title_text_input_edit_text))))));

        public TimeoutEspresso.TimedViewInteraction publicationDateField =
                onViewWithTimeout(withId(R.id.news_item_publish_date_text_input_edit_text));
        public TimeoutEspresso.TimedViewInteraction publicationDateFieldAlertIcon =
                onViewWithTimeout(allOf(
                        withId(R.id.news_item_create_date_text_input_layout),
                        withParent(
                                withParent(
                                        hasSibling(withId(R.id.news_item_publish_date_text_input_edit_text))))));

        public TimeoutEspresso.TimedViewInteraction publicationTimeField =
                onViewWithTimeout(withId(R.id.news_item_publish_time_text_input_edit_text));
        public TimeoutEspresso.TimedViewInteraction publicationTimeFieldAlertIcon =
                onViewWithTimeout(allOf(
                        withId(R.id.news_item_publish_time_text_input_layout),
                        withParent(
                                withParent(
                                        hasSibling(withId(R.id.news_item_publish_time_text_input_edit_text))))));
        public TimeoutEspresso.TimedViewInteraction descriptionField =
                onViewWithTimeout(withId(R.id.news_item_description_text_input_edit_text));
        public TimeoutEspresso.TimedViewInteraction descriptionFieldAlertIcon =
                onViewWithTimeout(allOf(
                        withId(R.id.news_item_description_text_input_layout),
                        withParent(
                                withParent(
                                        hasSibling(withId(R.id.news_item_description_text_input_edit_text))))));
        public TimeoutEspresso.TimedViewInteraction switcher =
                onViewWithTimeout(withId(R.id.switcher));
        public TimeoutEspresso.TimedViewInteraction saveButton =
                onViewWithTimeout(withId(R.id.save_button));

        public TimeoutEspresso.TimedViewInteraction cancelButton =
                onViewWithTimeout(withId(R.id.cancel_button));
        public void clickOnCategoryField() {
            Allure.step("Клик по полю выбора категории");
            categoryField.performWithTimeout(click(), closeSoftKeyboard());
        }

        public void clickOnCategory (String category) {
            Allure.step("Клик по категории \"" + category + "\"");
            onViewWithTimeout(withText(category))
                    .inRoot(RootMatchers
                            .isPlatformPopup())
                    .performWithTimeout(click());
        }

        public void insertInTitleField (String title) {
            Allure.step("Ввод в поле \"Заголовок\" значения \"" + title + "\"");
            titleField.performWithTimeout(replaceText(title));
        }

        public void insertInDescriptionField (String description) {
            Allure.step("Ввод в поле \"Описание\" значения \"" + description + "\"");
            descriptionField.performWithTimeout(replaceText(description));
        }

        public void insertInDateField (String date) {
            Allure.step("Ввод в поле \"Дата\" значения \"" + date + "\"");
            publicationDateField.performWithTimeout(replaceText(date));
        }

        public void insertInTimeField (String time) {
            Allure.step("Ввод в поле \"Время\" значения \"" + time + "\"");
            publicationTimeField.performWithTimeout(replaceText(time));
        }

        public void clickOnSwitcher() {
            Allure.step("Клик по переключателю статуса новости");
            switcher.performWithTimeout(click());
        }

        public void clickOnSaveButton() {
            Allure.step("Клик по кнопке \"Сохранить\"");
            saveButton.performWithTimeout(click());
        }
    }
}
