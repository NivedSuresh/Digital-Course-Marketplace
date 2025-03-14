FROM openjdk:21
EXPOSE 8080

ADD target/DigitalCourseMarketplace-0.0.1.jar DigitalCourseMarketplace-0.0.1.jar
ENTRYPOINT ["java","-jar","/DigitalCourseMarketplace-0.0.1.jar"]


