package assignment.kz.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class DetailPhotoActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, photoId: String): Intent =
                Intent(context, DetailPhotoActivity::class.java).apply {
                    putExtra(KEY_PHOTO_ID, photoId)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = DetailPhotoFragment()
            fragment.arguments = intent.extras

            supportFragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .commit()
        }
    }
}
