FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY build/libs/aws-mv-product-consumer-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
