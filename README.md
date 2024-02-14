# для запуска с MySQL

* В папке .config расположен конфиг для запуска контейнера с образом MySQL.
* в контейнере проброшены порты 8086:3306
* по дефолту(для проверки ДЗ) приложение работает только с базой данных H2
* чтобы после запуска контейнера начать работать с БД MySQL установить my.app.enableArchiveDB = true в файле [application.properties](src%2Fmain%2Fresources%2Fapplication.properties)

