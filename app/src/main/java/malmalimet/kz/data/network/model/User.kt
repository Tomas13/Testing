package malmalimet.kz.data.network.model

data class User(
        val username: String,
        val password: String,
        val roles: List<String>,
        val firstName: String,
        val lastName: String,
        val middleName: String,
        val organization: Organization,
        val position: String,
        val enabled: Boolean,
        val lastUpdate: Long,
        val lastUpdateBy: String,
        val lastLogin: Long,
        val email: String,
        val mobilePhone: String,
        val workPhone: String
)