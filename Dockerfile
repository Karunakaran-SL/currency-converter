FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/currency-converter-1.4.0.RELEASE.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]