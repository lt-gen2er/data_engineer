#!/bin/bash

docker exec master spark-submit \
  --master local \
  --executor-memory 1G \
  --total-executor-cores 2 \
  /spark-jobs/wordcount/spark-submit/wordcount_2.12-1.0.jar \
  /spark-jobs/wordcount/spark-submit/wordcount_input.txt \
  /spark-jobs/wordcount/spark-submit/wordcount_output \
  &> log.txt &
