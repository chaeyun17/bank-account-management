#!/bin/bash

FILE=/home/ubuntu/deploy/bank_account/*.jar
if [ -f "$FILE" ]; then
    rm $FILE
fi