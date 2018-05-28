package ru.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3Test {
    private Path tmp;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        tmp = Files.createTempDirectory("test");
        System.out.println("Create directory " + tmp);
    }

    @Test(groups = "positive")
    public void createFileSuccess1() {
        Assert.assertTrue(createFile(tmp.toString(), "temp"), "File isn't create!!!");
    }

    @Test(groups = "positive")
    public void createFileSuccess2() {
        Assert.assertTrue(createFile(tmp.toString(), "temp.txt"), "File isn't create!!!");
    }

    @Test(groups = "negative")
    public void createFileNoDir() {
        Assert.assertFalse(createFile(tmp + "123", "temp1"), "File is create!!!");
    }

    @Test(groups = "negative")
    public void createFileWrongDir() {
        Assert.assertFalse(createFile(null, "temp1"), "File is create!!!");
    }

    @Test(groups = "negative")
    public void createFileNameEmptyName() {
        Assert.assertFalse(createFile(tmp.toString(), ""), "File is create!!!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (tmp != null) tmp.toFile().deleteOnExit();
        System.out.println("Delete directory " + tmp);
    }

    private boolean createFile(String path, String name) {
        String fullPath = path + "\\" + name;
        File file = new File(fullPath);
        boolean res = false;
        try {
            res = file.createNewFile();
            System.out.println("Create file " + fullPath);
        } catch (IOException e) {
            System.out.println("Can't create file " + fullPath);
        }
        return res;
    }
}
