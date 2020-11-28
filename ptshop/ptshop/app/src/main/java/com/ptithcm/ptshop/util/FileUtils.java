package com.ptithcm.ptshop.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
public class FileUtils {
    public static String getOutputFilePath(Bitmap.CompressFormat compressFormat, String outputDirPath, String outputFilename, File sourceImage) {
        String originalFileName = sourceImage.getName();
        String targetFileName;
        String targetFileExtension = "." + compressFormat.name().toLowerCase(Locale.US).replace("jpeg", "jpg");

        if (outputFilename == null) {
            int extensionIndex = originalFileName.lastIndexOf('.');
            if (extensionIndex == -1) {
                targetFileName = originalFileName + targetFileExtension;
            } else {
                targetFileName = originalFileName.substring(0, extensionIndex) + targetFileExtension;
            }
        } else {
            targetFileName = outputFilename + targetFileExtension;
        }

        return outputDirPath + File.separator + targetFileName;
    }

    public static void writeBitmapToFile(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality, String filePath) throws IOException {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filePath);
            bitmap.compress(compressFormat, quality, fileOutputStream);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
    }

    public static Hashtable<String, Object> getFileInfo(Context context, Uri uri) {
        try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null)) {
            String mime = context.getContentResolver().getType(uri);
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "sendbird");

            ParcelFileDescriptor inputPFD = context.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fd = null;
            if (inputPFD != null) {
                fd = inputPFD.getFileDescriptor();
            }
            FileInputStream inputStream = new FileInputStream(fd);
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            if (cursor != null) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                Hashtable<String, Object> value = new Hashtable<>();

                if (cursor.moveToFirst()) {
                    String name = cursor.getString(nameIndex);
                    int size = (int) cursor.getLong(sizeIndex);

                    value.put("path", file.getPath());
                    value.put("size", size);
                    value.put("mime", mime);
                    value.put("name", name);
                }
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(e.getLocalizedMessage(), "File not found.");
            return null;
        }
        return null;
    }

    public static String getMimeType(String url) {
        try {
            String extension = url.substring(url.lastIndexOf(".") + 1);
            Log.i("extension", "ext : " + extension);
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}