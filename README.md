# Marketplace
A basic auction API

All unit tests and integration tests can be run by running the "mvn clean verify" goal.

Service layer unit tests use mockito for mocked dao injections.

Service layer integration tests use SpringJUnit4ClassRunner.class and Spring Config to build the app context.

I could have written the integration tests using Cucumber but this was not a specified requirement.
