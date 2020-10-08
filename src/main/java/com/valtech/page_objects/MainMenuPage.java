package com.valtech.page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainMenuPage {

    private SelenideElement getButtonFromMenu(String buttonName) {
        String xpath = String.format("//button[text()=\"%s\"]", buttonName);
        return $(By.xpath(xpath));
    }

    private SelenideElement getPartnersMenuSection() {
        return $(By.xpath("(//div[@class=\"site-nav__main__container\"])/div[3]"));
    }

    private SelenideElement getMenuMainContentRedirectButton() {
        return $(By.xpath("//div[@style=\"opacity: 1; visibility: inherit;\"]/div[@class=\"site-nav__main__content-box\"]/a"));
    }


    public boolean isPartnersSectionVisible() {
        return getPartnersMenuSection().waitUntil(Condition.attributeMatching("style", "opacity: 1; visibility: inherit;"), 4000).isDisplayed();
    }

    public void clickButtonFromMenu(String buttonName) {
        getButtonFromMenu(buttonName).click();
    }

    public void clickMenuContentButton() {
        getMenuMainContentRedirectButton().click();
    }

}
