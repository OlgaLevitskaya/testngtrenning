package ru.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Примеры
 * https://github.com/barancev/testng_samples
 */

public class Task3Test extends TestBase {
    @Test(groups = "positive")
    public void createFileSuccess1() {
        Assert.assertTrue(createFile(getTmp().toString(), "temp"), "File isn't create!!!");
    }

    @Test(groups = "positive")
    public void createFileSuccess2() {
        Assert.assertTrue(createFile(getTmp().toString(), "temp.txt"), "File isn't create!!!");
    }

    @Test(groups = "negative")
    public void createFileNoDir() {
        Assert.assertFalse(createFile(getTmp() + "123", "temp1"), "File is create!!!");
    }

    @Test(groups = "negative")
    public void createFileWrongDir() {
        Assert.assertFalse(createFile(null, "temp1"), "File is create!!!");
    }

    @Test(groups = "negative")
    public void createFileNameEmptyName() {
        Assert.assertFalse(createFile(getTmp().toString(), ""), "File is create!!!");
    }
}
