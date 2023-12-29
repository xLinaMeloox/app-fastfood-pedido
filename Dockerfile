FROM amazoncorretto:17-alpine-jdk

RUN apk add --no-cache openssl perl
RUN mkdir /tmp/certs
WORKDIR /code
COPY ./app /code/app
COPY ./app/importCert.sh /certs/importCert.sh
RUN chmod +x /certs/importCert.sh
ARG trustStorePassword
RUN /certs/importCert.sh $trustStorePassword

ENV TRUST_STORE_PASSWORD=$trustStorePassword

CMD ["sh", "-c", "java -Djavax.net.ssl.trustStore=/tmp/certs/rds-truststore.jks -Djavax.net.ssl.trustStorePassword=$TRUST_STORE_PASSWORD -jar /code/app/app-fastfood.jar"]