package assignment.kz.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import assignment.kz.App
import assignment.kz.R
import assignment.kz.databinding.FragmentMainBinding
import assignment.kz.di.SchedulerFactory
import assignment.kz.ui.detail.DetailPhotoActivity
import com.domain.*
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var getPhotos: GetPhotos
    @Inject
    lateinit var queryRepository: QueryRepository
    @Inject
    lateinit var photoRepository: PhotoRepository
    @Inject
    lateinit var schedulerFactory: SchedulerFactory

    val suggestions = ArrayList<String?>()

    init {
        App.getApp().getmDiComponent().inject(this)
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        photoRepository.getRecent().subscribeOn(Schedulers.io())
                .observeOn(schedulerFactory.mainScheduler)
                .subscribe { result ->
                    when (result) {
//                        is Busy -> isLoading.set(true)
                        is Success<Photos> -> {
                            result.value.forEachIndexed { index, photo ->
                                suggestions.add(photo.title)
                            }
                        }
                        is Failure -> {
                        }
                    }
                }

        suggestions.add("He")
        suggestions.add("Hes")
        suggestions.add("Novak")

        val adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, suggestions)
        autoCompleteTextView1.setAdapter(adapter)

        autoCompleteTextView1.threshold = 0
        autoCompleteTextView1.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                viewModel.onQueryTextSubmit(v.text.toString())
                handled = true
            } else
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.onQueryTextSubmit(v.text.toString())
                    handled = true
                }
            handled
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.vm = viewModel

        binding.toolbarMain.title = "Недавние"

        viewModel.loadPhotos(savedInstanceState)

        viewModel.onPhotoTapped.subscribe {
            startActivity(DetailPhotoActivity.newIntent(activity!!.applicationContext, it))

//            val bundle = Bundle()
//            bundle.putString(KEY_PHOTO_ID, it)
//
//            findNavController().navigate(R.id.detailPhotoFragment, bundle)
        }
    }
}
