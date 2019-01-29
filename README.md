# wp-test-demo-java
Project to demonstrate my automation testing skills in Java, as part of my 2019 job search for SDET positions.

This suite of test cases runs against an example Wordpress site. It uses Wordpress REST API endpoints to retrieve data about the test site, and analyzes JSON payloads returned by these endpoints.

In Phase 1 of this demo, I am working with the endpoints that provide publicly available data, such as the posts, categories, tags, and pages visible on the site.

## Prerequisites

To run this suite, I set up a test Wordpress site using Docker Compose. You can see the docker-compose.yml file I use to set up the containers on my [misc-configs repo](https://github.com/annathepiper/misc-configs/blob/master/docker-compose.yml). 

I also add aliases for wordpress.local and phpmyadmin.local to my hosts file, so that those URLs will work as the automation runs.

The test data I'm using is a copy of one of my [live Wordpress sites](http://angelahighland.wordpress.com), which has provided me the items to fill in for my properties file.

The main tool I'm using to run the suite is IntelliJ. The IML file for IntelliJ is included in this repo, as well as the pom.xml, as this is set up as a Maven project. I use the Unirest library to hit the Wordpress endpoints.

### Maven dependencies

Dependencies included in the POM are:

* org.json
* com.mashape.unirest
* org.testng
* org.apache.maven.plugins (for Surefire, and exclusion to force use of log4j 2)
* org.apache.log4j (version 2)

## Skills and tech I'm demonstrating here

* Use of a properties file to set test-specific strings like ID numbers, titles, and names
* Using the Unirest library to hit the service endpoints
* Using the org.json classes to process JSON payloads
* Using TestNG functionality to run the test suite, and to annotate test methods
* Use of a POM file to set Maven dependencies and to run the test suite via Maven as well as TestNG
* Testing against a site running as a Docker container
* Use of Log4j to do basic logging

## Running the code yourself

If you'd like to try running this suite yourself, you will need to do the following:

1. Install IntelliJ IDEA if you don't have it already.
2. Install Docker if you don't have it already.
3. Install git if you don't have it already.
4. Edit your local hosts file to add aliases for wordpress.local to 127.0.0.1 (and phpmyadmin.local if you're using my PHPMyAdmin container)
5. Create a local Wordpress instance with Docker (see my misc-configs repo for a wp-docker-compose.yml you can use as a reference, and modify as appropriate for your testing).
6. Configure your local Wordpress with whatever test data you want to use. This may involve importing data of your own in from another site, or otherwise generating test posts, pages, etc. via whatever means you have available.
7. Check out this repo on your own system.
8. Import the project into your IntelliJ.
9. In IntelliJ, use the testng.xml file to create a configuration you can use to run the test cases. It's a suite file, so you'll want to choose "Suite" in the configuration settings. All other settings can remain as default.
10. Open the wp-test-demo.properties file and edit it as appropriate to reflect what test data you're using
11. If you use my Postman collection and environment, you should also tweak the environment settings to reflect your test data. The environment file should match what you put into the properties file in IntelliJ.

### Running the code on a Windows 10 environment

If you want to run this demo on Windows 10, you may need to take additional steps depending on whether you're using the Windows 10 Linux subsystem. See the tutorial link I include in the reference links below.

My Windows 10 setup for this demo includes running the Docker containers within the Linux subsystem. My Windows IntelliJ can't see the subsystem, so on this environment, I also installed Cygwin (with git) to do my code checkout in a place where IntelliJ could see it.

## Reference links
* [Misc-configs repo](https://github.com/annathepiper/misc-configs) where I have my Docker Compose file for a test Wordpress site, and my Postman files 
* [Unirest](http://unirest.io/java.html), the Java library I'm using to do the REST calls
* [Wordpress REST API](https://developer.wordpress.org/rest-api/), reference source for the endpoints I use in this demo
* [Setting Up Docker for Windows and WSL to Work Flawlessly](https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly), tutorial I followed to set up Docker to run on the Windows 10 partition of my dev box
* [Cygwin](http://www.cygwin.com/)
* [Postman](https://www.getpostman.com/)
* [IntelliJ](https://www.jetbrains.com/idea/)
