FROM openjdk:11
COPY out/artifacts/Nintendon_t_jar/Nintendon-t.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]