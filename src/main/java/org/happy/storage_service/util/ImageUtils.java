package org.happy.storage_service.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Slf4j
public class ImageUtils {

    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)){
            byte[] temp = new byte[4*1024];

            outer:
                while (!deflater.finished()) {
                    int size = deflater.deflate(temp);
                    if(size==0 && deflater.needsInput()) {
                        break outer;
                    }
                    outputStream.write(temp, 0, size);
                }

            deflater.end();
            return outputStream.toByteArray();
        }
        catch (IOException e) {
            log.error("Compression failed {}", String.valueOf(e));
            return null;
        }
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] temp = new byte[4*1024];
        while (!inflater.finished()) {
            int size = inflater.inflate(temp);
            outputStream.write(temp, 0, size);
        }
        try {
            outputStream.close();
        }
        catch (Exception ignore) {}
        return outputStream.toByteArray();

    }
}
