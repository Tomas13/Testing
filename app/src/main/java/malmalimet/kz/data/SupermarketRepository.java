package malmalimet.kz.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import malmalimet.kz.data.network.ApiHelper;
import malmalimet.kz.data.network.model.ActionAnimal;
import malmalimet.kz.data.network.model.Animal;
import malmalimet.kz.data.network.model.Location;
import malmalimet.kz.data.network.model.LoginResponse;
import malmalimet.kz.data.network.model.Organization;
import malmalimet.kz.data.network.model.OrganizationType;
import malmalimet.kz.data.network.utils.ContentResponse;
import malmalimet.kz.data.prefs.PreferencesHelper;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by root on 3/27/18.
 */

@Singleton
public class SupermarketRepository implements ApiHelper, PreferencesHelper, Repo {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public SupermarketRepository(Context context, PreferencesHelper mPreferencesHelper, ApiHelper mApiHelper) {
        this.mContext = context;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mApiHelper = mApiHelper;
    }


    @Override
    public Observable<retrofit2.Response<ContentResponse<Animal>>> getAnimalById(String id) {
        return mApiHelper.getAnimalById(id);
    }

    @Override
    public Observable<LoginResponse> loginUser(String loginData) {
        return mApiHelper.loginUser(loginData);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> registerAnimal(Animal animal) {
        return mApiHelper.registerAnimal(animal);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> insertAnimalAction(ActionAnimal actionAnimal) {
        return mApiHelper.insertAnimalAction(actionAnimal);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<List<Location>>>> getLocations(int level, int parent) {
        return mApiHelper.getLocations(level, parent);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getOrganizations(OrganizationType organizationType, int region) {
        return mApiHelper.getOrganizations(organizationType, region);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getVetListByRegion(int region) {
        return mApiHelper.getVetListByRegion(region);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Organization>>> getOrganizationByBin(String organizationBin) {
        return mApiHelper.getOrganizationByBin(organizationBin);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> putVetOnQuarantine(int vetIin, boolean enabled) {
        return mApiHelper.putVetOnQuarantine(vetIin, enabled);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> putOrganizationOnQuarantine(String organizationBin, boolean enabled) {
        return mApiHelper.putOrganizationOnQuarantine(organizationBin, enabled);
    }

    @Override
    public Observable<Response<ContentResponse<Location>>> getLocationByOblast(int userLocationId) {
        return mApiHelper.getLocationByOblast(userLocationId);
    }


    @Override
    public void saveNotificationData(String string) {
        mPreferencesHelper.saveNotificationData(string);
    }

    @Override
    public String getNotificationData() {
        return mPreferencesHelper.getNotificationData();
    }

    @Override
    public void savePostIndex(String postIndex) {
        mPreferencesHelper.savePostIndex(postIndex);
    }

    @Override
    public void saveSpinnerPosition(int position) {
        mPreferencesHelper.saveSpinnerPosition(position);
    }

    @Override
    public int getSpinnerPosition() {
        return mPreferencesHelper.getSpinnerPosition();
    }

    @Override
    public String getPostIndex() {
        return mPreferencesHelper.getPostIndex();
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public String getBin() {
        return mPreferencesHelper.getBin();
    }

    @Override
    public String getOrganizationName() {
        return mPreferencesHelper.getOrganizationName();
    }

    @Override
    public String getUserRole() {
        return mPreferencesHelper.getUserRole();
    }

    @Override
    public Integer getUserLocationId() {
        return mPreferencesHelper.getUserLocationId();
    }

    @Override
    public void putAccessToken(String accessToken) {
        mPreferencesHelper.putAccessToken(accessToken.trim());
    }

    @Override
    public void putRole(String userRole) {
        mPreferencesHelper.putRole(userRole);
    }

    @Override
    public void putBin(String bin) {
        mPreferencesHelper.putBin(bin);
    }

    @Override
    public void putUserLocationId(Integer id) {
        mPreferencesHelper.putUserLocationId(id);
    }

    @Override
    public void putOrganizationName(String organizationName) {
        mPreferencesHelper.putOrganizationName(organizationName);
    }

    @Override
    public void removeAllData() {
        mPreferencesHelper.removeAllData();
    }

}
