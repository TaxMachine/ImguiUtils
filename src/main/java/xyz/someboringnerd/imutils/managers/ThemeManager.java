package xyz.someboringnerd.imutils.managers;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileNotFoundException;

@UtilityClass
public class ThemeManager {

    @SneakyThrows
    public static void loadTheme(File file) {
        if(!file.exists())
            throw new FileNotFoundException("The given file does not exist.");

        if(!file.canRead())
            throw new RuntimeException("The given file is not readable (bad permissions ?)");

        if(!file.isFile())
            throw new RuntimeException("The given file is a folder.");

    }

}
