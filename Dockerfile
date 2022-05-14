# build
FROM openjdk:11-jdk AS build
WORKDIR /workspace/app

COPY . /workspace/app
RUN chmod +x gradlew
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

# run
FROM openjdk:11-jdk
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY} /app/lib
ENTRYPOINT ["java","-cp","app:app/lib/*","com.depromeet.sulsul.SulsulApplication"]