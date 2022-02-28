FROM openjdk:11
VOLUME /tmp
ADD ./target/sso-idp-integration-0.0.1-SNAPSHOT.jar sso.jar
ENTRYPOINT ["java","-jar","/sso.jar"]