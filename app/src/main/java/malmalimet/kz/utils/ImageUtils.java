package malmalimet.kz.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility methods for working with images.
 */
public class ImageUtils {

    // Maximum image dimension used in the `shrinkImageFile` method below.
    private static final int MAX_IMAGE_DIMENSION = 1500;
    // JPEG compression quality to be used when shrinking an image.
    private static final int JPEG_QUALITY = 80;

    /**
     * Tint the image contained in the given imageView using the given color.
     * Works by setting imageView's color filter.
     *
     * @param imageView - the imageView to tint.
     * @param color     - the tint color.
     */
    public static void setTint(ImageView imageView, @ColorRes int color) {
        if (color != 0) {
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), color));
        } else {
            imageView.setColorFilter(null);
        }
    }

    public static void clearTint(ImageView imageView) {
        imageView.setColorFilter(null);
    }

    /**
     * Shrink the image file by downsampling it so that its largest dimension is at most MAX_IMAGE_DIMENSION,
     * and compressing it using JPEG compression.
     *
     * @param context   Any context.
     * @param imageFile The image file to shrink.
     * @return The shrunk file.
     * @throws IOException If failed to create temporary file for the shrunk image.
     */
    public static File shrinkImageFile(Context context, File imageFile) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(imageFile), null, options);

        int scale = 1;
        while (options.outWidth / scale >= MAX_IMAGE_DIMENSION ||
                options.outHeight / scale >= MAX_IMAGE_DIMENSION) {
            scale += 1;
        }

        BitmapFactory.Options opt2 = new BitmapFactory.Options();
        opt2.inDensity = scale;
        opt2.inTargetDensity = 1;

        Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(imageFile), null, opt2);

        File outputFile = FileUtils.createImageFile(context);
        FileOutputStream stream = new FileOutputStream(outputFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, stream);

        return outputFile;
    }

    /**
     * Shrink the image uri by downsampling it so that its largest dimension is at most MAX_IMAGE_DIMENSION,
     * and compressing it using JPEG compression.
     *
     * @param context  Any context.
     * @param imageUri The image uri to shrink.
     * @return The shrunk file.
     * @throws IOException If failed to create temporary file for the shrunk image.
     */
    public static File shrinkImageFile(Context context, Uri imageUri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
        if (inputStream == null) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);

        int scale = 1;
        while (options.outWidth / scale >= MAX_IMAGE_DIMENSION ||
                options.outHeight / scale >= MAX_IMAGE_DIMENSION) {
            scale += 1;
        }

        BitmapFactory.Options opt2 = new BitmapFactory.Options();
        opt2.inDensity = scale;
        opt2.inTargetDensity = 1;

        inputStream = context.getContentResolver().openInputStream(imageUri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, opt2);

        File outputFile = FileUtils.createExternalImageFile(context);
        FileOutputStream stream = new FileOutputStream(outputFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, stream);

        return outputFile;
    }

    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        if (byteArray != null && byteArray.length > 0) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        return null;
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        return stream.toByteArray();
    }
}
