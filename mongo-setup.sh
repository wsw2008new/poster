#!/bin/bash
rm -rf /srv/mongodb/
mkdir -p /srv/mongodb/rs0-0 /srv/mongodb/rs0-1 /srv/mongodb/rs0-2 /srv/mongodb/rs0-3
mongod --port 27017 --dbpath /srv/mongodb/rs0-0 --replSet rs0 --smallfiles --oplogSize 128
mongod --port 27018 --dbpath /srv/mongodb/rs0-1 --replSet rs0 --smallfiles --oplogSize 128
mongod --port 27019 --dbpath /srv/mongodb/rs0-2 --replSet rs0 --smallfiles --oplogSize 128
mongod --port 27020 --dbpath /srv/mongodb/rs0-3 --replSet rs0 --smallfiles --oplogSize 128