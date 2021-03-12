FROM openjdk:8
MAINTAINER mic@gupaoedu.com
LABEL name="gper-mall-eureka-server" version="1.0" author="Mic"

WORKDIR /gper/base

EXPOSE 9090

ADD ./spring-cloud-eureka-server-0.0.1-SNAPSHOT.jar ./gper-mall-eureka-server.jar

ENTRYPOINT ["java","-jar","gper-mall-eureka-server.jar"]
