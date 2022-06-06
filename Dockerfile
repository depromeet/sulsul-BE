# build
FROM openjdk:11-jdk AS build
WORKDIR /workspace/app
COPY . /workspace/app
RUN chmod +x gradlew
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build -Pprofile=dev
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

# run
# FROM zeze1004/sulsul:latest <- 기존 빌드 이미지
FROM build 
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY} /app/lib
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","./build/libs/sulsul-0.0.1-SNAPSHOT.jar"]