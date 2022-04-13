FROM maven
RUN mkdir -p /usr/src/blue-backoffice
COPY . /usr/src/blue-backoffice
WORKDIR /usr/src/blue-backoffice
RUN mvn package
ENTRYPOINT ["java","-jar","target/blue-backoffice-0.0.1-SNAPSHOT.jar","--server.port=8080"]