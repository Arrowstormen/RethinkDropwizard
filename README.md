# RethinkDropwizard

To run this prototype type in the following in the root directory of the project:

mvn liquibase:dropAll (to delete localdb)
mvn liquibase:update (To create and seed to new local db)

mvn clean   (remove old compiled package)
mvn package (compile package)

java -jar target/dropwizard-demo-1.0-SNAPSHOT.jar server hello-world.yml (run program with server option)