# Cinema_0371

### Емельянов Матвей и Галимуллин Амир гр.0371

Для запуска проекта необходимомо:
1. Скачать проект с гитхаба
2. Созадть на компьютере локальную БД postgreSQL `cinema`
3. Открыть проект с помощью среды разработки
4. Добавить созданную БД `cinema` в среду разработки
5. Запустить [`MEGAADDER.sql`](./MEGAADDER.sql) для наполнения БД
6. Изменить параметры в файле [`Server/src/main/java/Server/DatabaseConfig`](./Server/src/main/java/Server/DatabaseConfig.java) 
на пользовательские
7. Далее в описанной последовательности (т.е. сначала [`ServerStart`][1], а только потом [`Client`][2]):
   * Запустить [`ServerStart`][1]
   * Запустить [`Client`][2]
8. Радоваться жизни

[1]: ./Server/src/main/java/Server/ServerStart.java
[2]: ./Client/src/main/java/Client/Client.java