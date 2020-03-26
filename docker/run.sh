#!/bin/bash

cd /deepPhosAPI
. env/bin/activate
python3 predict.py &

cd /pinet-server
java -jar build/libs/*.jar
