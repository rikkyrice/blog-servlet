#!/bin/sh

set -e
. /docker-entrypoint-initdb.d/db.env

echo "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_type = 'TABLE'" > run.sql

if [ ${PASSWORD} != "" ]; then
  PASSWORD="-password $PASSWORD"
fi

java -cp /h2/bin/h2.jar org.h2.tools.RunScript \
  -url "jdbc:h2:tcp://my-h2-service.default.svc.cluster.local:9092/${DBNAME}" -user ${USERNAME} ${PASSWORD} -script run.sql -showResults

exit 0
