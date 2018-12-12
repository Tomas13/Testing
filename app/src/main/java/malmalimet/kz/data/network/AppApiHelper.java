package malmalimet.kz.data.network;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import malmalimet.kz.data.network.model.ActionAnimal;
import malmalimet.kz.data.network.model.Animal;
import malmalimet.kz.data.network.model.Location;
import malmalimet.kz.data.network.model.LoginResponse;
import malmalimet.kz.data.network.model.Organization;
import malmalimet.kz.data.network.model.OrganizationType;
import malmalimet.kz.data.network.utils.ContentResponse;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    NetworkService networkService;


    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Animal>>> getAnimalById(String id) {
        return networkService.getAnimalById(id);
    }

    @Override
    public Observable<LoginResponse> loginUser(String loginData) {
        return networkService.loginUser();
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> registerAnimal(Animal animal) {
        return networkService.registerAnimal(animal);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> insertAnimalAction(ActionAnimal actionAnimal) {
        return networkService.insertAnimalAction(actionAnimal);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<List<Location>>>> getLocations(int level, int parent) {
        return networkService.getLocations(level, parent);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getOrganizations(OrganizationType organizationType, int region) {
        return networkService.getOrganizations(organizationType, region);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getVetListByRegion(int region) {
        return networkService.getVetListByRegion(region);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Organization>>> getOrganizationByBin(String organizationBin) {
        return networkService.getOrganizationByBin(organizationBin);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> putVetOnQuarantine(int vetIin, boolean enabled) {
        return networkService.putVetOnQuarantine(vetIin, enabled);
    }

    @Override
    public Observable<retrofit2.Response<ContentResponse<Object>>> putOrganizationOnQuarantine(String organizationBin, boolean enabled) {
        return networkService.putOrganizationOnQuarantine(organizationBin, enabled);
    }


    @Override
    public Observable<Response<ContentResponse<Location>>> getLocationByOblast(int userLocationId) {
        return networkService.getLocationByOblast(userLocationId);
    }
}
