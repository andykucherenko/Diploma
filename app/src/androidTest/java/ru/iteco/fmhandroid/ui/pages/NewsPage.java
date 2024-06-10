package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.common.EspressoUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;
import ru.iteco.fmhandroid.R;

public class NewsPage {
    private final ViewInteraction headerPage = onView(withText("News"));
    private final ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    private final ViewInteraction allNews = onView(withId(R.id.all_news_cards_block_constraint_layout));
    private final ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));

    public void checkHeaderPage() {
        headerPage.check(matches(isDisplayed()));
        headerPage.check(matches(withText("News")));
    }

    public void checkDisplayedMainElements() {
        sortButton.check(matches(isClickable()));
        filterButton.check(matches(isClickable()));
        editNewsButton.check(matches(isClickable()));
        allNews.check(matches(isDisplayed()));
    }

    public void clickNewsByIndex(int index) {
        recyclerView.perform(waitDisplayed(R.id.news_list_recycler_view, 5000)); // Ожидание загрузки RecyclerView
        recyclerView.perform(actionOnItemAtPosition(index, click()));
        ViewInteraction itemNews = onView(allOf(withId(R.id.news_item_material_card_view), withParentIndex(index)));
        itemNews.check(matches(isDisplayed()));
    }

    public void checkDescriptionNews(String titleNewsText, String descriptionNewsText) {
        ViewInteraction titleNews = onView(allOf(withId(R.id.news_item_title_text_view), withText(titleNewsText)));
        ViewInteraction descriptionNews = onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionNewsText)));

        titleNews.perform(waitDisplayed(R.id.news_item_title_text_view, 5000));
        descriptionNews.perform(waitDisplayed(R.id.news_item_description_text_view, 5000));

        titleNews.check(matches(isDisplayed()));
        descriptionNews.check(matches(isDisplayed()));
    }

    public void clickFilterButton() {
        filterButton.check(matches(isDisplayed()));
        filterButton.perform(click());
    }

    public void clickEditNewsButton() {
        editNewsButton.check(matches(isDisplayed()));
        editNewsButton.perform(click());
    }

    public void clickSortButton() {
        sortButton.check(matches(isDisplayed()));
        sortButton.perform(click());
    }
}


