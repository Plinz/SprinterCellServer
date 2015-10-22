from debian 
run apt-get update && \
    apt-get install -y maven openjdk-7-jdk && \
    apt-get clean 
add pom.xml /srv/SprinterCellServer/
workdir /srv/SprinterCellServer/
run mvn install
add src /srv/SprinterCellServer/src/
expose 8080
cmd mvn jetty:run
