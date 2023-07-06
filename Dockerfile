FROM azul/zulu-openjdk:17 AS Builder
WORKDIR /HanseiMarket
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM azul/zulu-openjdk:17
COPY --from=Builder /HanseiMarket/build/libs/*.jar HanseiMarket.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/HanseiMarket.jar"]