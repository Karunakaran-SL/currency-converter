FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/currency-converter-1.4.0.RELEASE.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=development","-jar","/app.war"]