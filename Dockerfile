FROM java:8
ENV LANG=zh_CN.UTF-8
ENV LANGUAGE=zh_CN:zh:en_US:en
ENV LC_ALL=LC_ALL=zh_CN.UTF-8
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="\
 -server \
 -Xmx1g \
 -Xms1g \
 -Xss512 \
 -Xmn500m"
ADD target/resume-parse.jar /opt/resume-parse.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","-Dfile.encoding=utf-8","/opt/resume-parse.jar"]
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime
EXPOSE 8080
