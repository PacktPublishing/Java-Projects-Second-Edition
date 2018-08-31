REM START SNIPPET start_main_cmd
set MODULE_PATH=Main/target/Main-1.0.0-SNAPSHOT.jar;
set MODULE_PATH=%MODULE_PATH%SortInterface/target/SortInterface-1.0.0-SNAPSHOT.jar;
set MODULE_PATH=%MODULE_PATH%quick/target/quick-1.0.0-SNAPSHOT.jar;
set MODULE_PATH=%MODULE_PATH%SortSupportClasses/target/SortSupportClasses-1.0.0-SNAPSHOT.jar
java -p %MODULE_PATH% -m packt.java189fundamentals.Main/packt.java189fundamentals.ch03.main.Main pom.xml
REM END SNIPPET