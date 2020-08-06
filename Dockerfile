FROM centos

RUN yum install -y java-11-openjdk-devel

VOLUME /tmp
ADD /pet-clinic-web-0.0.6-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom", "-jar", "/myapp.j>

