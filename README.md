# ImageColorAnalysis
Project to take a list of urls and find the 3 primary colors in each image

## Getting Started

To run do a pull request on the repository and build from an existing project with eclipse or intellij if you have maven configured on your machine. From there you can run in the IDE or command line with java if you have it configured on your system path. And you can run mvn test to run the tests if you have it configured on your system path.

### Prerequisites

To run have Java 8+ and have java on your environmental variables and path
Have maven 3.5+ and have it on your environmental variables and path
If maven is having trouble loading the referenced libraries open the pom xml and it will find them


```
[user@desktop ~]$ java Cluster
[user@desktop ~]$ mvn test
```

### Installing

If you have all the prerequisites just do a pull request on this repo and copy the source code into a folder

From there your ready to run this project


## Running the tests

The tests for this project use selenium with the chromedriver and some other tests for testing expected outputs within the program

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [OpenCV](https://opencv.org/) - Used to deal with images as natives instead of java bufferedimages as well as built in k-means calls
* [slf4j](https://www.slf4j.org/) - Used internally for logging
* [lombok](https://projectlombok.org/) - Used internally for logging
* [Speedment](https://www.speedment.com/) - Object Relational Mapper build with Streams and speed in mind
* [Mysql-connector](https://dev.mysql.com/downloads/connector/j/5.1.html) - Used with Speedment to connect to MySQL db
* [junit4](https://junit.org/junit4/) - Unit testing
* [selenium-java](https://www.seleniumhq.org/) - Automated browser testing
* [cucumber-java](https://cucumber.io/) - Behavior driven testing


## Authors

* **Shawn Anderson** - *Initial work* - [Wijkuy](https://github.com/Wijkuy)

## Acknowledgments

* [Badlogic](https://github.com/badlogic) - provided great resources to work around openCV's lack of java documentation
* [Ultraviolet](https://stackoverflow.com/users/5330223/ultraviolet) - provided a solution to convert a bufferedimage to a opencv mat
* etc
