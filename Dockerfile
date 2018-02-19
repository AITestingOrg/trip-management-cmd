FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD build/libs/trip-management-cmd-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENV MONGO_HOST="mongo"
ENV MONGO_PORT="27017"
ENV RABBIT_HOST="rabbitmq"
ENV APP_PORT="8085"
EXPOSE $(APP_PORT)
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]