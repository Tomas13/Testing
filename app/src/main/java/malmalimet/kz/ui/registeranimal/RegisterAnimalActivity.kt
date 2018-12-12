package malmalimet.kz.ui.registeranimal

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import malmalimet.kz.R
import malmalimet.kz.ui.widget.viewcontract.ViewContractActivity

class RegisterAnimalActivity : ViewContractActivity() {

    private lateinit var viewModel: RegisterAnimalViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_animal)
//        val binding = DataBindingUtil.setContentView<ActivityRegisterAnimalBinding>(this, R.layout.activity_main)

//        setSupportActionBar(toolbar_register_animal)
        supportActionBar?.title = "Регистрация животного"

        viewModel = ViewModelProviders.of(this).get(RegisterAnimalViewModel::class.java)

    }

}
