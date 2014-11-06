loadster-sdk-java
=================

This is the Java SDK for the Loadster Workbench API. As a desktop application, Loadster Workbench might not be the most obvious candidate to provide a REST API but we've had customer requests for better remote and programmatic access to certain features so this is where to start!

Philosophy
----------
While the API itself has RESTful semantics, this SDK is more about giving you what it takes to get things done fast. The SDK will end up feeling a lot more procedural than resource-based.

Package Structure
-----------------
[loadster.sdk.tasks](src/main/java/loadster/sdk/tasks) - Tasks that can be invoked from the command line, wrapped in a Maven mojo or Jenkins plugin, etc. For example, to kick off a load test remotely, check out [RunTest](src/main/java/loadster/sdk/tasks/RunTest.java).

[loadster.sdk.client](src/main/java/loadster/sdk/client) - Easy access to important API functions. Have a look at [WorkbenchApiClient](src/main/java/loadster/sdk/client/WorkbenchApiClient.java) as a starting point.

[loadster.sdk.types](src/main/java/loadster/sdk/types) - Dumb little POJOs that map very closely to the JSON responses coming back from the API. If the SDK does its job, you'll be dealing with these objects instead of the JSON itself.

[loadster.sdk.exceptions](src/main/java/loadster/sdk/exceptions) - When something goes wrong in the API, we try to map it to an exception so you don't have to worry about HTTP 2xx/3xx/4xx/5xx error codes and all that.

Licensing
---------
Loadster is a commercial product, but our SDK is available under the [Apache License v2](LICENSE).
