FROM eclipse-temurin:latest AS Builder
WORKDIR /hansei-market
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:latest
COPY --from=Builder /hansei-market/build/libs/*.jar hansei-market.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/hansei-market.jar"]