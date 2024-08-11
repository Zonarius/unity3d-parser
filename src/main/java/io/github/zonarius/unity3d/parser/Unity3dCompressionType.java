package io.github.zonarius.unity3d.parser;

public enum Unity3dCompressionType {
    NONE,
    LZMA,
    LZ4,
    LZ4HC,
    LZHAM;

    public static Unity3dCompressionType fromInt(int value) {
        return switch (value) {
            case 0 -> NONE;
            case 1 -> LZMA;
            case 2 -> LZ4;
            case 3 -> LZ4HC;
            case 4 -> LZHAM;
            default -> throw new IllegalArgumentException("Unknown compression type: " + value);
        };
    }
}
