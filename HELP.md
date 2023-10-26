
# Getting Started

### Run the application
Execute the following command

./gradlew bootRun

### API 
Hit the following endpoint to generate the csv report

http://localhost:8080/summaryReport

## Create Docker Image
docker build -t future-movement-docker:latest .

## Run Docker Image
docker run -p 8080:8080 future-movement-docker:latest   


### Angular app
To run the angular app go to src/main/frontend/trade-summary and execute

npm install
npm start

