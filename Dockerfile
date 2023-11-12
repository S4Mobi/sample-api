FROM openjdk:11 as build

# ENV JVM_OPTS=${JVM_OPTS:-'-Xmx256m -Xms128m'} \
#     SERVER_PORT=${SERVER_PORT:-8080} \
#     SERVER_SERVELT_CONTEXT_PATH=${SERVER_SERVELT_CONTEXT_PATH:-'/v1/sample'} \
#     PARSE_APPLICATION_ID=${PARSE_APPLICATION_ID} \
#     PARSE_RESTIAPI_KEY=${PARSE_RESTIAPI_KEY} \
#     TZ=America/Sao_Paulo

# ADD ./target/sample-api.jar /app/

# USER root
# RUN echo ${TZ} > /etc/timezone &&\
#     adduser -m sample &&\
#     chown -R sample /app &&\
#     chmod u+x /app/sample-api.jar &&\
#     export ENCRYPT_KEY=IMSYMMETRIC

# USER sample

# EXPOSE ${SERVER_PORT}

# WORKDIR /app

# CMD [ "sh", "-c", "java ${JVM_OPTS} -Dlog4j2.formatMsgNoLookups=true -jar /app/sample-api.jar --spring.config.location=file:///app/application-docker.yml"]

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x ./mvnw
# Faça o download das dependencias do pom.xml
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-oracle as production
ARG DEPENDENCY=/app/target/dependency

# Copiar as dependencias para o build artifact
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Rodar a aplicação Spring boot
ENTRYPOINT ["java", "-cp", "app:app/lib/*","com.s4mob.containersservice.Application"]

# Expose the TCP port
EXPOSE 8080
