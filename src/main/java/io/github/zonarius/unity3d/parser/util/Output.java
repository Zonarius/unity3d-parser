package io.github.zonarius.unity3d.parser.util;

import java.io.IOException;

public interface Output<T> {
    T read() throws IOException;
    void skip() throws IOException;
}
