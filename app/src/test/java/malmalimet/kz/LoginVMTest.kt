package malmalimet.kz

import junit.framework.Assert.assertEquals
import malmalimet.kz.data.SupermarketRepository
import malmalimet.kz.ui.login.LoginViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginVMTest {


    @Mock
    lateinit var loginViewModel: LoginViewModel

    @InjectMocks
    lateinit var supermarketRepository: SupermarketRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
//        loginViewModel = Mockito.mock(LoginViewModel::class.java)
//        supermarketRepository = Mockito.mock(SupermarketRepository::class.java)
//        loginViewModel.supermarketRepository = supermarketRepository
    }

    @Test
    fun login() {
        val username = "admin"
        val pswd = "qwerty"
        val data = "$username:$pswd"
//        val base64 = Base64.encodeToString(data.toByteArray(), Base64.DEFAULT)

        loginViewModel.login(data)


        assertEquals("showProgress isn't true", true, loginViewModel.showProgress.value)
    }
}