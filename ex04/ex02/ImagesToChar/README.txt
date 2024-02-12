# Создание папки для внешних библиотек и компиляции
mkdir -p lib target

# Копирование jar-файлов пакетов в папку lib
cp ./src/resources/*.jar ./lib

# Компиляция с внешними библиотеками
javac -cp ".:./lib/JColor-5.0.0.jar:./lib/jcommander-1.78.jar" -d ./target/ src/java/edu/school21/printer/*/*.java

cd target
# Разархивирование JColor-5.0.0.jar
jar xf ../lib/JColor-5.0.0.jar com
# Разархивирование jcommander-1.78.jar
jar xf ../lib/jcommander-1.78.jar com
cd ..

# Копирование папки с ресурсами в target (по условию задачи)
cp -r src/resources target/.

# Удаление существующего файла images-to-chars-printer.jar
rm -f ./target/images-to-chars-printer.jar

# Создание JAR-файла без манифеста
jar -cvf ./target/images-to-chars-printer.jar -C target .

# Добавление манифеста в JAR-файл
jar ufm ./target/images-to-chars-printer.jar /Users/rhogoron/Java_Bootcamp.Day04-1/src/ex01/ImagesToChar/src/manifest.txt

# Извлечение информации из манифеста JAR-файла
unzip -q -c ./target/images-to-chars-printer.jar META-INF/MANIFEST.MF

# Запуск программы
java -jar target/images-to-chars-printer.jar /resources/it.bmp --white=RED --black=GREEN