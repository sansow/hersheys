This YAML(hershey-broker.yaml) defines an ActiveMQArtemis broker with the following key configurations:
It's named "hershey-broker" in the "hersheys" namespace.
It has an AMQP acceptor on port 5672.
The admin username and password are both set to "admin".
The management console is exposed.
The deployment plan specifies:
The broker image to use
NIO journal type
Management RBAC enabled
Persistence enabled
No login required for the console
A cluster size of 2 brokers
