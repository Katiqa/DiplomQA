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
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;


import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

public class NewsPage {
    private String newsTitle = "Новости";

    public TimeoutEspresso.TimedViewInteraction title =
            onViewWithTimeout(withText(newsTitle));
    public TimeoutEspresso.TimedViewInteraction newsContainer =
            onViewWithTimeout(withId(R.id.container_list_news_include));

    public TimeoutEspresso.TimedViewInteraction appBarPanel =
            onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_news_list));
    public TimeoutEspresso.TimedViewInteraction sortButton =
            onViewWithTimeout(withId(R.id.sort_news_material_button));
    public TimeoutEspresso.TimedViewInteraction filterButton =
            onViewWithTimeout(withId(R.id.filter_news_material_button));
    public TimeoutEspresso.TimedViewInteraction editedButton =
            onViewWithTimeout(withId(R.id.edit_news_item_image_view));
    public TimeoutEspresso.TimedViewInteraction editButton =
            onViewWithTimeout(withId(R.id.edit_news_material_button));
    public TimeoutEspresso.TimedViewInteraction newsList =
            onViewWithTimeout(20000, withId(R.id.news_list_recycler_view));
    public TimeoutEspresso.TimedViewInteraction swipeRefresh =
            onViewWithTimeout(withId(R.id.news_list_swipe_refresh));
    public void swipeRefresh() {
        Allure.step("Обновление страницы свайпом вниз");
        swipeRefresh.performWithTimeout(swipeDown());
        CustomViewAction.stopExecutionForSeconds(1);
    }

    public void clickOnFilterButton() {
        Allure.step("Клик по кнопке фильтра");
        filterButton.performWithTimeout(click());
    }

    public void clickOnEditButton() {
        Allure.step("Клик по кнопке \"Панель управления\"");
        editButton.performWithTimeout(click());
    }


    public void filterNewsByCategory(String category) {
        FilterForm filterForm = new FilterForm();
        clickOnFilterButton();
        filterForm.clickOnCategoryField();
        filterForm.clickOnCategory(category);
        filterForm.clickOnAcceptButton();
    }

    public TimeoutEspresso.TimedViewInteraction dropButtonNewsWithTitle(String title) {
        return onViewWithTimeout(allOf(
                withId(R.id.view_news_item_image_view),
                hasSibling(allOf(
                        withId(R.id.news_item_title_text_view),
                        withText(title)))));
    }

    public TimeoutEspresso.TimedViewInteraction iconCategoryNewsWithTitle(String title) {
        return onViewWithTimeout(allOf(
                withId(R.id.category_icon_image_view),
                hasSibling(allOf(
                        withId(R.id.news_item_title_text_view),
                        withText(title)))));
    }

    public TimeoutEspresso.TimedViewInteraction descriptionNewsWithTitle(String title) {
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_description_text_view),
                hasSibling(allOf(
                        withId(R.id.news_item_title_text_view),
                        withText(title)))));
    }

    public TimeoutEspresso.TimedViewInteraction newsWithTitle(String title) {
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_title_text_view),
                withText(title)));
    }

    public TimeoutEspresso.TimedViewInteraction descriptionOfNews(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_description_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction iconOfNews(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.category_icon_image_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction titleOfNews(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.news_item_title_text_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }

    public TimeoutEspresso.TimedViewInteraction dropButtonOfNews(int num) {
        int number = num -1;
        return onViewWithTimeout(allOf(
                withId(R.id.view_news_item_image_view),
                withParent(
                        withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
    }
    public void clickOnNews(String title) {
        Allure.step("Клик по  новости в списке c заголовком \"" + title + "\"");
        editedButton.performWithTimeout(click());
    }
    public void scrollToNewsWithTitle(String title) {
        swipeRefresh();
        try {
            new NewsPage().newsList.perform(RecyclerViewActions.actionOnItem(
                    hasDescendant(withText(title)),
                    scrollTo()));
            Allure.step("Скролл к новости с заголовком: \"" + title + "\"");
        }
        catch (Exception e) {
        }
    }



    public class FilterForm {

        public TimeoutEspresso.TimedViewInteraction title =
                onViewWithTimeout(withId(R.id.filter_news_title_text_view));
        public TimeoutEspresso.TimedViewInteraction categoryField =
                onViewWithTimeout(withId(R.id.news_item_category_text_auto_complete_text_view));
        public TimeoutEspresso.TimedViewInteraction dateStartField =
                onViewWithTimeout(withId(R.id.news_item_publish_date_start_text_input_edit_text));
        public TimeoutEspresso.TimedViewInteraction dateEndField =
                onViewWithTimeout(withId(R.id.news_item_publish_date_end_text_input_edit_text));
        public TimeoutEspresso.TimedViewInteraction acceptButton =
                onViewWithTimeout(withId(R.id.filter_button));
        public TimeoutEspresso.TimedViewInteraction cancelButton =
                onViewWithTimeout(withId(R.id.cancel_button));

        public TimeoutEspresso.TimedViewInteraction category(String category) {
            return onViewWithTimeout(withText(category))
                    .inRoot(RootMatchers
                            .isPlatformPopup());
        }
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

        public void clickOnAcceptButton() {
            Allure.step("Клик по кнопке \"Фильтровать\"");
            acceptButton.performWithTimeout(click());
        }
    }
}
