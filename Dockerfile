# 该镜像需要依赖的基础镜像
FROM openjdk:8
# 调整时区
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone \
#将当前目录下的jar包复制到docker容器的/目录下
ADD target/wj-1.0.0.jar /wj-1.0.0/wj-1.0.0.jar \
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","wj-1.0.0/wj-1.0.0.jar"] \
