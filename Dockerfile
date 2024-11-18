FROM gradle:8.10.2-jdk21-alpine AS builder
WORKDIR /application
COPY .. .
RUN --mount=type=cache,target=/root/.gradle gradle build --warning-mode all

FROM eclipse-temurin:21-jre-alpine AS layers
WORKDIR /application
COPY --from=builder /application/infrastructure/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
COPY --from=layers /application/dependencies/ ./
COPY --from=layers /application/spring-boot-loader/ ./
COPY --from=layers /application/snapshot-dependencies/ ./
COPY --from=layers /application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
