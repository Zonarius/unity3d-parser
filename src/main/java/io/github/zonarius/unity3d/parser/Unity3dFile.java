package io.github.zonarius.unity3d.parser;

import io.github.zonarius.unity3d.parser.util.BinaryReader;
import net.jpountz.lz4.LZ4Factory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class Unity3dFile implements Closeable {
    private final BinaryReader reader;

    public Unity3dFile(InputStream inputStream) {
        this.reader = new BinaryReader(inputStream);
    }

    public void read() throws IOException {
        Unity3dHeader header = Unity3dHeader.fromBinaryReader(reader);
        switch (header.flags().getCompressionType()) {
            case NONE:
                readMetaData(header.uncompressedBlocksInfoSize());
            case LZMA:
            case LZ4:
                LZ4Factory.fastestInstance();
                break;
            default:
                throw new IllegalArgumentException("Unknown compression type: " + header.flags().getCompressionType());
        }
    }

    private void readMetaData(int size) {

    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
