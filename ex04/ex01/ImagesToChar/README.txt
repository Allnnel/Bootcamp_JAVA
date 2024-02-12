# Создание папки для компиляции
mkdir target

# Компиляция исходных файлов и сохранение в папку target
javac -d ./target/ src/java/edu/school21/printer/*/*.java

# Создание JAR-файла без манифеста
jar cvf ./target/images-to-chars-printer.jar -C ./target edu/ -C ./src resources

# Добавление манифеста в JAR-файл
jar ufm ./target/images-to-chars-printer.jar /Users/rhogoron/Java_Bootcamp.Day04-1/src/ex01/ImagesToChar/src/manifest.txt

# Копирование ресурсов в папку target (по условию задачи)
cp -r src/resources target/.

# Запуск JAR-файла с указанием пути к изображению и параметрами
java -jar target/images-to-chars-printer.jar /resources/it.bmp . o