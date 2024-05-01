FROM openjdk:17
ADD target/kafka-publisher.jar kafka-publisher.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "kafka-publisher.jar"]