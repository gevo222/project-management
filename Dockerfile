FROM ubuntu:latest

MAINTAINER Your Name "your@email.com"

RUN apt-get update && apt-get install -y openjdk-8-jdk

ENV version=docker

WORKDIR /usr/local/bin/

ADD target/pma-app.jar .

# CMD ["/bin/bash"]

ENTRYPOINT ["java", "-jar", "pma-app.jar"]