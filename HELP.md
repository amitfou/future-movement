
# Getting Started

### Run the application
Execute the following command

./gradlew bootRun

### API 
Hit the following endpoint to generate the csv report

http://localhost:8080/summaryReport

## Create Docker Image
docker build -t future-movement-docker:latest .

### Run Docker Image
docker run -p 8080:8080 future-movement-docker:latest   


## Angular app
To run the angular app go to src/main/frontend/trade-summary and execute

npm install

npm start

### View the webapp
http://localhost:4200/


## To deploy to Kubernetes
Make sure you have minikube running locally and you have already created the docker image.

Run the below command to deploy to minikube

kubectl apply -f deployment.yaml 

### check the deployment

kubectl get deployments

### Access the api
Run below command to check the url

minikube service future-movement-service

Access the url to call the api, make sure you add the context path /summaryReport
