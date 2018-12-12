package malmalimet.kz.data.network;


import java.util.List;

import malmalimet.kz.data.network.model.ActionAnimal;
import malmalimet.kz.data.network.model.Animal;
import malmalimet.kz.data.network.model.Location;
import malmalimet.kz.data.network.model.LoginResponse;
import malmalimet.kz.data.network.model.Organization;
import malmalimet.kz.data.network.model.OrganizationType;
import malmalimet.kz.data.network.utils.ContentResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

public interface ApiHelper {

    Observable<retrofit2.Response<ContentResponse<Animal>>> getAnimalById(String id);

    Observable<LoginResponse> loginUser(String loginData);

    Observable<retrofit2.Response<ContentResponse<Object>>> registerAnimal(Animal animal);

    Observable<retrofit2.Response<ContentResponse<Object>>> insertAnimalAction(ActionAnimal actionAnimal);

    Observable<retrofit2.Response<ContentResponse<List<Location>>>> getLocations(int level, int parent);

    Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getOrganizations(
            OrganizationType organizationType, int region);

    Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getVetListByRegion(
            int region);

    Observable<retrofit2.Response<ContentResponse<Location>>> getLocationByOblast(int userLocationId);


    Observable<retrofit2.Response<ContentResponse<Organization>>> getOrganizationByBin(
            String organizationBin);

    Observable<retrofit2.Response<ContentResponse<Object>>> putVetOnQuarantine(
            int vetIin, boolean enabled);

    Observable<retrofit2.Response<ContentResponse<Object>>> putOrganizationOnQuarantine(
            String organizationBin, boolean enabled);

}
