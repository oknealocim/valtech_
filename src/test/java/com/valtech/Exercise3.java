package com.valtech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Exercise3 extends BaseTest {

    private static List<String> listForAddresses;
    private byte counter = 0;

    @Test
    public void tc_1() throws IOException {
        officePage.openOfficePage();
        officePage.openOfficeAddresses("United Kingdom");
        listForAddresses = officePage.getOfficeAddress();
        reportAddressesToConsole(listForAddresses);
    }


    @Test(dataProvider = "addressForAsserts")
    public void tc_2(String address) {
        Assert.assertEquals(listForAddresses.get(counter), address);
        counter++;
    }

    private void reportAddressesToConsole(List<String> listForOfficeAddresses) {
        for (String eachAddress : listForOfficeAddresses) {
            logger.debug("Parsed Address is:\n" + eachAddress + "\n");
        }
    }

    //I'm doing it because i want to show that i know how to use data provider
    //Even in this case it being longer
    @DataProvider
    public Object[] addressForAsserts() {
        return new Object[]{"Valtech Bristol\n" +
                "Unit 18, Keynsham Rd, Willsbridge\n" +
                "Bristol BS30 6EL\n" +
                "+44 (0) 20 7014 0800\n" +
                "info@valtech.co.uk",
                "Valtech London \n" +
                        "46 Colebrooke Row \n" +
                        "London N1 8AF \n" +
                        "+44 (0) 20 7014 0800 \n" +
                        "info@valtech.co.uk",
                "Valtech Manchester\n" +
                        "Basil Chambers\n" +
                        "65 High Street\n" +
                        "Manchester M4 1FS\n" +
                        "+44 (0) 20 7014 0800\n" +
                        "info@valtech.co.uk"};
    }

}
