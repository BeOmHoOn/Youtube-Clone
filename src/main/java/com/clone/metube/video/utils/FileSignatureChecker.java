package com.clone.metube.video.utils;

import com.clone.metube.global.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class FileSignatureChecker {
    private static final Map<FileType, byte[]> signatures = Map.of(
            FileType.MP4, new byte[]{0x66, 0x74, 0x79, 0x70}
            /*
            "avi", new byte[]{0x52, 0x49, 0x46, 0x46},
            "mov", new byte[]{0x66, 0x74, 0x79, 0x70, 0x71, 0x74},
            "mkv", new byte[]{(byte) 0x1A, 0x45, (byte) 0xDF, (byte) 0xA3},
            "webm", new byte[]{(byte) 0x1A, 0x45, (byte) 0xDF, (byte) 0xA3},
            "flv", new byte[]{0x46, 0x4C, 0x56},
            "wmv", new byte[]{0x30, 0x26, (byte) 0xB2, 0x75},
            "3gp", new byte[]{0x66, 0x74, 0x79, 0x70, 0x33, 0x67, 0x70},
            "mpeg", new byte[]{0x00, 0x00, 0x01, (byte) 0xBA}
             */
    );

    public static boolean checkSignature(MultipartFile file) {
        if(file == null || file.isEmpty()) {
            return false;
        }

        try (InputStream is = file.getInputStream()) {
            byte[] header = new byte[16];
            int readLength = is.read(header);

            if (readLength == -1) {
                return false;
            }

            var fromOverHeader = Arrays.copyOfRange(header, 4, header.length);

            for (var entry : signatures.entrySet()) {
                byte[] signature = entry.getValue();

                if (startsWith(fromOverHeader, signature)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean startsWith(byte[] source, byte[] prefix) {
        if (source.length < prefix.length) return false;
        for (int i = 0; i < prefix.length; i++) {
            if (source[i] != prefix[i]) return false;
        }
        return true;
    }
}
