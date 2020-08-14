#!/bin/bash

apt-get --yes install nginx
mv ./frontend/nginx-config /etc/nginx/sites-available/frontend-cdn
mv frontend /var/www/frontend
systemctl restart nginx

java -jar ./toolsharing-0.0.1-SNAPSHOT.jar
