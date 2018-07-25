FROM java:8-jre

ADD ./target/object-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/object-service.jar"]

EXPOSE 6000