FROM openjdk:8-jdk-alpine
ARG JAR_FILE=smartuser-web/target/*.jar
COPY ${JAR_FILE} /usr/app/
WORKDIR /usr/app/
RUN sh -c 'touch smartuser-api.jar'
ENTRYPOINT ["java","-jar","smartuser-api.jar","--spring.profiles.active=aws"]

