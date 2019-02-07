#!/usr/bin/env bash
set -eo pipefail

if [[ "$1" = "run" || "$1" = "debug" ]] ; then
    set -- ./catalina.sh "$@"
    . /saiku-server/set-java.sh
    setJava
    JAVA_HOME=$_JAVA_HOME
    export CATALINA_OPTS="-Xms256m -Xmx768m -XX:MaxPermSize=256m -Dfile.encoding=UTF-8 -Dorg.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true -Djava.awt.headless=true"
    cd /saiku-server/tomcat/bin
fi

exec "$@"
