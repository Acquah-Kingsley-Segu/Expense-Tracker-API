# pull maven alpine image
FROM maven:3.9.7-eclipse-temurin-17-alpine AS build

# Create a directory in container
WORKDIR /expense_tracker

# Copy local directory content into container directory
COPY . .

# Build the application
RUN mvn -DskipTests -Dspring.profiles.active=production clean package

# Pull java 17 jdk image
FROM openjdk:17.0.1-jdk-slim

COPY --from=build expense_tracker/target/springboot.expense_tracker-0.0.1-SNAPSHOT.jar expense_tracker.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "expense_tracker.jar"]
