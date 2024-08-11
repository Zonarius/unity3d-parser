package io.github.zonarius.unity3d.parser;

public record Unity3dHeaderFlags(int flags) {
    public Unity3dCompressionType getCompressionType() {
        return Unity3dCompressionType.fromInt(flags & 0x3F);
    }
}
