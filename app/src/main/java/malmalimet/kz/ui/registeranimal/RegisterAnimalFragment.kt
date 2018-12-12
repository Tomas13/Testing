package malmalimet.kz.ui.registeranimal

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import malmalimet.kz.R
import malmalimet.kz.databinding.FragmentRegisterAnimalBinding


class RegisterAnimalFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterAnimalFragment()
    }

    private lateinit var viewModel: RegisterAnimalViewModel
    private lateinit var binding: FragmentRegisterAnimalBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_animal, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(RegisterAnimalViewModel::class.java)
        binding.vm = viewModel

        setObservers()
        setViewListeners()
    }


    private fun setViewListeners() {
    }

    private fun setObservers() {
/*
        viewModel.showProgress.observe(this, android.arch.lifecycle.Observer {
            if (it != null) {
                if (it) {
                    progress_relative_layout_register.showLoading()
                } else {
                    progress_relative_layout_register.showContent()
                    activity?.finish()
//                    findNavController().popBackStack()
                }
            }
        })
*/
    }
}
