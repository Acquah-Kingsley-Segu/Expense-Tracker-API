# pull maven alpine image
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

# Create a directory in container
WORKDIR /expense_tracker

# Copy local directory content into container directory
COPY . .

# Build the application
RUN mvn -DskipTests clean package

# Pull java 17 jdk image
FROM eclipse-temurin:17-jdk-alpine

COPY --from=build expense_tracker/target/springboot.expense_tracker-0.0.1-SNAPSHOT.jar expense_tracker.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "expense_tracker.jar"]
