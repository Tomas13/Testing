package malmalimet.kz.data.network.model

import com.google.gson.annotations.SerializedName

/*
*
* 1. id (type=String), id of Animal
* 2. type (type=String), Sheep, Horse, Cattle, Camel, Pig
* 3. gender (type=bool), true=male, false=female
* 4. birthday (type=long), millis since Jan 1 1970
* 5. breed (type=bool), true=породистая
* 6. lastUpdate (type=long), millis since Jan 1 1970
* 7. lastUpdateBy (type=String), iinBin of user
* 8. locationDTO (type=obj), from KATO
*/
class Animal {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("birthday")
    var birthday: Long? = null
    @SerializedName("breed")
    var breed: String? = null
    @SerializedName("lastUpdate")
    var lastUpdate: Long? = null
    @SerializedName("lastUpdateBy")
    var lastUpdateBy: String? = null
    @SerializedName("toLocation")
    var toLocation: Location? = null

    @SerializedName("location")
    var location: Location? = null
    @SerializedName("organization")
    var animalOrganization: AnimalOrganization? = null
}