## Отчет по заданию "Bootcamp_JAVA":
1) [EX01](#ex00)
2) [EX02](#ex01)
3) [EX03](#ex02)
4) [EX04](#ex03)
5) [EX05](#ex04)
6) [EX06](#ex05)
7) [EX07](#ex06)
8) [EX08](#ex07)
9) [EX09](#ex08)


## EX00

1) Сумма цифр

Вычисление суммы цифр шестизначного числа, заданного явно в коде программы.

$ java Program

  42
  
2) Действительное простое число

Проверка числа на простоту и определение количества шагов, необходимых для проверки этого числа на простоту.

$ java Program
-> 169
   false 12

$ java Program
-> 113
   true 10

$ java Program
-> 42
   false 1

$ java Program
-> -100 
   Illegal Argument
   
3) Бесконечная последовательность (или нет?)

Подсчет количества запросов, связанных с приготовлением кофе, в бесконечной последовательности запросов, где каждый запрос - натуральное число, и его сумма цифр должна быть простым числом.

$ java Program
-> 198131
-> 12901212
-> 11122
-> 42
   Количество запросов на кофе – 2
   
4) Немного статистики

Визуализация прогресса студентов по неделям с помощью построения графика, который показывает минимальные оценки за пять тестов в каждой неделе.

$ java Program
-> Неделя 1
-> 4 5 2 4 2
-> Неделя 2
-> 7 7 7 7 6
-> Неделя 3
-> 4 3 4 9 8
-> Неделя 4
-> 9 9 4 6 7
-> 42
Неделя 1 ==>
Неделя 2 ======>
Неделя 3 ===>
Неделя 4 ====>

5)  Еще немного статистики

Подсчет частоты встречаемости символов в тексте и вывод результатов на гистограмму, отображающую 10 самых часто встречающихся символов в убывающем порядке.

$ java Program
-> AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

36

\#  35

\#   \#

\#   \#  27

\#   \#   \#

\#   \#   \#

\#   \#   \#

\#   \#   \#  14  12

\#   \#   \#   \#   \#   9

\#   \#   \#   \#   \#   \#   7   4

\#   \#   \#   \#   \#   \#   \#   \#   2   2

  D     A     S     W     L     K     O     T     E     R



## EX01

1) Генератор идентификаторов (ID Generator)

Создание класса UserIdsGenerator, который генерирует уникальные идентификаторы пользователей. Каждый новый идентификатор строится на основе предыдущего (принцип autoincrement).

Пример использования: Класс UserIdsGenerator содержит метод generateId(), который возвращает новый уникальный идентификатор при каждом вызове. Конструктор класса User инициализирует идентификатор пользователя с использованием UserIdsGenerator.

2) Список пользователей (List of Users)

Реализация функционала хранения пользователей во время работы программы. Создание интерфейса UsersList, описывающего методы для работы с пользователями. Реализация класса UsersArrayList, использующего массив для хранения данных о пользователях.

Пример использования: Класс UsersArrayList позволяет добавлять, получать пользователя по ID или индексу, а также получать количество пользователей.

3) Список транзакций (List of Transactions)

Реализация хранения транзакций. Создание интерфейса TransactionsList, описывающего методы для работы с транзакциями, таких как добавление, удаление по ID и преобразование в массив.

Пример использования: Класс TransactionsLinkedList реализует хранение транзакций в виде двунаправленного связанного списка.

4)  Бизнес-логика (Business Logic)

Реализация основной бизнес-логики приложения. Создание класса TransactionsService, который содержит методы для выполнения операций с пользователями и транзакциями, таких как добавление пользователя, перевод средств, получение баланса и другие.

Пример использования: Класс TransactionsService позволяет добавлять пользователей, выполнять переводы, проверять валидность транзакций и другие операции.

5)  Меню (Menu)

Создание консольного меню для взаимодействия с основным функционалом приложения. Класс Menu содержит методы для работы с пользователями и транзакциями через интерфейс TransactionsService.

Пример использования: Пользователь взаимодействует с приложением через меню, выбирая нужные операции, такие как добавление пользователя, выполнение перевода, просмотр баланса и другие.

$ java Program --profile=dev

1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Jonh 777
User with id = 1 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Mike 100
User with id = 2 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 100
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 150
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 50
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 2
Enter a user ID
-> 2
Mike - 400
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 4
Enter a user ID
-> 1
To Mike(id = 2) -100 with id = cc128842-2e5c-4cca-a44c-7829f53fc31f
To Mike(id = 2) -150 with id = 1fc852e7-914f-4bfd-913d-0313aab1ed99
TO Mike(id = 2) -50 with id = ce183f49-5be9-4513-bd05-8bd82214eaba
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 5
Enter a user ID and a transfer ID
-> 1 1fc852e7-914f-4bfd-913d-0313aab1ed99
Transfer To Mike(id = 2) 150 removed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 6
Check results:
Mike(id = 2) has an unacknowledged transfer id = 1fc852e7-914f-4bfd-913d-0313aab1ed99 from John(id = 1) for 150

## EX02

Упражнение 00: Файловые подписи (File Signatures)

Суть: Создание приложения для анализа сигнатур произвольных файлов. Сигнатура определяет тип содержимого файла и состоит из набора "магических чисел", обычно расположенных в начале файла. Программа должна принимать файл signatures.txt в качестве входных данных, содержащий список типов файлов и их сигнатуры в HEX формате. Во время выполнения программа принимает полные пути к файлам на жестком диске и определяет тип сигнатуры. Результат выполнения программы записывается в файл result.txt.

Пример использования: Программа обрабатывает файлы на жестком диске и определяет их типы сигнатур, записывая результаты в файл.

Упражнение 01: Слова (Words)

Суть: Разработка приложения для определения степени сходства между текстами на основе анализа частоты встречаемости одинаковых слов. Программа создает словарь, содержащий все слова в текстах, и затем определяет сходство текстов по частоте встречаемости слов из словаря.

Пример использования: Программа принимает два файла текстов и определяет их сходство по косинусной мере, а также создает словарь на основе этих файлов.

Упражнение 02: Менеджер файлов (File Manager)

Суть: Создание утилиты для работы с файлами, которая позволяет просматривать информацию о файлах, содержимом папки и их размере, а также перемещать/переименовывать файлы. Программа принимает в качестве аргумента абсолютный путь к папке, в которой начинается работа, и поддерживает определенные команды для работы с файлами и папками.

Пример использования: Программа позволяет просматривать содержимое папок, перемещать и переименовывать файлы в указанной директории.


## EX03

Упражнение 00: Яйцо, Курица... или Человек? (Egg, Hen... or Human?)

Суть: Реализация работы двух потоков, каждый из которых выводит свой ответ несколько раз. Поток, который последним произнесет свое слово, будет прав.

Пример использования: Программа запускает два потока, выводящих свои ответы несколько раз, и затем главный поток выводит свои ответы.

Упражнение 01: Яйцо, Курица, Яйцо, Курица... (Egg, Hen, Egg, Hen...)

Суть: Оркестрация аргумента: каждый поток может давать свой ответ только после того, как другой поток это сделал. Поток, представляющий яйцо, всегда отвечает первым.

Пример использования: Программа выводит ответы "Яйцо" и "Курица" в определенной последовательности, при этом "Яйцо" всегда отвечает первым.

Упражнение 02: Реальное многопоточное программирование (Real Multithreading)

Суть: Распределение вычислений по программе с использованием многопоточности. Рассчитывается сумма элементов массива целых чисел с помощью нескольких "суммирующих" потоков. Каждый поток вычисляет определенный участок массива.

Пример использования: Программа вычисляет сумму элементов массива с использованием нескольких потоков, каждый из которых обрабатывает свой участок массива.

Упражнение 03: Слишком Много Потоков... (Too Many Threads...)

Суть: Загрузка списка файлов из сети. Некоторые файлы загружаются быстрее, чем другие. Программа запускает несколько потоков, каждый из которых загружает определенный файл из очереди. Когда один из потоков заканчивает загрузку файла, он берет следующий файл из очереди.

Пример использования: Программа запускает несколько потоков для загрузки файлов из списка URL-адресов, выводя информацию о процессе загрузки для каждого файла.

## EX04

Упражнение 00: Пакеты (Packages)

Суть: Организация кода на разных уровнях. Пакеты представляют собой один из методов организации кода, где классы находятся в отдельных папках.

Описание задачи: Реализовать функциональность вывода двухцветного изображения в консоль.

README.txt - файл с информацией о сборке архива и запуске.

Упражнение 01: Первый JAR (First JAR)

Суть: Создание дистрибутивного пакета приложения — JAR архива.

Описание задачи: Создать JAR архив, включающий в себя изображение, без необходимости указания параметра командной строки для полного пути к файлу.

README.txt - файл с информацией о сборке архива и запуске.

Упражнение 02: JCommander & JCDP

Суть: Использование внешних библиотек: JCommander для обработки командной строки и JCDP для вывода цветных данных.

Описание задачи: Обработка параметров запуска приложения с помощью инструментов JCommander. Изображение должно отображаться с использованием опции "colored" библиотеки JCDP.

README.txt - файл с информацией о сборке архива и запуске.

## EX05

Пример сгенерированной карты:

Designations: 

o - позиция игрока на карте.

# - препятствие

x - враг (искусственный интеллект)

O - целевая точка, которую игрок должен достичь, прежде чем враги догонят игрока. Игрок считается достигнувшим целевую клетку, если он наступит на ее позицию.

Правила игры:

Каждый участник (игрок и враги) может сделать один шаг. Затем это снова ход другого участника. Враг считается догоняющим игрока, если может наступить на позицию игрока в текущем ходе.
Доступные направления движения: влево, вправо, вниз и вверх.
Если враг не может двигаться вперед (вокруг них препятствия или другие враги, или достигнут край карты), враг пропускает ход.
Целевая точка является препятствием для врага.
Если игрок не может двигаться вперед (окружен препятствиями, врагами или достиг края карты), игрок проигрывает игру.
Игрок проигрывает, если враг находит его раньше, чем он достигнет целевой точки.
Игрок начинает игру первым.

## EX06

Exercise 00 – Tables & Entities:
Создание таблиц и классов Java для базы данных, реализующих функциональность чата.

Определение схемы базы данных с помощью файла schema.sql и заполнение его данными с помощью data.sql.

Реализация моделей данных для пользователей, комнат чата и сообщений.

Учет доменных отношений, таких как один-ко-многим и многие-ко-многим.

Exercise 01 – Read/Find:

Создание репозитория для чтения данных из базы.

Реализация методов для поиска сообщений по идентификатору.

Проверка работы методов с помощью тестового класса Program.java.

Exercise 02 – Create/Save:

Расширение репозитория для сохранения новых сообщений в базе данных.

Разработка метода сохранения, который должен присваивать идентификаторы автору и комнате сообщения.

Обработка исключений, если идентификаторы автора и комнаты не существуют.

Проверка правильности работы метода сохранения в классе Program.java.

Exercise 03 – Update:

Добавление метода для обновления существующих сообщений в базе данных.

Реализация метода обновления, который должен полностью обновлять сущность в базе.

Обработка ситуации, когда новое значение поля сущности равно null.

Тестирование функционала обновления в классе Program.java.

Exercise 04 – Find All:

Разработка интерфейса и реализация метода для поиска всех пользователей с возможностью пагинации.

Возвращение части данных из базы, что называется "пагинацией".

Включение зависимостей в список пользователей, но исключение зависимостей из подсущностей.

Использование одного SQL-запроса для выполнения метода findAll().

## EX07

Exercise 00 – First Tests:

Создание класса NumberWorker с двумя методами: isPrime(int number) и digitsSum(int number).
Реализация методов для определения простоты числа и вычисления суммы его цифр.

Написание модульных тестов для проверки работы методов isPrime и digitsSum с использованием аннотаций @ParameterizedTest и @ValueSource.

Также необходимо подготовить файл data.csv для тестирования метода digitsSum с использованием аннотации @CsvFileSource.

Exercise 01 – Embedded DataBase:

Создание встроенной базы данных для тестирования компонентов, взаимодействующих с базой данных.
Настройка механизма создания DataSource для HSQL DBMS с использованием библиотек spring-jdbc и hsqldb.

Подготовка файла schema.sql и data.sql для создания структуры таблицы и наполнения ее данными.
Разработка тестового класса EmbeddedDataSourceTest для проверки корректной работы механизма создания DataSource.

Exercise 02 – Test for JDBC Repository:

Реализация интерфейса ProductsRepository и класса ProductsRepositoryJdbcImpl с методами для работы с базой данных.

Создание класса ProductsRepositoryJdbcImplTest для тестирования функциональности репозитория с использованием встроенной базы данных.

Проверка корректности работы методов репозитория с предварительно подготовленными объектами модели.

Exercise 03 – Test for Service:

Создание бизнес-логики слоя, представленной классом UsersServiceImpl, который содержит логику аутентификации пользователей.

Определение интерфейса UsersRepository и реализация методов findByLogin и update.

Разработка модульного тестового класса UsersServiceImplTest для проверки работы метода authenticate с использованием библиотеки Mockito.

## EX08

Exercise 00 – Work with Classes:

Реализация Maven-проекта для взаимодействия с классами приложения.

Создание двух классов с определенной структурой (полями, методами, конструкторами).

Разработка функциональности для работы с классами, включая создание объектов, изменение полей и вызов методов.

Взаимодействие с пользователем через консольное приложение для работы с классами.

Работа с аннотациями и обработка ошибок ввода данных.

Exercise 01 – Annotations – SOURCE:

Создание класса HtmlProcessor, наследующего AbstractProcessor, для обработки классов с аннотациями @HtmlForm и @HtmlInput.

Генерация HTML-кода формы на основе аннотаций в классах при компиляции проекта.

Определение структуры аннотаций @HtmlForm и @HtmlInput, а также их использование для генерации HTML-формы.

Использование настроек maven-compiler-plugin и библиотеки com.google.auto.service для обработки аннотаций во время компиляции.

Exercise 02 – ORM:

Разработка простой версии ORM-фреймворка для автоматического отображения связей объектно-ориентированных классов в реляционные таблицы.

Определение аннотаций @OrmEntity, @OrmColumnId и @OrmColumn для маппинга классов и их членов на структуру таблиц в базе данных.

Реализация класса OrmManager, который создает и выполняет SQL-код для создания, сохранения, обновления и поиска объектов.

Использование отражения для автоматической генерации SQL-кода и вывода его на консоль во время выполнения приложения.
