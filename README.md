# Hershey's AMQ Broker Starter Project

This repository contains the configuration and code for setting up an AMQ Broker with producer and consumer applications in OpenShift.

## Project Structure

- `hershey-producer/`: Quarkus-based producer application
- `hershey-consumer/`: Quarkus-based consumer application
- `yamls/`: YAML configuration files for OpenShift deployments

## AMQ Broker Configuration

The AMQ Broker is set up using the AMQ Broker Operator in OpenShift. The configuration includes:

- AMQP acceptor on port 5672
- Admin console exposed
- Persistence enabled
- 2 broker instances for high availability

## Producer Application

The producer application is a Quarkus-based service that sends messages to the AMQ Broker. It's configured to connect to the broker using the following environment variables:

- `AMQP_HOST`: hershey-broker-amqp-acceptor-0-svc
- `AMQP_PORT`: 5672
- `AMQP_USERNAME`: admin
- `AMQP_PASSWORD`: admin

## Consumer Application


The consumer application is also a Quarkus-based service that receives messages from the AMQ Broker. It uses the same connection configuration as the producer.

## Deployment

To deploy the applications:

1. Ensure the AMQ Broker Operator is installed in your OpenShift cluster.
2. Apply the AMQ Broker custom resource:
