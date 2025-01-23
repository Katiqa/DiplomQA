package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.OurMissionPage;

public class OpenPage {
    // Methods for opening application pages
    public MainPage main() {
        AppBarPanel appBarPanel = new AppBarPanel();
        MainPage mainPage = new MainPage();
        Allure.step("Переход к \"Главной\" странице:");
        try {
            appBarPanel.clickOnMainMenuButton();
            appBarPanel.clickOnMainPageButton();
        } catch (Exception e) {
            try {
                appBarPanel.clickOnAboutBackButton();
                appBarPanel.clickOnMainMenuButton();
                appBarPanel.clickOnMainPageButton();
            } catch (Exception e1) {
            }
        }

        try {
            mainPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
            mainPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
            return mainPage;
        } catch (Exception e1) {
            throw new RuntimeException("Не удалось перейти на страницу \"Главная\" после попытки вернуться назад.", e1);
        }
    }

    public NewsPage news() {
        AppBarPanel appBarPanel = new AppBarPanel();
        NewsPage newsPage = new NewsPage();
        Allure.step("Переход к странице \"Новости\":");

        try {
            appBarPanel.clickOnMainMenuButton();
            appBarPanel.clickOnNewsPageButton();
        } catch (Exception e) {
            Allure.step("Не удалось перейти на страницу \"Новости\", пробуем вернуться назад.");
            try {
                appBarPanel.clickOnAboutBackButton();
                appBarPanel.clickOnMainMenuButton();
                appBarPanel.clickOnNewsPageButton();
            } catch (Exception e1) {
                throw new RuntimeException("Не удалось перейти на страницу \"Новости\" после попытки вернуться назад.", e1);
            }
        }

        try {
            newsPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
            return newsPage;
        } catch (Exception e) {
            throw new RuntimeException("Элемент новостей не отображается.", e);
        }
    }


    public ControlPanelPage controlPanel() {
        NewsPage newsPage = new NewsPage();
        ControlPanelPage controlPanelPage = new ControlPanelPage();
        try {
            controlPanelPage.title.check(matches(isDisplayed()));
            return controlPanelPage;
        } catch (Exception e) {
            Allure.step("Переход к странице \"Панель управления\":");
            try {
                newsPage.clickOnEditButton();
                controlPanelPage.title.checkWithTimeout(matches(isDisplayed()));
                return controlPanelPage;
            } catch (Exception e1) {
                new OpenPage().news();
                newsPage.clickOnEditButton();
                controlPanelPage.title.checkWithTimeout(matches(isDisplayed()));
                return controlPanelPage;
            }
        }
    }

    public AboutPage about() {
        AppBarPanel appBarPanel = new AppBarPanel();
        AboutPage aboutPage = new AboutPage();
        Allure.step("Переход к странице \"О приложении\":");
        try {
            appBarPanel.clickOnMainMenuButton();
            appBarPanel.clickOnAboutPageButton();
        } catch (Exception e) {
            CustomViewAction.returnBack();
            appBarPanel.clickOnMainMenuButton();
            appBarPanel.clickOnAboutPageButton();
        }

        try {
            aboutPage.aboutLabel.checkWithTimeout(matches(isDisplayed()));
            return aboutPage;
        } catch (Exception e1) {
            throw new RuntimeException("Не удалось перейти на страницу \"О приложении\" после попытки вернуться назад.", e1);
        }
    }

    public OurMissionPage ourMission() {
        AppBarPanel appBarPanel = new AppBarPanel();
        OurMissionPage ourMissionPage = new OurMissionPage();
        Allure.step("Переход к странице тематических цитат:");
        try {
            appBarPanel.clickOnOurMissionButton();
        } catch (Exception e) {
            appBarPanel.clickOnAboutBackButton();
            appBarPanel.clickOnOurMissionButton();
        }

        try {
            ourMissionPage.title.checkWithTimeout(matches(isDisplayed()));
            return ourMissionPage;
        } catch (Exception e1) {
            throw new RuntimeException("Не удалось перейти на страницу тематических цитат после попытки вернуться назад.", e1);
        }
    }
}
