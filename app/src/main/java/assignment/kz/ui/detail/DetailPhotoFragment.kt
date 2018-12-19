package assignment.kz.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import assignment.kz.R
import assignment.kz.databinding.FragmentDetailPhotoBinding
import kotlinx.android.synthetic.main.fragment_detail_photo.*

class DetailPhotoFragment : Fragment() {

    companion object {
        fun newInstance() = DetailPhotoFragment()
    }

    private lateinit var viewModel: DetailPhotoViewModel
    private lateinit var binding: FragmentDetailPhotoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_photo, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailPhotoViewModel::class.java)

        binding.vm = viewModel
        viewModel.extractPhotoId(arguments)

        image_back.setOnClickListener {
            activity?.finish()
        }
    }
}
