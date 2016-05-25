# Application - Java Architect (with NoSql and SOLR)

### by Tiago Vinicius

## Setup

#### Solr
Application was built on top of Solr 6. Java 8 is also required. Copy *news* folder to `$SOLR_INSTALLATION_DIR/server/solr/`, and start with `./$SOLR_INSTALLATION_DIR/bin/solr start`. Standalone collection must be initialized. `solrconfig.xml`, `schema.xml` and `data-config.xml` were modified to configure DIH and document fields.

#### MySQL
Initialize MySQL according to your installation choice, create a `news` database. `user` and `password` must both be `root`

#### Nginx
Just initialliza Nginx. Images are retrieved from `/usr/share/nginx/html`. If you like Docker, can use command:, `docker create -h nginx --name nginx -v /tmp/images:/usr/share/nginx/html:ro -p 80:80  nginx` and put images in /tmp/images of host machine

#### Run Spring-boot app
Just execute `mvn clean install && mvn spring-boot:run`. **Java 8 required!!!**. Access application url in your browser: `http://localhost:8080`

## Create data

#### MySQL
- Execute script located in `SQL/script.sql` into your MySQL client to populate database

#### Solr
- Execute Data Import . Solr DIH will be executed and fetch data from MySQL and populate Solr index.


## Assumptions
- Solr is running Standalone mode
- For admin users, a *create article* link is available; other roles just show user name and a logout link
- Nginx serves image urls


## Limitations
- Single application to handle user search requests and backoffice operations
- there are some issues in frontend flow, some missaligned components
- there are just unit tests. No integration tests
- Solr was not configured in SolrCloud mode, just standalone for dev purposes
- Lack of functionalities using Solr capabilities like: highlight, facets, spellchecker, suggester and many others


## Improvements
- Decouple application into frontend and backoffice applications
- Schedule dataimport process; use JPA EntityListeners to trigger when a new Article is saved in data base; Decouple by sending a message to a message broker like RabbitMQ or even Apache Kafka, create a consumer to listen broker and index data on Solr
- Several frontend improvements
- If AWS infra is available, we could use Amazon S3 to image storage instead of Nginx
- Solr highlight as content snippets in search
- Solr facets on following fields: tags, author and category
- Nginx serving application in front of tomcat
- Solr Suggester to auto-complete for Search box (Ternary search trie)
- Solr Spellchecker ("Did you mean?") to suggest a list of words already in index (Levenstein distance)
- Integration Behavior driven-tests with Cucumber