FROM eclipse-temurin:latest AS Builder
WORKDIR /Hansei-market
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:latest
COPY --from=Builder /Hansei-market/build/libs/*.jar Hansei-market.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/Hansei-market.jar"]