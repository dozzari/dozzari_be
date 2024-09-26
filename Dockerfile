# 1. 이미지를 선택한다.
FROM openjdk:17-jdk-slim

# 2. 명령어를 실행한다.
ARG MODE
COPY ./build/libs/dozzari.jar dozzari.jar
COPY ./.env .env

# 3. 도커 이미지를 띄운다.
ENTRYPOINT java -jar -Dspring.profiles.active=${MODE} dozzari.jar