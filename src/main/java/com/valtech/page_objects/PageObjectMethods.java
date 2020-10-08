package com.valtech.page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public abstract class PageObjectMethods {
    private SelenideElement getCookieWindow = $(By.id("cookiebanner"));
    private SelenideElement getAcceptCookieButton = $(By.xpath("//a[text()=\"Accept cookies\"]"));

    private boolean isCookieWindowVisible() {
        try {
            getCookieWindow.waitUntil(Condition.appear, 7000);
            return true;
        } catch (ElementNotFound e) {
            return false;
        }
    }


    public void openPageByUrl(String url) {
        open(url);
        if (isCookieWindowVisible()) {
            getAcceptCookieButton.click();
        }
    }
}
