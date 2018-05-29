package ru.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestBase {
    public Path getTmp() {
        return tmp;
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

    public boolean createFile(String path, String name) {
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
