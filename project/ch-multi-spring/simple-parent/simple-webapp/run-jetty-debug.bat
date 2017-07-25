cd neutrino-bib-webapp
set MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n -Xmx1024m -XX:MaxPermSize=1536m
call mvn clean jetty:run %1