package assignment.kz.ui.main

import assignment.kz.data.network.model.Photo
import javax.inject.Inject

open class PhotoViewModelMapper @Inject internal constructor() {
    open operator fun invoke(photo: Photo) = PhotoViewModel(
            id = photo.id,
            link = photo.url_s,
            title = photo.title
    )
}
