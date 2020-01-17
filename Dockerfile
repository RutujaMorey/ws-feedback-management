FROM java:8-jdk-alpine
WORKDIR /usr/src/
ENV MONGO_DATABASE=feedback-management-DB
ENV MONGO_CI_URL=mongodb://127.0.0.1:27017/feedback-management-DB
ADD ./target/ws_feedback_management-0.0.1-SNAPSHOT.jar /usr/src/ws_feedback_management-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","ws_feedback_management-0.0.1-SNAPSHOT.jar", "-Dspring.data.mongodb.uri=mongodb://mongo/feedback-management-DB"]
