FROM ubuntu-jdk

MAINTAINER lauraluo78 "qian.luo78@hotmail.com"

ENV version=docker
WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]

