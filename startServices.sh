#!/bin/bash

mkdir /var/log/nginx
mkdir /var/log/mongodb
chmod 777 /var/log/mongodb

systemctl start nginx.service
systemctl status nginx.service

systemctl start mongod.service
systemctl status mongod.service
