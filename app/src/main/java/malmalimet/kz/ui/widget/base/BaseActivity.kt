package malmalimet.kz.ui.widget.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import malmalimet.kz.R
import rx.subscriptions.CompositeSubscription

/**
 * Some base functionality and helper methods that should be implemented by all activities.
 *
 * DO NOT EXTEND THIS CLASS DIRECTLY. Extend [ViewContractActivity] instead.
 */
abstract class BaseActivity : AppCompatActivity() {

//    @Inject
//    lateinit var prefs: SharedPreferencesProvider

    /**
     * Handles the result of calling `startActivityForResult`.
     */
//    val activityResultHandler = ActivityResultHandler()

    /**
     * Handles the result of calling `requestPermissions`.
     */
//    val requestPermissionResultHandler = RequestPermissionResultHandler()

//    private var networkReceiver: NetworkReceiver? = null
    private var canHandleConnectionEvents: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
//            R.id.item_profile -> {
//                startActivity(Intent(this, ProfileActivity::class.java))
//                return true
//            }
        }
        return super.onOptionsItemSelected(item)
    }

//    private val lifecycleListeners = mutableListOf<LifecycleListener>()

//    protected val isMainActivityBroughtToFront: Boolean
//        get() = (this is MainActivity) &&
//                ((getIntent().flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0)
//
    /**
     * True if this activity should be protected by the PIN lock.
     */
//    private val isLockedActivity: Boolean
//        get() = !(this is PinCodeActivity || this is RegistrationActivity)
//
    /**
     * The application instance.
     */
//    protected val app: App
//        get() = application as App

    /**
     * Return the application-scoped component.
     */
//    val appInjector: AppInjector
//        get() = app.appInjector
//
    private val subs = CompositeSubscription()

    // Make it final so that it is not confused with the other method.
    final override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is a workaround for the case when the app is launched first from
        // Google Play and such, then from the home screen launcher.
        // In these cases the activity is duplicated, and so we finish the
        // newly created activity.
        //
        // Note that onDestroy callback will still be called, and our `lateinit`
        // variables may not be initialized yet, so you have to check for that.
//        if (isMainActivityBroughtToFront) {
//            finish()
//            return
    }

//        Timber.d("***** CREATE %s", javaClass.name)
//
//        title = null
}

//    override fun onDestroy() {
//        super.onDestroy()
//
//        Timber.d("vvvvv DESTROY %s", javaClass.name)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        lifecycleListeners.forEach(LifecycleListener::onStart)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        lifecycleListeners.forEach(LifecycleListener::onStop)
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//         Register the network receiver.
//        networkReceiver = NetworkReceiver(appInjector)
//        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(networkReceiver, filter)

//        val app = this.app
//        if (prefs.isPinEnabled && isLockedActivity) {
//            if (app.wasInBackground() || !app.isUnlocked) {
//                showLockScreen()
//            }
//        }
//
//        if (isLockedActivity || this is PinCodeActivity) {
//            app.stopActivityTransitionTimer()
//        }
//
//        lifecycleListeners.forEach(LifecycleListener::onResume)
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        if (networkReceiver != null) {
//            unregisterReceiver(networkReceiver)
//            networkReceiver = null
//        }
//
//
//        if (prefs.isPinEnabled && isLockedActivity) {
//            app.startActivityTransitionTimer()
//        }
//
//        lifecycleListeners.forEach(LifecycleListener::onPause)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            finish()
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        overridePendingTransition(R.anim.none, R.anim.activity_close_exit)
//    }
//
//    fun addLifecycleListener(listener: LifecycleListener) {
//        lifecycleListeners.add(listener)
//    }
//
//    fun removeLifecycleListener(listener: LifecycleListener) {
//        lifecycleListeners.remove(listener)
//    }
//
/**
 * Show the PIN code screen and block the user from interacting with the app.
 */
//    private fun showLockScreen() {
//        val pinCodeManager = PinCodeManager.getInstance()
//        if (pinCodeManager.isEnabled) {
//            val data = Bundle()
//            data.putInt(PinCodeActivity.EXTRA_ACTION, PinCodeActivity.ACTION_UNLOCK)
//            val intent = Intent(this, PinCodeActivity::class.java)
//            intent.putExtras(data)
//            startActivity(intent)
//        }
//    }

/**
 * Return true if this activity can handle connection events, such as
 * whether network was connected or disconnected. If the activity doesn't
 * handle these events, then [EventHandler] will display a snackbar when
 * one of them is received.
 */
//    fun canHandleConnectionEvents(): Boolean {
//        return canHandleConnectionEvents
//    }

/**
 * Extending activities can specify whether they want to handle connection
 * by themselves, without delegating the handling to [EventHandler]
 */
//    protected fun setCanHandleConnectionEvents() {
//        canHandleConnectionEvents = true
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        activityResultHandler.handleActivityResult(requestCode, resultCode, data)
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        requestPermissionResultHandler.handleRequestPermissionResult(permissions, grantResults)
//    }

/**
 * Set the given toolbar as the support ActionBar.
 *
 * You can probably do without calling this method, by using a custom
 * toolbar with data binding instead.
 */
//    protected fun setUpActionBar(toolbar: Toolbar, setDisplayHomeAsUpEnabled: Boolean) {
//        title = null
//        setSupportActionBar(toolbar)
//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled)
//    }
//
//    @Deprecated("Don't use ActionBarLayout")
//    protected fun setUpActionBar(actionBarLayout: ActionBarLayout, setDisplayHomeAsUpEnabled: Boolean) {
//        setSupportActionBar(actionBarLayout.toolbar)
//        if (actionBarLayout.hasTitle()) {
//            title = null
//        }
//        if (setDisplayHomeAsUpEnabled) {
//            if (actionBarLayout.hasTitle()) {
//                actionBarLayout.setHasBackButton()
//            }
//            val actionBar = supportActionBar
//            actionBar?.setDisplayHomeAsUpEnabled(true)
//        }
//    }

/////////////////////////////////////////////////////////////////////////
// Methods to be implemented by child activities, if necessary.

/**
 * Return the particular instance of ActivityInjector, created
 * by the extending activity.
 */
//    open fun getActivityInjector(): ActivityInjector? {
//        return null
//    }

/**
 * If the activity layout uses CoordinatorLayout as its root, then
 * it should return its instance in this method, so that snackbars
 * can be shown within it. This enables some default behaviors that
 * the android design library provides.
 */
//    open fun getCoordinatorLayout(): CoordinatorLayout? {
//        return null
//    }
//
//    open fun getRootLayout(): ViewGroup {
//        return this.getCoordinatorLayout() ?: return findViewById(android.R.id.content)
//    }

