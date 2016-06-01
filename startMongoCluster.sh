#!/bin/bash

screen -d -m mongod --port 27017 --dbpath /srv/mongodb/rs0-0 --replSet rs0 --smallfiles --oplogSize 128
screen -d -m mongod --port 27018 --dbpath /srv/mongodb/rs0-1 --replSet rs0 --smallfiles --oplogSize 128
screen -d -m mongod --port 27019 --dbpath /srv/mongodb/rs0-2 --replSet rs0 --smallfiles --oplogSize 128
screen -d -m mongod --port 27020 --dbpath /srv/mongodb/rs0-3 --replSet rs0 --smallfiles --oplogSize 128

#sleep 10
#mongo --host localhost --port 27017 $(pwd)/mongoCluster.js

echo "Wait for 40 seconds..."
sleep 40
mongo --host localhost --port 27017 $(pwd)/mongoCluster.js
mongo --host localhost --port 27017 --eval "printjson(rs.status())"

mongo --host localhost --port 27018 --eval "db.getMongo().setReadPref('secondaryPreferred')"
mongo --host localhost --port 27019 --eval "db.getMongo().setReadPref('secondaryPreferred')"
mongo --host localhost --port 27020 --eval "db.getMongo().setReadPref('secondaryPreferred')"
