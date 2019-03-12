rm -r /usr/local/tomcat8/webapps/ChoriTest.war
cd /home/choritest/chori
svn update
mvn -Dchori_home=/home/choritest/.configurations package
cp -r /home/choritest/chori/target/Chori.war /usr/local/tomcat8/webapps/ChoriTest.war
service tomcat stop
cp -r /home/choritest/.configurations/application.properties /usr/local/tomcat8/webapps/ChoriTest/WEB-INF/classes/configurations
service tomcat start

############
mysql -uchoritest_db -p drop database choritest_db

mysql -uchoritest_db -p create database choritest_db

mysql -uchoritest_db -p choritest_db < /home/choritest/database/chori4_07112016.sql