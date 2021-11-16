#!/usr/bin/env bash

aws configure set aws_access_key_id 123
aws configure set aws_secret_access_key 123
aws configure set default.region us-east-1
aws --endpoint-url=http://localhost:4566 s3 mb s3://mybucket