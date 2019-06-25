FROM jenkins/jnlp-slave:latest

#MAINTAINER huwanyang168@163.com

#LABEL Description="This is a extend image base from jenkins/jnlp-slave which install maven in it."

#配置环境变
ENV MAVEN_VERSION 3.6.1

# 切换到 root 账户进行操作
USER root

# 安装 maven-3.6.1
RUN wget http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
    tar -zxf apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
    mv apache-maven-${MAVEN_VERSION} /usr/local && \
    rm -f apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
    ln -s /usr/local/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn && \
    ln -s /usr/local/apache-maven-${MAVEN_VERSION}} /usr/local/apache-maven
