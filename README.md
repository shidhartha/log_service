# log_service

This is the prototype for the Log Sequencing Service wich can be though as a run time log aggrgator service, which will given a root component name
1. can resolve log level dependency of othe component
2. and then go the log persistance layer for each of this dependent component and retrive the log
3. and then sort all the logs in chronological order

This service currently support the following APIS
1. GET on /log with component as a argument
```
curl http://localhost:9000/log?component=apache
```


# Architecture:
The Service is designed with the help of Akka Play framework and the service is designed to complete its functionality in with non-blocking method calls.

The main controller is assisted by 
1. Log ordering service which is reponsible for returing chronological ordered log statements accross various components
2. Log persistance service which is reponsible for retrieving logs from the real log storage. Note that for this prototype it is using a dummy S3 persistance service which is mocking the expected behavior.
3. Module dependency service which is repsonsible for resolving dependencies among modules for establishing business logic among the log generating component.

The serice can be deployed behind some HA-proxy server to handle required amount of load with required amount of instances.

# Running the application

The application can be run as

```
1. sbt run
```
Can be tested as 
```
 http://localhost:9000/log?component=apache
 http://localhost:9000/log?component=db
 http://localhost:9000/log?component=java_ms
 http://localhost:9000/log?component=php_ms
 ```
 
 UT
 ```
sbt test
```
