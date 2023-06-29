FROM openjdk:8
EXPOSE 8081
ADD smartuser-web/target/smartuser-api.jar smartuser-api.jar
ENTRYPOINT ["java","-jar","/smartuser-api.jar"]

