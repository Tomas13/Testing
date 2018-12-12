package kz.senim.ui.dialog.type

import android.support.v4.content.FileProvider
import java.io.File

import rx.Observable
import rx.functions.Action0
import rx.functions.Action1

/**
 * A menu dialog for selecting an image to upload.
 */
class ImageUploadDialog(fileProvider: FileProvider) : FileUploadDialog(fileProvider) {

    init {
        setMimeTypes(arrayOf("image/*"))
    }

    override fun onFileSelected(onFileSelected: Action1<Observable<File>>): ImageUploadDialog {
        super.onFileSelected(onFileSelected)
        return this
    }

    override fun onMultipleFilesSelected(onMultipleFilesSelected: Action1<Observable<List<File>>>): ImageUploadDialog {
        super.onMultipleFilesSelected(onMultipleFilesSelected)
        return this
    }

    override fun onDelete(onDelete: Action0): ImageUploadDialog {
        super.onDelete(onDelete)
        return this
    }

    override fun setShouldCrop(shouldCrop: Boolean): ImageUploadDialog {
        super.setShouldCrop(shouldCrop)
        return this
    }

    override fun setMimeTypes(mimeTypes: Array<String>): ImageUploadDialog {
        super.setMimeTypes(mimeTypes)
        return this
    }

    override fun setAllowMultiple(): ImageUploadDialog {
        super.setAllowMultiple()
        return this
    }
}
