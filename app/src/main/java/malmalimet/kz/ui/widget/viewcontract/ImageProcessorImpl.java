package malmalimet.kz.ui.widget.viewcontract;

import android.content.Context;

import java.io.File;
import java.io.IOException;

import malmalimet.kz.utils.ImageUtils;


public class ImageProcessorImpl implements ImageProcessor {

    private Context mContext;

    public ImageProcessorImpl(Context context) {
        mContext = context;
    }

    @Override
    public File shrinkImageFile(File originalFile) throws IOException {
        return ImageUtils.shrinkImageFile(mContext, originalFile);
    }
}
