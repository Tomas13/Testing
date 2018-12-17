package assignment.kz.ui.main

import com.domain.Photo
import javax.inject.Inject

open class PhotoViewModelMapper @Inject internal constructor() {
    open operator fun invoke(photo: Photo) = PhotoViewModel(
            id = photo.id,
            link = photo.link,
            title = photo.title
    )
}
