#!/bin/bash

mkdir /var/log/nginx

systemctl start nginx.service
systemctl status nginx.service
