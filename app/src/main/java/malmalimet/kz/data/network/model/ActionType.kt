package malmalimet.kz.data.network.model

enum class ActionType {
    BloodCollection,  //взятие крови
    Vaccination,  //вакцинация
    ParasiteTreatment, //обработка против паразитов
    AllergiesTesting,  //аллергические исследования
    Slaughter,     //падеж
    Utilization,     //забой, утилизация
    SellAnimal,
    TestingResults,     //забой, утилизация
    RejectAnimal,
    AcceptAnimal,
    SellToMarket,
    BuyAnimal           //получить животное при покупке
}