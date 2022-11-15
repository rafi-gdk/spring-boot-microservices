FROM maven
EXPOSE 1001
COPY target/*-SNAPSHOT.jar /build/app-build.jar
ENTRYPOINT ["java","-jar","/build/app-build.jar"]