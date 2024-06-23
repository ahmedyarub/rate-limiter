A microservice that uses sliding-window based rate-limiting to control sending of notifications 

RUNNING INSTRUCTIONS
====================

1- Run PostgreSQL and Redis containers using the command:
docker compose up
2- Run the service using the command:
mvn spring-boot:run
3- Send requests to endpoint http://localhost:8080/api/notification/send, ex:
curl -X POST --location "http://localhost:8080/api/notification/send" \
-H "Content-Type: application/json" \
-d '{
"notificationType": "news",
"user": "user 1",
"text": "notification text"
}'