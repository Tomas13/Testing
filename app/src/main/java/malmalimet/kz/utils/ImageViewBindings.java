package malmalimet.kz.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

import malmalimet.kz.R;
import malmalimet.kz.data.Image;

public class ImageViewBindings {

    // The drawable for the placeholder to be shown while an image is being downloaded.
    @DrawableRes
    private static final int DEFAULT_PLACEHOLDER_DRAWABLE_RES = R.drawable.shape_rect_light_gray;
//    private static final int DEFAULT_PLACEHOLDER_DRAWABLE_RES = R.drawable.loading_photo;

    /**
     * Load the given file into the ImageView.
     *
     * @param imageView the ImageView.
     * @param imageFile the image file.
     */
    @BindingAdapter("imageView_file")
    public static void setImageFile(ImageView imageView, File imageFile) {
        if (imageFile != null) {
            Picasso.with(imageView.getContext())
                    .load(imageFile)
                    .fit()
                    .centerInside()
                    .into(imageView);
        }
    }

    /**
     * Set the image into the given ImageView.
     * <p>
     * If the provided Image object references a remote image, then it is downloaded.
     * If the remote image has a thumbnail URL, then the thumbnail is downloaded first, and set as
     * placeholder while the main image is being downloaded.
     * <p>
     * If it references a local image file, it is immediately set into the ImageView.
     *
     * @param imageView the ImageView.
     * @param image     the Image object to load.
     */
    @BindingAdapter("imageView_loadImage")
    public static void setImage(ImageView imageView, Image image) {
        if (image != null) {
            switch (image.type) {
                case Image.TYPE_LOCAL:
                    setImageFile(imageView, image.file);
                    break;
                case Image.TYPE_REMOTE:
                    if (image.remoteImage != null) {
                        if (image.remoteImage.hasFullUrl) {
                            loadFullUrl(imageView, image.remoteImage.mainUrl, image.remoteImage.thumbnailUrl, true);
                        } else {
                            loadUrl(imageView, image.remoteImage.mainUrl, image.remoteImage.thumbnailUrl, true);
                        }
                    }
                    break;
                case Image.TYPE_RESOURCE:
                    if (image.resourceId != 0) {
                        imageView.setImageResource(image.resourceId);
                    }
                    break;
            }
        }
    }

    /**
     * Load only the thumbnail URL from the given Image, if it is a remote image.
     *
     * @param imageView the ImageView.
     * @param image     the Image.
     */
    @BindingAdapter("imageView_loadImageThumbnail")
    public static void setImageThumbnail(ImageView imageView, Image image) {
        if (image != null) {
            switch (image.type) {
                case Image.TYPE_LOCAL:
                    setImageFileThumbnail(imageView, image.file);
                    break;
                case Image.TYPE_REMOTE:
                    if (image.remoteImage != null) {
                        loadUrl(imageView, image.remoteImage.thumbnailUrl, null, false);
                    }
                    break;
                case Image.TYPE_RESOURCE:
                    imageView.setImageResource(image.resourceId);
                    break;
            }
        }
    }

    private static void setImageFileThumbnail(ImageView imageView, File imageFile) {
        if (imageFile != null) {
            Picasso.with(imageView.getContext())
                    .load(imageFile)
                    .fit()
                    .centerCrop()
                    .into(imageView);
        }
    }

    @BindingAdapter("imageView_src")
    public static void setDrawableResource(ImageView imageView, int drawableRes) {
        if (drawableRes != 0) {
            imageView.setImageResource(drawableRes);
        } else {
            imageView.setImageDrawable(null);
        }
    }

    @BindingAdapter("imageView_tint")
    public static void setDrawableTint(ImageView imageView, int tintColorRes) {
        ImageUtils.setTint(imageView, tintColorRes);
    }

    @BindingAdapter(value = {"imageView_loadUrl", "imageView_loadUrlThumbnail", "imageView_loadUrlNoPlaceholder"}, requireAll = false)
    public static void loadUrl(ImageView imageView, String mainUrl, String thumbnailUrl, boolean noPlaceholder) {
        loadUrlInternal(imageView, mainUrl, thumbnailUrl, noPlaceholder);
    }

    @BindingAdapter(value = {"imageView_loadFullUrl", "imageView_loadFullUrlThumbnail", "imageView_loadFullUrlNoPlaceholder"}, requireAll = false)
    public static void loadFullUrl(ImageView imageView, String mainUrl, String thumbnailUrl, boolean noPlaceholder) {
        loadUrlInternal(imageView, mainUrl, thumbnailUrl, noPlaceholder);
    }

    private static void loadUrlInternal(ImageView imageView, String mainUrl, String thumbnailUrl, boolean noPlaceholder) {

        Picasso.with(imageView.getContext().getApplicationContext()).cancelRequest(imageView);
        imageView.setImageDrawable(null);

        if (mainUrl != null && !mainUrl.isEmpty()) {

            String url = thumbnailUrl != null ? thumbnailUrl : mainUrl;

            RequestCreator creator = Picasso.with(imageView.getContext().getApplicationContext())
                    .load(url);

            if (!noPlaceholder) {
                creator.placeholder(DEFAULT_PLACEHOLDER_DRAWABLE_RES);
                imageView.setBackgroundResource(DEFAULT_PLACEHOLDER_DRAWABLE_RES);
            }

            creator.into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.setBackground(null);
                    Picasso.with(imageView.getContext().getApplicationContext())
                            .load(mainUrl)
                            .fit()
                            .centerInside()
                            .placeholder(imageView.getDrawable())
                            .error(R.drawable.ic_no_photo)
                            .into(imageView);
                }

                @Override
                public void onError() {
                    Picasso.with(imageView.getContext().getApplicationContext())
                            .load(R.drawable.ic_no_photo)
                            .fit()
                            .centerInside()
                            .into(imageView);
                }
            });
        } /*else {
            creator.into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.setBackground(null);
                }

                @Override
                public void onError() {
                }
            });
        }*/

    }

    @BindingAdapter("imageView_bitmap")
    public static void setBitmap(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageDrawable(null);
        }
    }

    @BindingAdapter("imageView_drawable")
    public static void setImageDrawable(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

/*
    @BindingAdapter("imageView_disabled")
    public static void setImageDisabled(ImageView imageView, boolean inactive) {
        if (inactive) {
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.textColorSubtleDark), PorterDuff.Mode.SRC_IN);
        } else {
            imageView.setColorFilter(null);
        }
    }
*/


}
