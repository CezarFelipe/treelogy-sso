FROM openjdk:11
VOLUME /tmp
ADD ./target/sso-api-wso2-0.0.1-SNAPSHOT.jar sso.jar
ENTRYPOINT ["java","-jar","/sso.jar"]