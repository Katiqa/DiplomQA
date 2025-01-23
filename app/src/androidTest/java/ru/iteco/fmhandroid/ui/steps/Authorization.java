package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

public class Authorization {

    public void validLogin() {
        Allure.step("Авторизация валидного пользователя:");
        DataGenerator.User validUser = new DataGenerator().getValidUser();
        auth(validUser.getLogin(), validUser.getPassword());
    }

    public void invalidLogin() {
        Allure.step("Авторизация невалидного пользователя:");
        DataGenerator.User invalidUser = new DataGenerator().getInvalidUser();
        auth(invalidUser.getLogin(), invalidUser.getPassword());
    }

    public void loginEmptyUser(){
        Allure.step(("Авторизация с пустыми полями:"));
        DataGenerator.User emptyUser = new DataGenerator().getEmptyUser();
        auth(emptyUser.getLogin(),emptyUser.getPassword());
    }

    public void tryLogIn() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
    // LogIn method for tests Before/After
        try {
            authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        }
        catch (Exception e1){
            tryLogOut();
            authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        }
        validLogin();
        new MainPage().newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    public void tryLogOut() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        AppBarPanel appBarPanel = new AppBarPanel();
        // LogOut method for tests Before/After
        try {
            try {
                Allure.step("Выход из аккаунта:");
                appBarPanel.clickOnAuthButton();
                appBarPanel.clickOnLogOutButton();
                authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
            } catch (Exception e) {
                try {
                    authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
                } catch (Exception e1) {
                    CustomViewAction.returnBack();
                    appBarPanel.clickOnAuthButton();
                    appBarPanel.clickOnLogOutButton();
                    authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
                }
            }
        } catch (Exception e1) {
            throw new RuntimeException("Не удалось выйти из аккаунта ", e1);
        }
    }

    public void auth(String login, String password) {
        AuthorizationPage authorizationPage = new AuthorizationPage();

        authorizationPage.insertInLoginField(login);
        authorizationPage.insertInPasswordField(password);
        authorizationPage.clickOnEnterButton();
    }

    public void emptyLogin() {
    }
}
