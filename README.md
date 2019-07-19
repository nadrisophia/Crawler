# Crawler
## Requirements

* npm
* java 8

## Structure
This project contains 2 modules:
  ### Crawler:
  This is a spring boot application that will serve the crawler on port 8080
  ### CrawlerUI:
  This is a simple React application where you can submit the url you want to crawl and view your results. 
  It is running on port 3000
## Building and Running
### Crawler

* ./gradlew build
* ./gradlew bootRun

### CrawlerUI

* npm install
* npm start
## Usage
the url input requires the prefixes http:// or https://
