package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;

public class AboutPage {
    public TimeoutEspresso.TimedViewInteraction appBarPanel =
            onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_about));
    public TimeoutEspresso.TimedViewInteraction aboutLabel =
            onViewWithTimeout(withId(R.id.about_company_info_label_text_view));
    public TimeoutEspresso.TimedViewInteraction versionText =
            onViewWithTimeout(withId(R.id.about_version_title_text_view));
    public TimeoutEspresso.TimedViewInteraction versionNumber =
            onViewWithTimeout(withId(R.id.about_version_value_text_view));
    public TimeoutEspresso.TimedViewInteraction privacyPolicyText =
            onViewWithTimeout(withId(R.id.about_privacy_policy_label_text_view));
    public TimeoutEspresso.TimedViewInteraction privacyPolicyLink =
            onViewWithTimeout(withId(R.id.about_privacy_policy_value_text_view));
    public TimeoutEspresso.TimedViewInteraction termOfUseText =
            onViewWithTimeout(withId(R.id.about_terms_of_use_label_text_view));
    public TimeoutEspresso.TimedViewInteraction termOfUseLink =
            onViewWithTimeout(withId(R.id.about_terms_of_use_value_text_view));

    public void clickOnTermOfUseLink() {
        Allure.step("Клик по ссылке о Пользовательском Соглашении");
        termOfUseLink.performWithTimeout(click());
    }

    public void clickOnPrivacyPolicyLink() {
        Allure.step("Клик по ссылке о Политике Конфиденциальности");
        privacyPolicyLink.performWithTimeout(click());
    }

}
