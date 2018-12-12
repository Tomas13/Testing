package kz.senim.ui.dialog.type

import android.support.annotation.StringRes
import android.support.v4.content.FileProvider
import malmalimet.kz.R
import malmalimet.kz.ui.widget.select.SelectOption
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1
import java.io.File
import java.util.*

/**
 * A menu dialog which lets the user to select file source for uploading.
 */
open class FileUploadDialog(protected var fileProvider: FileProvider) : MenuDialog() {

    private var onFileSelected: Action1<Observable<File>>? = null
    private var onMultipleFilesSelected: Action1<Observable<List<File>>>? = null
    private var onDelete: Action0? = null
    private var shouldCrop = false
    private var mimeTypes: Array<String>? = null
    private var allowMultiple = false

    init {
        setTitleRes(R.string.action_choose_action)

        onSelect { option ->
            when (option.optionId) {
                CAMERA -> takePhoto()
                FILE_SYSTEM -> selectFromFileSystem()
                DELETE -> if (onDelete != null) {
                    onDelete?.call()
                }
            }
        }
    }

    open fun onFileSelected(onFileSelected: Action1<Observable<File>>): FileUploadDialog {
        this.onFileSelected = onFileSelected
        setItems(makeOptions())
        return this
    }

    open fun onMultipleFilesSelected(onMultipleFilesSelected: Action1<Observable<List<File>>>): FileUploadDialog {
        this.onMultipleFilesSelected = onMultipleFilesSelected
        setItems(makeOptions())
        return this
    }

    open fun onDelete(onDelete: Action0): FileUploadDialog {
        this.onDelete = onDelete
        setItems(makeOptions())
        return this
    }

    open fun setShouldCrop(shouldCrop: Boolean): FileUploadDialog {
        this.shouldCrop = shouldCrop
        return this
    }

    open fun setMimeTypes(mimeTypes: Array<String>): FileUploadDialog {
        this.mimeTypes = mimeTypes
        return this
    }

    open fun setAllowMultiple(): FileUploadDialog {
        allowMultiple = true
        return this
    }

    private fun takePhoto() {
        if (shouldCrop) {
//            onFileSelected?.call(fileProvider.takePhotoAndCrop())
        } else {
//            onFileSelected?.call(fileProvider.takePhoto())
        }
    }

    private fun selectFromFileSystem() {
        when {
//            shouldCrop -> onFileSelected?.call(fileProvider.pickImageFromGalleryAndCrop())
//            allowMultiple -> onMultipleFilesSelected?.call(fileProvider.chooseMultipleFiles(mimeTypes))
//            else -> onFileSelected?.call(fileProvider.chooseFile(mimeTypes))
        }
    }

    private fun makeOptions(): List<SelectOption> {
        val options = ArrayList<SelectOption>()

        if (onFileSelected != null) {
            options.add(CAMERA_OPTION)
            options.add(FILE_SYSTEM_OPTION)
        } else if (onMultipleFilesSelected != null) {
            options.add(FILE_SYSTEM_OPTION)
        }

        if (onDelete != null) {
            options.add(DELETE_OPTION)
        }

        return options
    }
}

private class UploadOption(
        private val id: Int,
        @StringRes private val titleRes: Int
) : SelectOption {

    override fun getOptionId(): Int {
        return id
    }

    override fun getTitleRes(): Int {
        return titleRes
    }

    override fun getTitle(): String? {
        return null
    }
}

private const val CAMERA = 2
private const val DELETE = 3
private const val FILE_SYSTEM = 4

private val CAMERA_OPTION = UploadOption(CAMERA, R.string.action_capture_image)
private val FILE_SYSTEM_OPTION = UploadOption(FILE_SYSTEM, R.string.action_load_from_file_system)
private val DELETE_OPTION = UploadOption(DELETE, R.string.action_delete_photo)
