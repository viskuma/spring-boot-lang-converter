@echo off


set JAVA_HOME=C:\Program Files\Java\jdk-17

set MAVEN_HOME=C:\devtools\Maven\apache-maven-3.9.0
set M2_HOME=%MAVEN_HOME%

set PATH=%JAVA_HOME%\bin;%M2_HOME%\bin;%PATH%

echo\
echo JAVA_HOME=%JAVA_HOME%
java -version
echo\
echo MAVEN_HOME=%MAVEN_HOME%
echo\

echo =====================================================
echo Building IDP SP
echo =====================================================
mvn clean install -DskipTests