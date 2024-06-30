# Test Automation Framework

## Overview
This is a test automation framework built using TestNG and Maven. It supports data-driven testing and custom logging with Extent Reports.

## Prerequisites
- Java 8 or higher
- Maven 3.6.0 or higher

## Running Tests
To execute the tests, use the following Maven command. You can specify the environment using the `-Denv` parameter.

### Command
```sh
mvn clean install -Denv="qa"
