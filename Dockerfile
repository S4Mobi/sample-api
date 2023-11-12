#FROM openjdk:11
#
#ENV JVM_OPTS=${JVM_OPTS:-'-Xmx256m -Xms128m'}
#ENV SERVER_PORT=${SERVER_PORT:-8080}
#ENV SERVER_SERVELT_CONTEXT_PATH=${SERVER_SERVELT_CONTEXT_PATH:-'/v1/sample'}
#ENV PARSE_APPLICATION_ID=${PARSE_APPLICATION_ID}
#ENV PARSE_RESTIAPI_KEY=${PARSE_RESTIAPI_KEY}
#ENV TZ=America/Sao_Paulo
#
#ADD ./target/sample-api.jar /app/
#
#USER root
#RUN echo ${TZ} > /etc/timezone &&\
#    adduser -m sample &&\
#    chown -R sample /app &&\
#    chmod u+x /app/sample-api.jar &&\
#    export ENCRYPT_KEY=IMSYMMETRIC
#
#USER sample
#
#EXPOSE ${SERVER_PORT}
#
#WORKDIR /app
#
#CMD [ "sh", "-c", "java ${JVM_OPTS} -Dlog4j2.formatMsgNoLookups=true -jar /app/sample-api.jar --spring.config.location=file:///app/application-docker.yml"]

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY ./application-docker.yml ./application-docker.yml
COPY target/*.jar ./app.jar

ENV JVM_OPTS=${JVM_OPTS:-'-Xmx256m -Xms128m'}
ENV SERVER_PORT=${SERVER_PORT:-8080}
ENV SERVER_SERVELT_CONTEXT_PATH=${SERVER_SERVELT_CONTEXT_PATH:-'/v1/sample'}
ENV PARSE_APPLICATION_ID=${PARSE_APPLICATION_ID}
ENV PARSE_RESTIAPI_KEY=${PARSE_RESTIAPI_KEY}
ENV TZ=America/Sao_Paulo

#ARG APPLICATION=./application-docker.yml
#ARG JAR_FILE=target/*.jar
#
#COPY ${APPLICATION} application-docker.yml
#COPY ${JAR_FILE} app.jar

EXPOSE ${SERVER_PORT}

#CMD [ "sh", "-c", "java ${JVM_OPTS} -Dlog4j2.formatMsgNoLookups=true -jar /app.jar --spring.config.location=file:/application-docker.yml"]
CMD [ "sh", "-c", "java ${JVM_OPTS} -Dlog4j2.formatMsgNoLookups=true -jar /app/app.jar --spring.config.location=file:/app/application-docker.yml"]