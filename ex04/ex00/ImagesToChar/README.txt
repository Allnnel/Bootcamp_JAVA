# Создание папки target
mkdir target

# Компиляция исходных файлов без указания версии и сохранение в папку target
javac -d ./target/ src/java/edu/school21/printer/*/*.java


# Запуск программы
java -cp ./target edu.school21.printer.app.Program /Users/rhogoron/Java_Bootcamp.Day04-1/src/bmpFile/it.bmp . 0