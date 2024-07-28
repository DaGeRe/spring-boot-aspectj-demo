# Spring Boot vs. Spring + Jetty AspectJ Demo

This repository demonstrates how spring boot and spring with jetty behave on instrumentation.

To execute the example, please run the following commands:

- Building the aspect: `cd aspect && ./mvnw clean package && cd ..`
- Execute the Spring + Jetty demo: `cd demo-spring-jetty && ./gradlew clean assemble && java -javaagent:../aspect/target/aspectjtest-0.1-SNAPSHOT.jar -jar build/libs/demo-spring-jetty-0.0.1-SNAPSHOT.jar`, and in another terminal, run `curl localhost:8080`
  * You'll see that the `de.aspectjtest.ExampleAspect` is successfully woven into by the weaveinfo-output
  * Furthermore, everytime, you run `curl localhost:8080`, an output like `=== Call1: String com.example.demo.HelloController.index() method-execution` is created
- Execute the Spring Boot demo: `cd demo-spring-boot && ./gradlew clean assemble && java -javaagent:../aspect/target/aspectjtest-0.1-SNAPSHOT.jar -jar build/libs/demo-spring-boot-0.0.1-SNAPSHOT.jar`, and in another terminal, run `curl localhost:8080`
  * There is no weaveinfo-output at all
  * Furthermore, everytime, you run `curl localhost:8080`, no output is created, indicating that weaving didn't take place
