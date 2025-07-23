
//FROM eclipse-temurin:24-jdk AS build


//WORKDIR /app


# COPY mvnw .
# COPY mvnw.cmd .
# COPY .mvn .mvn
# COPY pom.xml .


# RUN ./mvnw dependency:go-offline


# COPY src src

# RUN ./mvnw clean package -DskipTests


# FROM eclipse-temurin:24-jre


# WORKDIR /app




# EXPOSE 8080


# CMD ["java", "-jar", "app.jar"]