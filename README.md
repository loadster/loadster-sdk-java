loadster-sdk-java
=================

This is the Java SDK for the Loadster Workbench API. As a desktop application, Loadster Workbench might not be the most obvious candidate to provide a REST API but we've had customer requests for better remote and programmatic access to certain features so this is where to start!

To learn about how to use the SDK with Loadster Workbench, see [The Loadster Workbench API](http://www.loadsterperformance.com/documentation/api/).

Usage
-----
The SDK is available via the repository at [https://bintray.com/loadster/loadster/loadster-sdk-java/](https://bintray.com/loadster/loadster/loadster-sdk-java/). Follow the instructions there to include it in your Gradle, Maven, or SBT build.

You can also download pre-built jars from [releases](https://github.com/loadster/loadster-sdk-java/releases).

Use the SDK library with your own code to build integrations, or by itself if you want to kick off a test from the command line (see below).

Philosophy
----------
While the API itself has RESTful semantics, this SDK is more about giving you what it takes to get things done fast. The SDK will end up feeling a lot more procedural than resource-based.

Package Structure
-----------------
[loadster.sdk.tasks](src/main/java/loadster/sdk/tasks) - Tasks that can be invoked from the command line, wrapped in a Maven mojo or Jenkins plugin, etc. For example, to kick off a load test remotely, check out [RunTest](src/main/java/loadster/sdk/tasks/RunTest.java).

[loadster.sdk.client](src/main/java/loadster/sdk/client) - Easy access to important API functions. Have a look at [WorkbenchApiClient](src/main/java/loadster/sdk/client/WorkbenchApiClient.java) as a starting point.

[loadster.sdk.types](src/main/java/loadster/sdk/types) - Dumb little POJOs that map very closely to the JSON responses coming back from the API. If the SDK does its job, you'll be dealing with these objects instead of the JSON itself.

[loadster.sdk.exceptions](src/main/java/loadster/sdk/exceptions) - When something goes wrong in the API, we try to map it to an exception so you don't have to worry about HTTP 2xx/3xx/4xx/5xx error codes and all that.

Running a Test
--------------

If you have Loadster Workbench running, you can trigger a test remotely via the SDK. This is helpful if you want to integrate load testing into your CD pipeline or run tests on a schedule.

```
$ java -cp loadster-sdk-1.4-all.jar -Dloadster.api.key=changeme -Dloadster.api.host=localhost loadster.sdk.tasks.RunTest <project-id> <scenario-id> report.html
```

The ```<project-id>``` and ```<scenario-id>``` can be obtained by right clicking your test scenario in Loadster Workbench and selecting "Info...". The values will be in a path like ```/projects/<project-id>/scenarios/<scenario-id>```.

You can obtain and set your Loadster Workbench API key in Preferences.

Compatibility
-------------

| Loadster Workbench Version | Recommended SDK Version |
| -------------------------- | ----------------------- |
| 3.9.6+                     | 1.5                     |
| 3.6.0-3.9.5                | 1.2                     |
| 3.2.0-3.5.6                | 1.0                     |

Licensing
---------
Loadster is a commercial product, but our SDK is available under the [Apache License v2](LICENSE).
