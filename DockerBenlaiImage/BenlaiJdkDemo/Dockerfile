FROM openjdk:8u212-jdk

ENV BENLAI_JDK_DEMO_NAME="benlai.jdk.demo"

LABEL author="sheep.yang" \
	app.name="benlai.jdk.demo" \
	app.service.name=${BENLAI_JDK_DEMO_NAME} \

WORKDIR /usr/local/app

COPY ./target/release /usr/local/app

CMD [ "echo" "yy" ]