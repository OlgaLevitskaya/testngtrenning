package ru.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Примеры
 * https://github.com/barancev/testng_samples
 */

public class DataProviderTest extends TestBase {
    @DataProvider
    public Object[][] fileName() {
        return new Object[][]{
                {"test"},
                {"test.txt"},
                {"_test"},
                {"абвгд"},
                {"АБВГД DJGKLS"},
                {String.valueOf(new Random().nextInt())},
        };
    }

    @Test(dataProvider = "fileName")
    public void testDataProvider1(String fileName) {
        createSuccessFile(getTmp(), fileName);
    }

    @Test(dataProvider = "loadUserFromFile")
    public void testDataProvider2(String fileName) {
        createSuccessFile(getTmp(), fileName);
    }


    @DataProvider
    public Iterator<Object[]> loadUserFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/user.data")));
        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }
}
