package io.github.zonarius.unity3d.parser.util;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BinaryReader implements Closeable {
    private final InputStream inputStream;

    public BinaryReader(InputStream inputStream) {
        if (inputStream instanceof BufferedInputStream) {
            this.inputStream = inputStream;
        } else {
            this.inputStream = new BufferedInputStream(inputStream);
        }
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    public Output<String> stringZeroTerm() {
        return new Output<>() {
            @Override
            public String read() throws IOException {
                StringBuilder stringBuilder = new StringBuilder();
                int read;
                while ((read = inputStream.read()) != 0) {
                    stringBuilder.append((char) read);
                }
                return stringBuilder.toString();
            }

            @Override
            public void skip() throws IOException {
                while (inputStream.read() > 0) { }
            }
        };
    }

    public Output<Integer> int32() {
        return readBytesInt(4);
    }

    public Output<Long> int64() {
        return readBytesLong(8);
    }

    private Output<Integer> readBytesInt(int length) {
        return new Output<>() {
            @Override
            public Integer read() throws IOException {
                int result = 0;
                for (int i = 0; i < length; i++) {
                    result = (result << 8) | inputStream.read();
                }
                return result;
            }

            @Override
            public void skip() throws IOException {
                if (inputStream.skip(length) != length) {
                    throw new IOException("Failed to skip bytes");
                };
            }
        };
    }

    private Output<Long> readBytesLong(int length) {
        return new Output<>() {
            @Override
            public Long read() throws IOException {
                long result = 0;
                for (int i = 0; i < length; i++) {
                    result = (result << 8) | inputStream.read();
                }
                return result;
            }

            @Override
            public void skip() throws IOException {
                if (inputStream.skip(length) != length) {
                    throw new IOException("Failed to skip bytes");
                };
            }
        };
    }
}
