package io.github.zonarius.unity3d.parser;

import io.github.zonarius.unity3d.parser.util.BinaryReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestUtil {
    public static BinaryReader testFileAsReader() {
        try {
            return new BinaryReader(new FileInputStream(getTestFilePath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getTestFilePath() {
        byte[] bytes;
        try {
            try (InputStream stream = TestUtil.class.getClassLoader().getResourceAsStream("unity3d-test-path")) {
                bytes = stream.readAllBytes();
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
