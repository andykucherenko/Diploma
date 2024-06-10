package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.pages.*;

import static androidx.test.espresso.Espresso.pressBack;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final MainPage mainPage = new MainPage();
    private final FilterNewsPage filterNewsPage = new FilterNewsPage();
    private final NewsPage newsPage = new NewsPage();
    private final QuotesPage quotesPage = new QuotesPage();
    private final AboutPage aboutPage = new AboutPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        baseSteps.ensureAuthenticated();
        baseSteps.navigationTo("NewsPage");
    }

    @Test
    @DisplayName("Видимость основных элементов на странице")
    public void test_displayed_main_elements_on_the_page() {
        newsPage.checkHeaderPage();
        newsPage.checkDisplayedMainElements();
    }

    @Test
    @DisplayName("Переход на страницу фильтрации")
    public void test_open_filter_page() {
        newsPage.clickFilterButton();
        filterNewsPage.checkHeaderPage();
        pressBack();
    }

    @Test
    @DisplayName("Переход на страницу редактирования новостей")
    public void test_open_edit_news_page() {
        newsPage.clickEditNewsButton();
    }

    @Test
    @DisplayName("Переход со страницы новостей на главную страницу приложения")
    public void navigatingPagesOfMainMenuFromNewsPage() {
        mainPage.goToQuotesPage();
        quotesPage.checkHeaderQuotesPage();
        pressBack();

        mainPage.goToMainPage();
        mainPage.checkClickableAllNewsButton();
        pressBack();

        mainPage.goToAboutPage();
        aboutPage.checkDisplayedVersionAndCompanyInfo();
        aboutPage.clickBackButton();

        baseSteps.checkInitStateAppWhenUserAuth();

    }
}
