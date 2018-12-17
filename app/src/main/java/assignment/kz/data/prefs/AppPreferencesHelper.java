package assignment.kz.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;

    private static final String PREF_KEY_POST_INDEX = "PREF_KEY_POST_INDEX";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_BIN = "PREF_KEY_BIN";
    private static final String PREF_KEY_NOTIFICATION = "PREF_KEY_NOTIFICATION";
    private static final String PREF_KEY_USER_ROLE = "PREF_KEY_USER_ROLE";
    private static final String PREF_KEY_USER_LOCATION_ID = "PREF_KEY_USER_LOCATION_ID";
    private static final String PREF_KEY_ORGANIZATION_NAME = "PREF_KEY_ORGANIZATION_NAME";
    private static final String PREF_KEY_SPINNER_POSITION = "PREF_KEY_SPINNER_POSITION";


    @Inject
    public AppPreferencesHelper(Context context,
                                String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "");
    }

    @Override
    public void putAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }


}