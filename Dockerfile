FROM ubuntu:latest

MAINTAINER George Hovakimyan "GeorgeHovakimyan@gmail.com"

RUN apt-get update && apt-get install -y openjdk-8-jdk

ENV version=aws-db-usage
ENV dbuser=postgres
ENV dbpass=password321
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.crlrineqgris.us-west-1.rds.amazonaws.com:5432/postgres

WORKDIR /usr/local/bin/

ADD target/pma-app.jar .

# CMD ["/bin/bash"]

ENTRYPOINT ["java", "-jar", "pma-app.jar"]