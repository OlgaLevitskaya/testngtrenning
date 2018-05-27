
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Практическое задание №3
 * <p>
 * Разработайте и добавьте в созданный ранее проект несколько тестов (2-3 позитивных теста и 1-2 негативных теста).
 * <p>
 * Тестировать будем функцию создания файла File.createNewFile(), входящую в стандартную библиотеку Java.
 * <p>
 * Не нужно делать сложные тесты, со сложной логикой или сложной архитектурой. Наша цель -- изучение возможностей
 * тестового фреймворка. Поэтому делайте несложные тесты.
 * <p>
 * Можно сделать тесты пока без проверок, если тестируемые операции отработали и ничего не упало -- значит всё хорошо.
 * <p>
 * Если Вы хотите вместо учебного примера сделать тесты для какого-то собственного приложения -- отлично!
 * Но не нарушайте условия неразглашения конфиденциальной информации.
 * Сделайте для этих тестов фикстуру. Она должна готовить временную директорию, в которой и будут выполняться попытки
 * создания файла, а после завершения теста эта временная директория должна быть удалена.
 * (Для создания временной директории можно использовать функцию c)
 * <p>
 * Если Вы делали тесты для своего собственного приложения -- подумайте, какая фикстура могла бы там быть полезна.
 * Убедитесь, что тесты запускаются в консоли и в среде разработки.
 */
public class Task3Test {
    private Path tmp;

    @BeforeClass
    public void setUp() throws IOException {
        tmp = Files.createTempDirectory("test");
        System.out.println("Create directory " + tmp);
    }

    @Test
    public void createFileSuccess1() throws IOException {
        createFile(tmp.toString(), "temp");
    }

    @Test
    public void createFileSuccess2() throws IOException {
        createFile(tmp.toString(), "temp.txt");
    }

    @Test
    public void createFileNoDir() throws IOException {
        createFile(tmp + "123", "temp1");
    }

    @Test
    public void createFileWrongDir() throws IOException {
        createFile(null, "temp1");
    }

    @Test
    public void createFileNameEmptyName() throws IOException {
        createFile(tmp.toString(), "");
    }

    @AfterClass
    public void tearDown() {
        if (tmp != null) tmp.toFile().deleteOnExit();
        System.out.println("Delete directory " + tmp);
    }

    private boolean createFile(String path, String name) throws IOException {
        String fullPath = path + "\\" + name;
        File file = new File(fullPath);
        boolean res = file.createNewFile();
        if (res) System.out.println("Create file " + fullPath);
        else System.out.println("Can't create file " + fullPath);
        return res;
    }
}
