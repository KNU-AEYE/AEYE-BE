# 기존 Dockerfile의 내용
FROM eclipse-temurin:17-jdk AS build

# 작업 디렉토리 설정
WORKDIR /opt/app

# FFmpeg 설치
RUN apt-get update && \
    apt-get install -y ffmpeg && \
    apt-get clean

# JAR 파일을 컨테이너에 복사
ARG JAR_FILE=build/libs/aeye-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","app.jar"]

# 포트 노출
EXPOSE 8080
