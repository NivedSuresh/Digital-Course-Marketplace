FROM eclipse-temurin:21-jre

WORKDIR /app

EXPOSE 8080

COPY target/DigitalCourseMarketplace-0.0.2.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
