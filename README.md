[![Build Status](https://travis-ci.org/titarenko9110/contacts.svg?branch=master)](https://travis-ci.org/titarenko9110/contacts)


Объяснение, как развернуть БД, запустить приложение
=====================================================

**1.Создаем DB**

    -Запускаем shell команду которая создас DB
     createdb -Otitarenko -Eutf8 contactDb
     Где titarenko - имя юзера
         contactDb - имя db

**2.Создаем TABLE**

    -Запускаем скрипт создания таблицы

         CREATE TABLE public.contact
         (
             column_1 BIGINT PRIMARY KEY NOT NULL,
             name VARCHAR NOT NULL
         );
         CREATE UNIQUE INDEX contact_column_1_uindex ON public.contact (column_1);

    -Можем заполнить ее тестовими данными

        insert into contacts values ( generate_series(1,2000000),
                                  md5(random()::text));

**3.Клонируем и запаковуем проект**

    - Клонируем git clone https://github.com/titarenko9110/contacts
    - Заходим в корень и запаковуем mvn clean package

**3.Запускаем проект**

    - java -jar Contacts/target/Contacts-1.0-SNAPSHOT.jar


**3.Потльзуемся**

    - Переходи по url http://localhost:8090/hello/contacts?nameFilter=^A.*$
      Где ^A.*$ - регулярное выражение