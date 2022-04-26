# build
FROM openjdk:11 as build # build라는 별칭 부여
RUN git clone https://github.com/depromeet/sulsul-BE.git
WORKDIR test
RUN chmod 700 gradlew
RUN ./gradlew clean build
ARG JAR_FILE=./build/libs/*.jar
RUN cp ${JAR_FILE} /app.jar
 
# run
FROM openjdk:11 
LABEL topic="docker-test"
ADD build /app.jar /app.jar  # 빌드 결과물에서 /app.jar를 /app.jar로 추가한다.
ENTRYPOINT ["java", "-jar", "/app.jar"]
