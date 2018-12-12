package malmalimet.kz.data;

import java.io.File;
import java.io.Serializable;

import malmalimet.kz.data.network.RemoteImage;

/**
 * Wrapper for image types. Can wrap remote images as well as local images, for convenience.
 */
public class Image implements Serializable {

    private static final long serialVersionUID = 8580029604357811229L;

    public static final int TYPE_REMOTE = 1;
    public static final int TYPE_LOCAL = 2;
    public static final int TYPE_RESOURCE = 3;

    // Either remote or local.
    public int type;

    public RemoteImage remoteImage;
    public File file;
    public int resourceId;

    public Image(File file) {
        this.type = TYPE_LOCAL;
        this.file = file;
    }

    public Image(RemoteImage remoteImage) {
        this.type = TYPE_REMOTE;
        this.remoteImage = remoteImage;
    }

    public Image(int resourceId) {
        this.type = TYPE_RESOURCE;
        this.resourceId = resourceId;
    }

    public static Image fromUrl(String url) {
        return new Image(new RemoteImage(url));
    }

    public static Image fromFullUrl(String url) {
        return new Image(new RemoteImage(url, true));
    }

    /**
     * Return true if this image is a remote image.
     */
    public boolean isRemote() {
        return type == TYPE_REMOTE;
    }
}
