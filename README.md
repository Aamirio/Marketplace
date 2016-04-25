# Marketplace
A basic auction API

====================================
IMPLEMENTATION
====================================
All development was done using Intellij 14.1.5

I have created a separate service and dao layer for this API.

All CRUD operations are in the dao layer. All business logic is in the service layer.

The service and dao layer are loosely coupled using the JSR-330 javax.inject annotations.

Note: I have used Java 8 Lambda statements in a couple of places to sort an array list using the functional Comparator interface.

=====================================
UNIT AND INTEGRATION TESTS
=====================================

All unit tests and integration tests can be run by running the "mvn clean verify" goal.

Service layer unit tests use mockito for mocked dao injections.

Service layer integration tests use SpringJUnit4ClassRunner.class and Spring Config to build the app context.

I could have written the integration tests using Cucumber (BDD) but this was not a specified requirement.
