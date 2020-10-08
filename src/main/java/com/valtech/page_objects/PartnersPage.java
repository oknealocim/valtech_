package com.valtech.page_objects;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import java.util.List;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PartnersPage {

    Logger logger ;

    public PartnersPage (Logger logger){
        this.logger = logger;
    }

    private SelenideElement getOurPartnerSolutionsButton() {
        return $(By.xpath("//a[@class=\"button button--tertiary\"]"));
    }

    private ElementsCollection getAllPartnersCollection = $$(By.className("case-item__title"));

    private SelenideElement getLoadMoreButton = $(By.xpath("//button[contains(text(), 'Load More')]"));

    private SelenideElement getIndustryButton = $(By.xpath("//span[text()=\"All Industry\"]"));

    public void clickPartnerSolutionButton() {
        getOurPartnerSolutionsButton().click();
    }

    public void waitForPartnersSiteToBeLoaded() {
        getIndustryButton.waitUntil(Condition.visible, 10000);
    }

    public void displayAllPartners() {
        logger.debug("Printing the list Partners:");
        while (getLoadMoreButton.isDisplayed()) {
            getLoadMoreButton.click();
        }

        List<String> allPartners = getAllPartnersCollection.texts();
        for (String eachPartner : allPartners) {
            logger.debug(eachPartner);
        }
    }


}
