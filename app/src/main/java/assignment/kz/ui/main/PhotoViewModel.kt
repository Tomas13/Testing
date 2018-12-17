package assignment.kz.ui.main

import assignment.kz.ui.TapAction
import java.util.*

typealias PhotoId = String

data class PhotoViewModel(
        val id: PhotoId = UUID.randomUUID().toString(),
        val link: String,
        val title: String? = null
) {
    val tapAction: TapAction<PhotoViewModel> = TapAction.create { this }
}
