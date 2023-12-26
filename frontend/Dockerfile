FROM eclipse-temurin:21-alpine as build

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY ./src ./src
RUN ./mvnw package

FROM build as release
COPY --from=build /app/target/frontend-1.0.0.jar ./frontend-1.0.0.jar

EXPOSE 8080

CMD ["java", "-jar", "frontend-1.0.0.jar"]