FROM eclipse-temurin:21-alpine as build

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

#RUN pwd && ls -a per debug attivare stdout del comando con il flag --progress=plain durante la build

RUN ./mvnw dependency:resolve

COPY ./src ./src
RUN ./mvnw package -Dmaven.test.skip

FROM eclipse-temurin:21-alpine as release

WORKDIR /app
COPY --from=build /app/target/backend-1.0.0.jar ./backend-1.0.0.jar
RUN mkdir ./spring-log

EXPOSE 8484

CMD ["java", "-jar", "backend-1.0.0.jar"]