# wp-test-demo-java
Project to demonstrate my automation testing skills in Java, as part of my 2019 job search for SDET positions.

This suite of test cases runs against an example Wordpress site. It uses Wordpress REST API endpoints to retrieve data about the test site, and analyzes JSON payloads returned by these endpoints.

In Phase 1 of this demo, I am working with the endpoints that provide publicly available data, such as the posts, categories, tags, and pages visible on the site.

## Prerequisites

To run this suite, I set up a test Wordpress site using Docker Compose. You can see the docker-compose.yml file I use to set up the containers on my misc-configs repo, here: https://github.com/annathepiper/misc-configs/blob/master/docker-compose.yml

I also add aliases for wordpress.local and phpmyadmin.local to my hosts file, so that those URLs will work as the automation runs.

The main tool I'm using to run the suite is IntelliJ. The IML file for IntelliJ is included in this repo, as well as the pom.xml, as this is set up as a Maven project. I use the Unirest library to hit the Wordpress endpoints.

### Maven dependencies

Dependencies included in the POM are:

* org.json
* com.mashape.unirest
* org.testng

## Skills and tech I'm demonstrating here

* Use of a properties file to set test-specific strings like ID numbers, titles, and names
* Using the Unirest library to hit the service endpoints
* Using the org.json classes to process JSON payloads
* Using TestNG functionality to run the test suite, and to annotate test methods
* Use of a POM file to set Maven dependencies

## Reference links
* Misc-configs repo where I have my Docker Compose file for a test Wordpress site: https://github.com/annathepiper/misc-configs
* Unirest: http://unirest.io/java.html
* Wordpress REST API: https://developer.wordpress.org/rest-api/
