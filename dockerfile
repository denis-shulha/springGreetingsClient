FROM ubuntu
RUN apt-get update \
&& apt-get install -y git gradle \
&& git clone https://github.com/denis-shulha/springgreetingsclient.git \
&& cd springgreetingsclient/ \
&& gradle build \
&& cd build/libs/ \
&& mv springGreetingsClient-1.0.jar /usr/bin/springGreetingsClient.jar 
ENTRYPOINT ["java","-jar","/usr/bin/springGreetingsClient.jar"]