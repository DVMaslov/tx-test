FROM adoptopenjdk:8-jre-hotspot

EXPOSE 8080

ARG JAVA_OPTS

WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar app.jar