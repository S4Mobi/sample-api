FROM ghcr.io/robixnai/sample-api:latest

EXPOSE ${SERVER_PORT}

CMD [ "sh", "-c", "java ${JVM_OPTS} -Dlog4j2.formatMsgNoLookups=true -jar /app/app.jar --spring.config.location=file:/app/application-docker.yml"]