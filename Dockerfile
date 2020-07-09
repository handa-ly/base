#指定基础镜像，在其上进行定制
FROM openjdk:11.0.4

#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /data 中写入的信息都不会记录进容器存储层
VOLUME /tmp

#复制上下文目录下的target/demo-1.0.0.jar 到容器里
COPY target/demo-0.0.1-SNAPSHOT.jar /home/demo-0.0.1-SNAPSHOT.jar

#bash方式执行，使test.jar可访问
#RUN新建立一层，在其上执行这些命令，执行结束后， commit 这一层的修改，构成新的镜像。
RUN bash -c "touch /home/demo-0.0.1-SNAPSHOT.jar"

#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 6000

#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
#java -jar /home/demo/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --spring.quartz.properties.org.quartz.jobstore.isClustered=true
ENTRYPOINT ["java","-jar","home/demo-0.0.1-SNAPSHOT.jar"]