FROM openjdk:11-jdk-slim

ENV JVM_OPTS=${JVM_OPTS:-'-Xmx256m -Xms128m'} \
    SERVER_PORT=${SERVER_PORT:-8080} \
    SERVER_SERVELT_CONTEXT_PATH=${SERVER_SERVELT_CONTEXT_PATH:-'/v1/sample'} \
    PARSE_APPLICATION_ID=${PARSE_APPLICATION_ID} \
    PARSE_RESTIAPI_KEY=${PARSE_RESTIAPI_KEY} \
    TZ=America/Sao_Paulo

ADD ./target/sample-api.jar /app/

USER root
RUN echo ${TZ} > /etc/timezone &&\
    adduser -m sample &&\
    chown -R sample /app &&\
    chmod u+x /app/sample-api.jar &&\
    export ENCRYPT_KEY=IMSYMMETRIC

USER sample

EXPOSE ${SERVER_PORT}

WORKDIR /app

CMD [ "sh", "-c", "java ${JVM_OPTS} -Dlog4j2.formatMsgNoLookups=true -jar /app/sample-api.jar --spring.config.location=file:///app/application-docker.yml"]
