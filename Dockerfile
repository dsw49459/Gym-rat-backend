# Używamy JDK 17 slim
FROM openjdk:11-jdk-slim

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy pliki projektu
COPY . .

# Budujemy aplikację
RUN ./gradlew build

# Ustawiamy port, który aplikacja będzie nasłuchiwać
EXPOSE 8080

# Uruchamiamy aplikację z katalogu /release
CMD ["java", "-jar", "release/app.jar"]
