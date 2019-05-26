# Overview

Product to Test Kubernetes Stuffs

# Setup

1. Start NATS Server

2. Start MongoDB

3. Execute the command above:

`mvn clean compile quarkus:dev`

# Docker

### Docker JVM

`mvn clean package && docker build -f src/main/docker/Dockerfile.jvm -t larchanjo/product .`

### Docker Native

` mvn package -Pnative -Dnative-image.docker-build=true && docker build -f src/main/docker/Dockerfile.native -t larchanjo/product .`

# Postman

The postman collection is located at `.../src/main/resources/postman`

# Troubleshooting

If the nats consumer doesn't processing the events, you need to make a request in `...\health` to inject all the configurations