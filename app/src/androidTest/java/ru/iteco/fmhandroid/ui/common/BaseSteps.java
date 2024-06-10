package ru.iteco.fmhandroid.ui.common;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.common.EspressoUtils.waitDisplayed;

import android.util.Log;
import android.view.View;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

public class BaseSteps {
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final ViewInteraction logoApp = onView(withId(R.id.trademark_image_view));
    private final TestData testData = new TestData();

    public void logout() {
        Log.d("BaseSteps", "Ожидание видимости кнопки авторизации");
        // Ожидание видимости кнопки перед выполнением действия
        try {
            onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 10000)); // Увеличено время ожидания до 10000 мс

            // Логирование текущего состояния видимости элемента
            onView(withId(R.id.authorization_image_button)).check((view, noViewFoundException) -> {
                if (noViewFoundException != null) {
                    Log.e("BaseSteps", "Кнопка авторизации не найдена в иерархии представлений.");
                } else if (view.getVisibility() != View.VISIBLE) {
                    Log.e("BaseSteps", "Кнопка авторизации не видима.");
                } else {
                    Log.d("BaseSteps", "Кнопка авторизации видима и готова для взаимодействия.");
                }
            });

            mainPage.logOut();
            authPage.checkHeaderAuthPage();
        } catch (Exception e) {
            Log.e("BaseSteps", "Ошибка при попытке выхода: " + e.getMessage());
            throw e;
        }
    }

    public void expectSplashScreen() {
        Log.d("BaseSteps", "Проверка экрана заставки");
        ViewInteraction imageSplashScreen = onView(withId(R.id.splashscreen_image_view));
        imageSplashScreen.check(matches(isDisplayed()));

        ViewInteraction progressBar = onView(withId(R.id.splash_screen_circular_progress_indicator));
        progressBar.check(matches(isDisplayed()));

        ViewInteraction textSplashScreen = onView(withId(R.id.splashscreen_text_view));
        textSplashScreen.check(matches(isDisplayed()));

        Log.d("BaseSteps", "Ожидание появления экрана авторизации");
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 15000)); // Увеличено время ожидания
        authPage.checkHeaderAuthPage();
    }

    public void showToastMessage(String textMessage) {
        Log.d("BaseSteps", "Показать сообщение тоста: " + textMessage);
        onView(withText(textMessage))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void checkLogoApp() {
        Log.d("BaseSteps", "Проверка логотипа приложения");
        logoApp.check(matches(isDisplayed()));
    }

    public void navigationTo(String page) {
        Log.d("BaseSteps", "Навигация на страницу: " + page);
        switch (page) {
            case "AboutPage":
                mainPage.goToAboutPage();
                break;
            case "NewsPage":
                mainPage.goToNewsPage();
                break;
            case "MainPage":
                mainPage.goToMainPage();
                break;
            case "QuotesPage":
                mainPage.goToQuotesPage();
                break;
            default:
                Log.e("BaseSteps", "Неверное имя страницы: " + page);
                break;
        }
    }

    public void ensureAuthenticated() {
        Log.d("BaseSteps", "Проверка аутентификации пользователя");
        try {
            onView(isRoot()).perform(waitDisplayed(R.id.trademark_image_view, 5000));
        } catch (Exception e) {
            Log.d("BaseSteps", "Пользователь не аутентифицирован, выполнение входа");
            authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
        }
    }

    public void checkInitStateAppWhenUserAuth() {
        Log.d("BaseSteps", "Проверка начального состояния приложения при аутентификации пользователя");
        mainPage.checkInitStatePage();
    }
}