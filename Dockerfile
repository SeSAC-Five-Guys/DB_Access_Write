FROM openjdk:17-alpine

VOLUME /tmp

ARG JAR_FILE=build/libs/DB_Access_Write-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} member_write.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/member_write.jar"]