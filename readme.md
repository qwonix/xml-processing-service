# XML Processing Service

## Introduction

Welcome to the XML Processing Service project!
This application is designed as a **job specification**.
It is designed to accept an incoming XML document and then process it using XSLT transformation.
The original and transformed documents are further accessed via API.

## Requirements

It is necessary to develop an application to receive an incoming XML document and then process it.
The client has an xml file (original), which he uploads from his system to a certain directory.
It is necessary to upload it and process it.

### Steps of document processing

1) Loading the original file from the `z` directory (if the file is read, delete it from the directory)
2) Applying xslt conversion to the original file
3) Saving the original and the resulting xml to the database (the resulting xml should have a link to the original file)

## Technologies Used

* Java 1.8
* [Spring Framework 4.3](https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/htmlsingle/)
* [H2](https://www.h2database.com/html/main.html)
* [Hibernate](https://hibernate.org/)
* XSLT, XML
* [Apache Camel](https://camel.apache.org/)

## Configuration Properties

Configure the behavior application using the following properties:

| Property Name          | Description            | Default Value       | Allowed Values                                                                                                                                                           |
|------------------------|------------------------|---------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `path.to.directory.z`  | File folder path       | `d:/z`              | String                                                                                                                                                                   |
| `path.to.xsl`          | `.xsl` file path       | `d:/idoc2order.xsl` | String                                                                                                                                                                   |
| `directory.check.rate` | Folder check frequency | `2000`              | [Duration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.conversion.durations) |

## API Endpoints

| Method | Path                             | Description                  |
|:------:|----------------------------------|------------------------------|
|  GET   | `/api/document`                  | Get list of all documents    |
|  GET   | `/api/document/{id}`             | Get document by id           |
|  GET   | `/api/document/{id}/original`    | Get original xml document    |
|  GET   | `/api/document/{id}/transformed` | Get transformed xml document |
