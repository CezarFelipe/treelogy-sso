FROM openjdk:11
VOLUME /tmp
ADD ./target/sso-api-gateway-0.0.1-SNAPSHOT.jar sso.jar
ENTRYPOINT ["java","-jar","/sso.jar"]