# Kafka Message Processor

## Overview
The Kafka Message Processor is a Java application designed to listen to messages from a Kafka topic and save them to a PostgreSQL database. The project utilizes Docker to run Kafka and PostgreSQL instances for easy setup and deployment.

## Features
- Listens to messages from a Kafka topic.
- Stores received messages in a PostgreSQL database.
- Dockerized setup for Kafka and PostgreSQL.


### Libs & Frameworks

| Tech            | Version    |
|-----------------|------------|
| JDK             | 17         |
| SpringBoot      | 3.2.3      | 
| Spring Data JPA | 3.2.3      | 
| Spring Kafka    | 3.1.2      | 
| Lombok          | 1.18.30    | 

## Prerequisites
Before running the application, ensure you have the following installed:

- Docker: Make sure you have Docker installed on your machine. You can download and install Docker from [here](https://www.docker.com/get-started).
- Maven: Ensure you have Maven installed. You can download Maven from [here](https://maven.apache.org/download.cgi) and follow the installation instructions.
- Java 17+: Make sure you have Java Development Kit (JDK) version 17 or higher installed on your machine. You can download and install Java from [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [adoptOpenJDK](https://adoptopenjdk.net/) for open-source alternatives.




## Setup
1. Clone this repository: 
```bash
   git clone git@github.com:Khachatur-Asmaryan/kafka-message-processor.git
```
2. Start Kafka and PostgreSQL Docker containers:
```bash
docker-compose up -d
```
3. Build the application using Maven:
```bash
mvn clean package
```
4. Run the Kafka Message Processor application:
```bash
java -jar target/kafka-message-processor-0.0.1-SNAPSHOT.jar
```

## Usage
- Upon running the application, it will start listening to messages from the configured Kafka topic.
- Received messages will be saved to the configured PostgreSQL database.

## Running Kafka Console Producer

To send messages to your Kafka topic within the Docker network, you can use the following command:
```bash
docker run --rm -it --network host wurstmeister/kafka /opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic kafkamessageprocessor
```
This command runs the Kafka console producer within a Docker container and connects it to the Kafka broker running at localhost:9092. It then prompts you to enter messages interactively. After running the command, you'll see a new line where you can input your messages.

## Entering Messages
After executing the above command, you'll see a new line like this:
```
>
```
You can now type your message and press Enter to send it to the Kafka topic. For example, to send a message, you can type:
```
{ "firstName" : "John", "lastName" : "Doe", "age" : 32 }
```

Press Enter after typing your message to send it to the Kafka topic. You can input multiple messages this way.
## Employee Message Example

Here's an example of a message representing an employee that you can send to the Kafka topic:

``` json
{"firstName": "someName", "lastName": "someLastName", "age": 32}
```
## Connecting to PostgreSQL Docker Container

To connect to the PostgreSQL database running in a Docker container, you can use the `psql` command-line utility. Here's how:

1. Open a new terminal window or command prompt.

2. Run the following command to connect to the PostgreSQL database container:

```bash
docker exec -it pg_container psql -h localhost -U postgres -d kafka_message_processor -p 5433 -W
```

After executing the command, PostgreSQL will prompt you to enter the password for the postgres user. Type root as the password and press Enter.

This will connect you to the kafka_message_processor database.

After executing the command, you'll see a prompt similar to this:
```
kafka_message_processor=#
```
At this prompt, you can execute SQL queries to interact with the database.

To select all saved employees from the employee table, you can use the following SQL query:
```
SELECT * FROM kafka_message_processor.employee;
```

After executing your SQL queries, you can exit the psql CLI by typing:
```
\q
```
This will quit the 'psql' session and return you to the command prompt.
