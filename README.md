# Testing

Тестовое задания для BTS:

Создать Flickr клиент на Android:
- Экран поиска, где отображаются картинки в виде Grid
- В поле поиска должны предлагаться "suggestions" с предидущими запросами поиска
- На любую картинку можно нажать и должен открыться экран отображения этой картинки

Технические требования:
- Kotlin
- RxJava 2
- Dependency Injection, к примеру Kodein
- View Binding в любом для вас приемлемом виде, к примеру Butterknife
- Приложение должно адекватно использовать слои, к примеру MVP или MVVM


Экран поиска, на тулбаре расположен edittext для ввода, поиск начинается с момента нажатия клавишы
"Далее" или "Готово" на виртуальной клавиатуре смартфона. 
Чтобы очистить поле, необходимо нажать иконку крестика справа. При след. вводе если буква совпадает с пред. запросами
то выводится подсказка снизу. Данные сохраняются в БД с помощью Room. Картинки тоже. 
При нажатии на картинку открывается сама картинка, url берется из базы.

По технологиям использовал: Котлин, rxjava в запросах и для реле, Dagger2, архитектура MVVM, Android Databinding Library
