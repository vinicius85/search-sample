# Application - Java Architect (with NoSql and SOLR)

### by Tiago Vinicius


## Setup

#### Run Spring-boot app
Just execute `mvn clean install && mvn spring-boot:run`. **Java 8 required!!!**

#### Solr
Application was built on top of Solr 6. Java 8 is also required. Copy *news* folder to `$SOLR_INSTALLATION_DIR/server/solr/news`, and start with `./$SOLR_INSTALLATION_DIR/bin/solr start`

#### MySQL
Initialize MySQL according to your installation choice, create a `news` database. `user` and `password` must both be `root`

#### Nginx
Just initialliza Nginx. Images are retrieved from `/usr/share/nginx/html`. If you like Docker, can use command:, `docker create -h nginx --name nginx -v /tmp/images:/usr/share/nginx/html:ro -p 80:80  nginx` and put images in /tmp/images of host machine


## Create data
Execute script sql and sample users and articles will be available in database.

#### MySQL
- Execute: /SQL/script.sql to import first data samples into database

#### Solr
- Execute: Data Import Handler in Solr interface. Solr DIH will be executed and fetch data from MySQL and populate Solr index.


## Assumptions
- Solr is running Standalone mode
- For admin users, a *create article* link is available; other roles just show user name and a logout link
- Nginx serves image urls
- Solr DIH to easily import data from relational database
- Bootstrap and AngularJS to  provide css and js


## Limitations
- Single application to handle user search requests and backoffice operations
- Solr was not configured in SolrCloud mode, just standalone for dev purposes
- Lack of functionalities using Solr capabilities like: highlight, facets, spellchecker, suggester and many others.
- Some interface issues


## Improvements
- Decouple application into frontend and backoffice applications
- Schedule dataimport process: use JPA EntityListeners to trigger when a new Article is saved in data base or Decouple by sending a message to a message broker like RabbitMQ or even Apache Kafka, create a consumer to listen broker and index data on Solr
- Several frontend improvements
- If AWS infra is available, we could use Amazon S3 to image storage instead of Nginx
- AngularJS to implement infinite scrolling pattern
- Use flyway to versioning sql scripts
- Solr capabilities
 - SuggesterComponent: auto-complete when user types on searchbox
 - SpellcheckComponent: if user typo, suggest the "right" word
 - Facets: count by tags, category and author
 - Configure Standard select RequestHandler qf parameter to search other fields than just title