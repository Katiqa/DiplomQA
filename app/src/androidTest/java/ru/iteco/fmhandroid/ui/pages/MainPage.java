package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;

public class MainPage {
    private String newsTitleText = "Новости";

    public TimeoutEspresso.TimedViewInteraction appBarPanel =
            onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_main));
    public TimeoutEspresso.TimedViewInteraction newsContainer =
            onViewWithTimeout(15000, withId(R.id.container_list_news_include_on_fragment_main));
    public TimeoutEspresso.TimedViewInteraction newsContainerTitle =
            onViewWithTimeout(withText(newsTitleText));
    public TimeoutEspresso.TimedViewInteraction dropButton =
            onViewWithTimeout(withId(R.id.expand_material_button));
    public TimeoutEspresso.TimedViewInteraction allNewsButton =
            onViewWithTimeout(withId(R.id.all_news_text_view));
    public TimeoutEspresso.TimedViewInteraction newsList =
            onViewWithTimeout(15000, withId(R.id.news_list_recycler_view));

    public void clickOnDropButton() {
        Allure.step("Клик по кнопке раскрытия/скрытия блока новостей на главной странице");
        dropButton.performWithTimeout(click());
    }
}
