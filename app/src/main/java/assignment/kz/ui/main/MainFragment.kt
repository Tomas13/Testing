package assignment.kz.ui.main

import android.arch.lifecycle.Observer
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
import com.domain.GetPhotos
import com.domain.PhotoRepository
import com.domain.QueryRepository
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
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

        autoCompleteTextView1.threshold = 0
        autoCompleteTextView1.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                viewModel.saveToRecents(v.text.toString())
                viewModel.onQueryTextSubmit(v.text.toString())
                handled = true
            } else
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.onQueryTextSubmit(v.text.toString())
                    handled = true
                }
            handled
        }

        image_cross.setOnClickListener {
            Timber.d("click")
            autoCompleteTextView1.clearComposingText()
            autoCompleteTextView1.text = null
            viewModel.getSuggestions()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.vm = viewModel
        binding.toolbarMain.title = "Недавние"

        viewModel.getSuggestions()

        viewModel.loadPhotos(savedInstanceState)

        viewModel.suggestions.observe(this, Observer {

            val adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1,
                    it)
            autoCompleteTextView1.setAdapter(adapter)
        })

        viewModel.onPhotoTapped.subscribe {
            startActivity(DetailPhotoActivity.newIntent(activity!!.applicationContext, it))
        }
    }
}
