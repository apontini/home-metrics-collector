FROM openjdk:17-jdk-bullseye

RUN apt update && apt install -y findutils bluez bluetooth

EXPOSE 8080:8080

RUN mkdir /app

COPY ./build/install/home-metrics-collector/ /app/
COPY ./docker/start.sh /app/bin/start.sh

WORKDIR /app/bin

CMD ["./start.sh"]