FROM openjdk:17
ADD target/kafka-consumer.jar kafka-consumer.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "kafka-consumer.jar"]