package assignment.kz.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import assignment.kz.R;


public class ImageViewBindings {

    // The drawable for the placeholder to be shown while an image is being downloaded.
    @DrawableRes
    private static final int DEFAULT_PLACEHOLDER_DRAWABLE_RES = R.drawable.shape_rect_light_gray;
//    private static final int DEFAULT_PLACEHOLDER_DRAWABLE_RES = R.drawable.loading_photo;

    @BindingAdapter("imageView_tint")
    public static void setDrawableTint(ImageView imageView, int tintColorRes) {
        ImageUtils.setTint(imageView, tintColorRes);
    }

    @BindingAdapter(value = {"imageView_loadUrl", "imageView_loadUrlThumbnail", "imageView_loadUrlNoPlaceholder"}, requireAll = false)
    public static void loadUrl(ImageView imageView, String mainUrl, String thumbnailUrl, boolean noPlaceholder) {
        loadUrlInternal(imageView, mainUrl, thumbnailUrl, noPlaceholder);
    }

    @BindingAdapter("imageView_loadUrl")
    public static void loadUrl(ImageView imageView, String mainUrl) {
        Picasso.with(imageView.getContext().getApplicationContext())
                .load(mainUrl);
    }

    private static void loadUrlInternal(ImageView imageView, String mainUrl, String thumbnailUrl, boolean noPlaceholder) {

        Picasso.with(imageView.getContext().getApplicationContext()).cancelRequest(imageView);
        imageView.setImageDrawable(null);

        if (mainUrl != null && !mainUrl.isEmpty()) {

            String url = thumbnailUrl != null ? thumbnailUrl : mainUrl;

            RequestCreator creator = Picasso.with(imageView.getContext().getApplicationContext())
                    .load(url);

            if (!noPlaceholder) {
//                creator.placeholder(DEFAULT_PLACEHOLDER_DRAWABLE_RES);
//                imageView.setBackgroundResource(DEFAULT_PLACEHOLDER_DRAWABLE_RES);
            }

            if (thumbnailUrl != null) {
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
            } else {
                creator.into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setBackground(null);
                    }

                    @Override
                    public void onError() {
                    }
                });
            }
        }
    }


}
