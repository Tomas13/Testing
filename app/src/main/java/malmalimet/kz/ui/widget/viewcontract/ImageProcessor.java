package malmalimet.kz.ui.widget.viewcontract;

import java.io.File;
import java.io.IOException;

public interface ImageProcessor {
    File shrinkImageFile(File originalFile) throws IOException;
}
