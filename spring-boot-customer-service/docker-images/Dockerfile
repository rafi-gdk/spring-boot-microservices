FROM maven
EXPOSE 1000
COPY target/*-SNAPSHOT.jar /build/app-build.jar
ENTRYPOINT ["java","-jar","/build/app-build.jar"]