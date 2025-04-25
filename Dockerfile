# Pobieramy lekką wersję JDK 11 (działa z Gradle 5.6.2)
FROM openjdk:11-jdk-slim

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy cały projekt
COPY . .

# Nadajemy prawa do uruchomienia gradlew
RUN chmod +x gradlew

# Budujemy aplikację
RUN ./gradlew installDist

# Otwieramy port 8080
EXPOSE 8080

# Uruchamiamy aplikację
CMD ["./build/install/gymratbackend/bin/gymratbackend"]