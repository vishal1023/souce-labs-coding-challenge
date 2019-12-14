#Pre-Requisites
Java 8 or higher
Maven

#Build
you can build this application using below command
mvn clean install

#Run
To run this application you can simply build it and run it using java command 
e.g. java -jar <location of jar file>

#Service details
Available end points - 
    /magnificent/health-matrix
    /magnificent/health

Assumption about the requirement
1. Need the health status in log --
    - Based on the logs if User wants to get the details of the service health 
    she will be able to see the logs with corresponding messages
  
2. Some way of getting the service availability  over the last little while.
 
Additional Info 
 - For the over all performance I have exposed another end point to get the matrix of health check (e.g. Number of success, failure and unresponsive behavior of the Magnificent service.)    
    -This matrix implementation can be extended to get based on some time interval which can be used for some analysis.
    
 - I also wanted to implement a notification service which sends a notification to User (e.g. Via Email) after specific number of failures; however because of lack of time I am unable to implement it.
 