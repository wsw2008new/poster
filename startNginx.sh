#!/bin/bash

pkill nginx
rm -rf /var/log/nginx && mkdir /var/log/nginx
rm -f /etc/nginx/conf.d/poster.conf
cp ./nginx-config /etc/nginx/conf.d/poster.conf

systemctl start nginx.service
systemctl status nginx.service
