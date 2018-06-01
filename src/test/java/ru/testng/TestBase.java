package ru.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestBase {
    public String getTmp() {
        return tmp.toString();
    }

    private Path tmp;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        tmp = Files.createTempDirectory("test");
        System.out.println("Create directory " + tmp);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (tmp != null) tmp.toFile().deleteOnExit();
        System.out.println("Delete directory " + tmp);
    }

    public void createNegativeFile(String path, String name) throws IOException {
        String fullPath = getPath(path, name);
        File file = new File(fullPath);
        SoftAssert as = new SoftAssert();
        as.assertFalse(file.createNewFile(), "File is create");
        as.assertTrue(file.exists(), "File exist");
        as.assertAll();
    }

    public void createSuccessFile(String path, String name) {
        String fullPath = getPath(path, name);
        File file = new File(fullPath);
        boolean res;
        SoftAssert as = new SoftAssert();
        try {
            res = file.createNewFile();
            as.assertTrue(res);
            System.out.println("Create file " + fullPath);
        } catch (IOException e) {
            Assert.fail("Can't create file " + fullPath + "\n" + e);
        }
        as.assertTrue(file.exists(), "File isn't exist");
        System.out.println("File exist" + fullPath);
        as.assertAll();
    }

    private String getPath(String path, String name) {
        return path + "\\" + name;
    }
}
