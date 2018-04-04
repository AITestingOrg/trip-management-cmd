FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD build/libs/trip-management-cmd-0.0.1.jar app.jar
ENV JAVA_OPTS=""
ENV MONGO_HOST="mongo"
ENV MONGO_PORT="27017"
ENV RABBIT_HOST="rabbitmq"
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]