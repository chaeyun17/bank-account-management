#!/bin/bash

SOURCE_DIR=/home/ubuntu/source/bank_account
DEPLOY_DIR=/home/ubuntu/deploy/bank_account

bash $SOURCE_DIR/gradlew bootJar

cp $SOURCE_DIR/build/libs/*.jar $DEPLOY_DIR/