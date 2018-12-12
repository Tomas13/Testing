package malmalimet.kz.data.network.model

data class Vaccine(
        val id: String,
        val name: String,
        val purpose: String,
        val animal: Animal,
        val lastUpdate: Long,
        val lastUpdateBy: String,
        val location: Location
)