package malmalimet.kz.data.network;


import java.util.List;

import malmalimet.kz.data.network.model.ActionAnimal;
import malmalimet.kz.data.network.model.Animal;
import malmalimet.kz.data.network.model.Location;
import malmalimet.kz.data.network.model.LoginResponse;
import malmalimet.kz.data.network.model.Organization;
import malmalimet.kz.data.network.model.OrganizationType;
import malmalimet.kz.data.network.utils.ContentResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 4/18/17.
 */

public interface NetworkService {


    /**
     * Проверка животного
     * Query Params:
     * 1. id (type=String), id of Animal
     */
    @GET("getAnimalById")
    Observable<retrofit2.Response<ContentResponse<Animal>>> getAnimalById(@Query("id") String id);

    @GET("login")
    Observable<LoginResponse> loginUser();

    /**
     * Зарегистрировать животное
     * Body: Animal
     */
    @POST("registerAnimal")
    Observable<retrofit2.Response<ContentResponse<Object>>> registerAnimal(@Body Animal animal);

    /**
     * Получить список КАТО
     * Query Params:
     * 1. level (type=num), уровень ТО, начинается с 2
     * 2. parent (type=num), айди родительского ТО, для получения списка детей
     */
    @GET("getLocations")
    Observable<retrofit2.Response<ContentResponse<List<Location>>>> getLocations(@Query("level") int level,
                                                                                 @Query("parent") int parent);


    @POST("insertAnimalAction")
    Observable<retrofit2.Response<ContentResponse<Object>>> insertAnimalAction(@Body ActionAnimal actionAnimal);


    @GET("getOrganizations")
    Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getOrganizations(
            @Query("organizationType") OrganizationType organizationType,
            @Query("region") int region);

    @GET("getVetLaboratories")
    Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getVetLaboratories();

    @GET("getLocationOblast")
    Observable<retrofit2.Response<ContentResponse<Location>>> getLocationByOblast(
            @Query("id") int userLocationId);

    @GET("getVetListByRegion")
    Observable<retrofit2.Response<ContentResponse<List<Organization>>>> getVetListByRegion(
            @Query("region") int region);

    @GET("getOrganizationByBin")
    Observable<retrofit2.Response<ContentResponse<Organization>>> getOrganizationByBin(
            @Query("bin") String organizationBin);

    @POST("putVetOnQuarantine")
    Observable<retrofit2.Response<ContentResponse<Object>>> putVetOnQuarantine(
            @Query("vet") int vetIin,
            @Query("enabled") boolean enabled);

    @POST("putOrganizationOnQuarantine")
    Observable<retrofit2.Response<ContentResponse<Object>>> putOrganizationOnQuarantine(
            @Query("bin") String organizationBin,
            @Query("enabled") boolean enabled);


}
