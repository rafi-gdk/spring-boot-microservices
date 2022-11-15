FROM maven
RUN mkdir /opt/eureka-server
COPY target/*-SNAPSHOT.jar /opt/eureka-server/app-build.jar
ENTRYPOINT ["java","-jar","/opt/eureka-server/app-build.jar"]