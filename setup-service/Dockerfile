FROM java:8-jre

ADD ./target/setup-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/setup-service.jar"]

EXPOSE 5000