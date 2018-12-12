package malmalimet.kz.data.prefs;

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
    public void saveNotificationData(String string) {
        mPrefs.edit().putString(PREF_KEY_NOTIFICATION, string).apply();
    }

    @Override
    public String getNotificationData() {
        return mPrefs.getString(PREF_KEY_NOTIFICATION, "");
    }

    @Override
    public void savePostIndex(String postIndex) {
        mPrefs.edit().putString(PREF_KEY_POST_INDEX, postIndex).apply();
    }

    @Override
    public String getPostIndex() {
        return mPrefs.getString(PREF_KEY_POST_INDEX, "Не выбрано");
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "");
    }

    @Override
    public String getBin() {
        return mPrefs.getString(PREF_KEY_BIN, null);
    }

    @Override
    public String getOrganizationName() {
        return mPrefs.getString(PREF_KEY_ORGANIZATION_NAME, null);
    }

    @Override
    public String getUserRole() {
        return mPrefs.getString(PREF_KEY_USER_ROLE, null);
    }

    @Override
    public Integer getUserLocationId() {
        return mPrefs.getInt(PREF_KEY_USER_LOCATION_ID, 0);
    }

    @Override
    public void putAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public void putRole(String userRole) {
        mPrefs.edit().putString(PREF_KEY_USER_ROLE, userRole).apply();
    }

    @Override
    public void putBin(String bin) {
        mPrefs.edit().putString(PREF_KEY_BIN, bin).apply();
    }

    @Override
    public void putUserLocationId(Integer id) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOCATION_ID, id).apply();
    }

    @Override
    public void putOrganizationName(String organizationName) {
        mPrefs.edit().putString(PREF_KEY_ORGANIZATION_NAME, organizationName).apply();
    }

    @Override
    public void removeAllData() {
        mPrefs.edit().remove(PREF_KEY_USER_LOCATION_ID).apply();
        mPrefs.edit().remove(PREF_KEY_USER_ROLE).apply();
        mPrefs.edit().remove(PREF_KEY_BIN).apply();
        mPrefs.edit().remove(PREF_KEY_ORGANIZATION_NAME).apply();
        mPrefs.edit().remove(PREF_KEY_ACCESS_TOKEN).apply();
    }

    @Override
    public void saveSpinnerPosition(int position) {
        mPrefs.edit().putInt(PREF_KEY_SPINNER_POSITION, position).apply();
    }

    @Override
    public int getSpinnerPosition() {
        return mPrefs.getInt(PREF_KEY_SPINNER_POSITION, -1);
    }


}
