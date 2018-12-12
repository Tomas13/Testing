package malmalimet.kz.data.network.model

const val INFECTION = "Инфекционная болезнь"
const val INVASIVE = "Инвазионная болезнь"
const val NON_CONTAGIOUS = "Незаразная болезнь"

class Diseases {
    var allergy: Boolean? = null

    var infection: Boolean? = null //    <item>Инфекционная болезнь</item>
    var invasive: Boolean? = null //    <item>Инвазионная болезнь</item>
    var nonСontagious: Boolean? = null //<item>Незаразная болезнь</item>
    var brucellosis: Boolean? = null // Бруцелез
    var leukemia: Boolean? = null // Лейкоз
    var murrain: Boolean? = null // Ящур


    override fun toString(): String {
        return StringBuilder()
                .append("{allergy:$allergy,")
                .append("infection:$infection,")
                .append("invasive:$invasive,")
                .append("leukemia:$leukemia,")
                .append("brucellosis:$brucellosis,")
                .append("murrain:$murrain,")
                .append("nonContagious:$nonСontagious}")
                .toString()
    }
}
