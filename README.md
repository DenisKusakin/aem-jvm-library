# AEM JVM Library (Early development stage)

*Scripting over multiple AEM servers*

Tiny library for accessing and performing various actions on running AEM servers.

## Motivation

Working with AEM often requires performing specific actions on running servers.
For example, you might need to check bundles status, restart them or list bundles in resolved state.
Sometimes it is needed to compare content between two instances or check replication agents queue
or just flush the queue.
These action could be performed using various AEM consoles, some of them could be performed using AEM Groovy Console.
Consoles provide interfaces for simple cases, but often it would be better to execute actions using code,
such approach allows to easily build complicated flows.
The goal of this project is to develop API for accessing AEM servers from Java/Groovy code.

## Examples (groovy-like)

```groovy
    // Output project bundles state
    myLocalAuthor
        .bundlesService
        .bundles()
        .filter { it.symbolicName.contains("my-project") }
        .forEach { System.out.println("${it.symbolicName} -> ${it.state}") }

    // Start project bundles in Resolved state
    myLocalAuthor
        .bundlesService
        .bundles()
        .filter { it.symbolicName.contains("my-project") }
        .filter { it.state == RESOLVED }
        .forEach { it.start() }

    // Using two AEM server to get some information
    def anotherServerBundles = anotherServer.bundlesService.bundles()
    myLocalAuthor
        .bundlesService
        .bundles()
        .forEach {
            def theSameBundleOnAnotherServer = anotherServerBundles.find{ x -> x.symbolicName == it.symbolicName}
            if(theSameBundleOnAnotherServer == null){
                System.out.println("Bundle ${it.symbolicName} not found on another server")
            }
        }
```