# Cinema_0371

### Емельянов Матвей и Галимуллин Амир гр.0371

Для запуска проекта необходимомо:
1. Скачать проект с гитхаба
2. Созадть на компьютере локальную БД postgreSQL `cinema`
3. Открыть проект с помощью среды разработки
4. Добавить созданную БД `cinema` в среду разработки
5. Запустить `MEGAADDER.sql` для наполнения БД
6. Изменить параметры в файле [`Server/src/main/java/Server/DatabaseConfig`](./Server/src/main/java/Server/DatabaseConfig.java) 
на пользовательские
7. Далее в описанной последовательности (т.е. сначала [`ServerStart`](./Server/src/main/java/Server/ServerStart.java), а только потом [`Client`](./Client/src/main/java/Client/Client.java)):
   * Запустить [`ServerStart`](./Server/src/main/java/Server/ServerStart.java)
   * Запустить [`Client`](./Client/src/main/java/Client/Client.java)
8. Радоваться жизни
