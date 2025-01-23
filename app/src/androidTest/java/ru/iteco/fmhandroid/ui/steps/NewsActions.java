package ru.iteco.fmhandroid.ui.steps;

import static ru.iteco.fmhandroid.ui.matchers.CustomViewAction.returnBack;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;


public class NewsActions {
    private String defaultCategory = "Объявление";
    private String defaultDate = DataGenerator.getCurrentDate();
    private String defaultTime = DataGenerator.getCurrentTime();
    public void addNewsFirst(String title) {
        addNews(defaultCategory, title, defaultDate, defaultTime, title);
    }

    public void addNewsSecond(String category, String title) {
        addNews(category, title, defaultDate, defaultTime, title);
    }
    public void addNews(String category, String title, String date, String time, String description) {

        Allure.step("Создание новой новости:");
        ControlPanelPage controlPanelPage = new OpenPage().controlPanel();
        ControlPanelPage.CreateEditForm createEditForm = controlPanelPage.new CreateEditForm();

        try {
            controlPanelPage.clickOnAddNewsButton();
            createEditForm.clickOnCategoryField();
            createEditForm.clickOnCategory(category);
            createEditForm.insertInTitleField(title);
            createEditForm.insertInDateField(date);
            createEditForm.insertInTimeField(time);
            createEditForm.insertInDescriptionField(description);

            createEditForm.clickOnSaveButton();
        }
        catch (Exception e1) {
            throw new RuntimeException("Не создать новую новость", e1);
        }
    }

    public void editDescriptionNewsWithTitle(String title, String newDescription) {
        ControlPanelPage controlPanelPage = new OpenPage().controlPanel();
        ControlPanelPage.CreateEditForm createEditForm = controlPanelPage.new CreateEditForm();

        try {
            controlPanelPage.clickOnEditButtonNewsWithTitle(title);
            createEditForm.insertInDescriptionField(newDescription);
            createEditForm.clickOnSaveButton();
        }
        catch (Exception e1) {
            throw new RuntimeException("Не удалось отредактировать описание", e1);
        }
    }
    public void changeStatusNewsWithTitle (String title) {
        ControlPanelPage controlPanelPage = new OpenPage().controlPanel();
        ControlPanelPage.CreateEditForm createEditForm = new ControlPanelPage().new CreateEditForm();
        try {
            controlPanelPage.clickOnEditButtonNewsWithTitle(title);
            createEditForm.clickOnSwitcher();
            createEditForm.clickOnSaveButton();
        }
        catch (Exception e1) {
            throw new RuntimeException("Не изменить статус ", e1);
        }
    }

    public void deleteNewsWithTitle(String title) {
        ControlPanelPage controlPanelPage = new OpenPage().controlPanel();
        try {
            Allure.step("Удаление новости с заголовком: \"" + title + "\"");
            controlPanelPage.swipeRefresh();
            controlPanelPage.clickOnDeleteButtonNewsWithTitle(title);

            // To wait for a selector to appear in the hierarchy
            CustomViewAction.stopExecutionForSeconds(20);
            controlPanelPage.clickOkDialogButton();
        }
        catch (Exception e1) {
            throw new RuntimeException("Не удалось удалить новость", e1);
        }
    }
}
