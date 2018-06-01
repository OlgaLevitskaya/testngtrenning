package ru.testng;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Примеры
 * https://github.com/barancev/testng_samples
 */

public class Task3Test extends TestBase {
    @Test(groups = "positive")
    public void createFileSuccess1() {
        createSuccessFile(getTmp().toString(), "temp");
    }

    @Test(groups = "positive")
    public void createFileSuccess2() {
        createSuccessFile(getTmp().toString(), "temp.txt");
    }

    @Test(testName = "Создание файла по нсуществующей дирректории",
            expectedExceptions = IOException.class, groups = "negative")
    public void createFileNoDir() throws IOException {
        createNegativeFile(getTmp() + "123", "temp1");
    }

    @Test(testName = "Создание файла по дирректории = null",
            expectedExceptions = IOException.class, groups = "negative")
    public void createFileDirisNull() throws IOException {
        createNegativeFile(null, "temp1");
    }

    @Test(groups = "negative")
    public void createFileNameEmptyName() throws IOException {
        createNegativeFile(getTmp().toString(), "");
    }
}
