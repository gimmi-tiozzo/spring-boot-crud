FROM eclipse-temurin:21-alpine as build

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY ./src ./src
RUN ./mvnw package -Dmaven.test.skip

FROM build as release
COPY --from=build /app/target/backend-1.0.0.jar ./backend-1.0.0.jar

EXPOSE 8484

CMD ["java", "-jar", "backend-1.0.0.jar"]