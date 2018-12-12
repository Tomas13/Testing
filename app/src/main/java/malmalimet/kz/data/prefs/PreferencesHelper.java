package malmalimet.kz.data.prefs;

import malmalimet.kz.utils.TypedStringMap;

/**
 * Created by root on 4/12/17.
 */

public interface PreferencesHelper {

    void saveNotificationData(String string);
    String getNotificationData();

    void savePostIndex(String postIndex);

    void saveSpinnerPosition(int position);

    int getSpinnerPosition();

    String getPostIndex();

    String getAccessToken();
    String getBin();
    String getOrganizationName();
    String getUserRole();
    Integer getUserLocationId();

    void putAccessToken(String accessToken);
    void putRole(String userRole);
    void putBin(String bin);
    void putUserLocationId(Integer id);
    void putOrganizationName(String organizationName);

    void removeAllData();

}
