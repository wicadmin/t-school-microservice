# Spring Boot app Integration with IBM Cloudant

*This project is part of the 'IBM Cloud Native Reference Architecture' suite, available at
https://github.com/ibm-cloud-architecture/refarch-cloudnative*

## Introduction

This project is built to demonstrate how to build a Spring Boot Microservices application to access IBM Cloudant NoSQL database. It provides basic operations of saving and querying accounts from database. The project covers following technical areas:

 - Leverage Spring Boot framework to build Microservices application
 - Use IBM Cloudant Spring Boot Starter to access Cloudant database

## Prerequsite
To complete this tutorial, you will need the following:

JDK 1.8
Gradle 2.3+
Git client

## Git clone
`https://github.com/shettygit/t-school-microservice.git`

## Provision Cloudant Database in IBM Cloud:

Login to your IBM Cloud console  
Open browser to create Cloudant Service using this link [https://new-console.ng.bluemix.net/catalog/services/cloudant-nosql-db](https://new-console.ng.bluemix.net/catalog/services/cloudant-nosql-db)  
Name your Cloudant service name like `t-school-cloudantdb`  
For testing, you can select the "Lite" plan, then click "Create"  
Once created, open the credential tab to note down your Cloudant Service credential, for example:

```
{
 "username": "3xxxx-44f0d2add79e-bluemix",
 "password": "xxxxxxxxxxxxxxxxxxxxxx",
 "host": "xxxxx-bluemix.cloudant.com",
 "port": 443,
 "url": "https://xxxxx-bluemix.cloudant.com"
}
```
Then, click the "Launch" button to open the Cloudant management console.   

You can close the console now.

## Run the application locally:

- Create Cloudant database

    Once you have cloudant setup, update the src/resources/application.yml file for the Cloudant credential:

    ```yml
    # Cloudant Configuration
    cloudant:
        db: accountdb
        username: {your_cloudant_username}
        password: {your_cloudant_password}
        host: https://{your_cloudant_host}
    ```

- Run following command to build and test the application:

    ```
    $ ./gradlew build
    ```
    If you get following error.
    `If you get Error: Could not find or load main class org.gradle.wrapper.GradleWrapperMain`
    Issue Command: 
    ```
    $ gradle wrapper
    ```
    Then Command:
    ```
    $ ./gradlew build
    ```

- To run the app:

    ```
    $ java -jar build/libs/micro-socialreview-0.1.0.jar
    ```

- Validate the application:

    Go to the reviews endpoint to see all reviews in the database: http://localhost:8080/micro/account

    You can use Chrome POSTMAN to insert a new review document using the following sample content:

    ```json
     {
      "comment": "VIP Customer",
      "accountId": 14402,
      "accountType": "Checking",
      "ownerEmail": "gangchen@us.ibm.com",
      "ownerName": "Gang Chen",
      "accountOpenDate": "01/31/2017"
     }
    ```

    Or you can use the following curl command:
    ```
    curl --request POST\
        --url 'http://localhost:8080/micro/review' \
        --header 'accept: application/json' \
        --header 'content-type: application/json' \
        --data @- <<'EOF'
        {
         "comment": "VIP Customer",
         "accountId": 14402,
         "accountType": "Checking",
         "ownerEmail": "gangchen@us.ibm.com",
         "ownerName": "Gang Chen",
         "accountOpenDate": "01/31/2017"
        }' 
        EOF
    ```
To query the account that you just inserted use url http://localhost:8080/micro/account?accountId=14402

Next Steps for you to try (no instruction available):

* Add accountBalance filed to your account entity, add new JSON document with the newly added field and query to make sure you can pull entire record.
* Create a new git repository and push your source code to a newly created git repository
* Configure delivery pipeline in IBM Cloud to pull the source code from your git repository and deploy the application on to cloud foundry or kubernetes cluster.

