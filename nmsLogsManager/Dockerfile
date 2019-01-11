FROM maven as build 
WORKDIR /code
COPY . . 
RUN mvn compile && mvn package -DskipTests=true


FROM java:8-jre-alpine
WORKDIR /
COPY --from=build /code/target/nms*.jar app
EXPOSE 8080 
CMD java -jar app 
