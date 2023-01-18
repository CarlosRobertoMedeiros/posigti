#! /usr/bin/env bash

#docker-compose -f authorization_server.yml down
#docker-compose -f authorization_server.yml up -d

docker-compose -f C:/Desenvolvimento/sistemas/study-pos-igti/infra/docker/docker-compose.yml down
docker-compose -f C:/Desenvolvimento/sistemas/study-pos-igti/infra/docker/docker-compose.yml up -d

echo 'Keycloak started !'