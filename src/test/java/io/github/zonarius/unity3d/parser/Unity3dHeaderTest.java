package io.github.zonarius.unity3d.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Unity3dHeaderTest {
    @Test
    void readFromFile() throws IOException {
        Unity3dHeader header = Unity3dHeader.fromBinaryReader(TestUtil.testFileAsReader());

        assertThat(header.version()).isEqualTo(7);
        assertThat(header.webBundleVersion()).isEqualTo("5.x.x");
        assertThat(header.webMinimumVersion()).isEqualTo("2020.3.30f1XD1.1.701b");
        assertThat(header.size()).isEqualTo(1855067);
        assertThat(header.compressedBlocksInfoSize()).isEqualTo(318);
        assertThat(header.uncompressedBlocksInfoSize()).isEqualTo(601);
        assertThat(header.flags().getCompressionType()).isEqualTo(Unity3dCompressionType.LZ4HC);
    }


}