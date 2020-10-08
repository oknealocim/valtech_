package com.valtech.page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends PageObjectMethods {

    private final String homePageLink = "https://www.valtech.com/en-gb";

    /**
     * Elements
     **/


    private SelenideElement getMenuButton = $(By.className("icon-menu"));

    private SelenideElement getSelectLocationButton = $(By.className("location-select"));

    private SelenideElement getSearchButton = $(By.xpath("//a[@href=\"/search/\"]"));

    /**
     * Actions
     **/
    public void openHomePage() {
        openPageByUrl(homePageLink);
    }

    public void openMainMenu() {
        getMenuButton.waitUntil(Condition.visible, 10000).click();
    }


}
