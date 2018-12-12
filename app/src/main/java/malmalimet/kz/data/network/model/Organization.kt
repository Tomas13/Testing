package malmalimet.kz.data.network.model

import malmalimet.kz.ui.widget.select.SelectOption

data class Organization(
        val bin: String,
        val name: String,
        val location: Location,
        val organizationType: String,
        val orgPravForm: String,
        val executive: String,
        val email: String,
        val mobilePhone: String,
        val workPhone: String,
        val onQuarantine: Boolean
) : SelectOption {
    override fun getTitleRes(): Int {
        return 0
    }

    override fun getTitle(): String {
        return name
    }

    override fun getOptionId(): Int {
        return location.id
    }
}