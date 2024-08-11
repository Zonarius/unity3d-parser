package io.github.zonarius.unity3d.parser;

import io.github.zonarius.unity3d.parser.util.BinaryReader;

import java.io.IOException;

public record Unity3dHeader(
    int version,
    String webBundleVersion,
    String webMinimumVersion,
    long size,
    int compressedBlocksInfoSize,
    int uncompressedBlocksInfoSize,
    Unity3dHeaderFlags flags
) {
    private final static String MAGIC_STRING = "UnityFS";

    public static Unity3dHeader fromBinaryReader(BinaryReader reader) throws IOException {
        if (!reader.stringZeroTerm().read().equals(MAGIC_STRING)) {
            throw new IllegalArgumentException("Invalid Unity3d Header");
        }

        return new Unity3dHeader(
            reader.int32().read(),
            reader.stringZeroTerm().read(),
            reader.stringZeroTerm().read(),
            reader.int64().read(),
            reader.int32().read(),
            reader.int32().read(),
            new Unity3dHeaderFlags(reader.int32().read())
        );
    }
}
