FROM maven as build 
WORKDIR /code
COPY . . 
RUN mvn compile && mvn package -DskipTests=true


FROM java:openjdk-8-jre
WORKDIR /
COPY --from=build /code/target/nms*.jar app
EXPOSE 8083 
CMD java -jar app 
