package com.valtech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Exercise2 extends BaseTest {

    @Test
    public void tc() {
        homePge.openHomePage();
        homePge.openMainMenu();
        mainMenu.clickButtonFromMenu("Partners");
        Assert.assertTrue(mainMenu.isPartnersSectionVisible());
        mainMenu.clickMenuContentButton();
        partnersPage.clickPartnerSolutionButton();
        partnersPage.waitForPartnersSiteToBeLoaded();
        partnersPage.displayAllPartners();
    }
}
