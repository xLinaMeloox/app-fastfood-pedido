FROM amazoncorretto:17-alpine-jdk

WORKDIR /code

COPY ./app /code/app

RUN wget https://truststore.pki.rds.amazonaws.com/global/global-bundle.pem
RUN mv global-bundle.pem app/global-bundle.pem

CMD ["sh", "-c", "java -jar /code/app/app-fastfood.jar"]