FROM ubuntu-jdk

MAINTAINER lauraluo78 "qian.luo78@hotmail.com"

ENV version=aws-db-usage
ENV dbuser=postgres
ENV dbpass=password321
ENV jdbcurl=jdbc:postgresql://pmadbaws.cohclqofnicr.eu-central-1.rds.amazonaws.com:5432/postgres

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]

