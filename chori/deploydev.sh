mvn -Dchori_home=/home/choritest/.configurations package
 
rm -rf /usr/local/tomcat8/webapps/ChoriTest.war
 
cp -rf /home/choritest/chori/target/Chori.war /usr/local/tomcat8/webapps/ChoriTest.war

cp -rf /home/choritest/.configurations/application.properties /usr/local/tomcat8/webapps/ChoriTest/WEB-INF/classes/configurations