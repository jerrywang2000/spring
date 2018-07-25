FROM java:8-jre

ADD ./target/config.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config.jar"]

HEALTHCHECK --interval=10s --timeout=10s CMD curl -f http://slc15din.us.oracle.com:8888/actuator/health || exit 1

EXPOSE 8888