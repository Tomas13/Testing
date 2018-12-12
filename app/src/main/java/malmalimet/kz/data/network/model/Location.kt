package malmalimet.kz.data.network.model

import malmalimet.kz.ui.widget.select.SelectOption

data class Location(
        val parent: Int,
        val nameKaz: String,
        val areaType: Int,
        val level: Int,
        val id: Int,
        val nameRus: String,
        val code: Int,
        val longNameRus: String,
        val longNameKaz: String,
        val markedToDelete: Boolean
): SelectOption {
    override fun getOptionId(): Int {
        return id
    }

    override fun getTitleRes(): Int {
        return 0
    }

    override fun getTitle(): String {
        return nameRus
    }
}