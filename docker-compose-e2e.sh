#!/bin/bash
export ARCH=$(uname -m)
# Удалит все контейнеры
docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q)
docker pull twilio/selenoid:chrome_stable_145
docker compose up -d
docker ps -a
