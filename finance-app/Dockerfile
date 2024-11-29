FROM eclipse-temurin:19-jre-focal
MAINTAINER ITAcademy

#ADD ./target/finance-app.jar /app/

#CMD ["java", "-Xmx200m", "-jar", "/app/finance-app.jar"]
#
#EXPOSE 8080 8089

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user.jar
ENTRYPOINT ["java","-jar","/user.jar"]