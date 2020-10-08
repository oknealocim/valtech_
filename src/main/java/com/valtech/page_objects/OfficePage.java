package com.valtech.page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class OfficePage extends PageObjectMethods {

    private final String OfficePageUrl = "https://www.valtech.com/about/offices";

    public void openOfficePage() {
        openPageByUrl(OfficePageUrl);
    }

    private SelenideElement getOfficeByName(String officeName) {
        return $(By.xpath(String.format("//img[@alt=\"%s\"]/parent::div/div[1]", officeName)));
    }

    private ElementsCollection getOfficeAddressesCollection() {
        return $$(By.xpath("//div[@class=\"contact-item__drawer is-open\"]//address"));
    }

    private SelenideElement getAddressTable() {
        return $(By.xpath("//div[@class=\"contact-item__drawer is-open\"]"));
    }

    public void openOfficeAddresses(String officeName) {
        getOfficeByName(officeName).waitUntil(Condition.visible, 10000).click();
    }

    public List<String> getOfficeAddress() {
        getAddressTable().waitUntil(Condition.visible, 5000);
        return getOfficeAddressesCollection().texts();
    }


}
