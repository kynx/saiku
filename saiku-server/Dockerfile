FROM openjdk:8
MAINTAINER Meteorite BI <info@meteorite.bi>


ADD target/saiku-server-foodmart-* /

RUN unzip /saiku-server-foodmart-* && rm /saiku-server-foodmart-*.zip
COPY saiku-beans.xml /saiku-server/tomcat/webapps/saiku/WEB-INF/
COPY docker-entrypoint.sh /
RUN chmod a+x /docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["run"]
