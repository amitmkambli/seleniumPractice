#docker run -e HUB_HOST=192.168.0.103 -e BROWSER=chrome -e THREAD_COUNT=3 amitdocker369/selfw 
FROM bellsoft/liberica-openjdk-alpine:17.0.11
WORKDIR /home/selenium-docker
ADD target/docker-resources /home/selenium-docker
ENTRYPOINT java -cp 'libs/*' \
 -DonGrid=true \
 -DgridHost=${HUB_HOST} \
 -Dbrowser=${BROWSER} \ 
 -DthreadCount=${THREAD_COUNT} \ 
 org.testng.TestNG testng.xml