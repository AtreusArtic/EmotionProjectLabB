# EmotionProjectLabB

# NOTE:
1. After you fetch the project, check if maven project it setted correcty, simply by start run the 'appTest.java', if the execution return 0, maven is ok.
2. Check in Project Window, if in the "External Libraries", there is the postgresql-42.2.5.jar, if not, add it manually.
3. pw for access the database: marco2000 (temporally)

# LINKS:
1. DOWNLOAD POSTGREE : https://www.postgresql.org/download/
2. DOWNLOAD INTELLIJ : https://www.jetbrains.com/idea/download/#section=windows
3. DOWNLOAD MAVEN (Facoltativo) : https://maven.apache.org/download.cgi

# HOW TO CHECK IF MAVEN IS CORRECTLY INSTALLED:
1. Open the project in IntelliJ;
2. Open the file 'appTest.java';
3. Run the file 'appTest.java';
4. If the execution return 0, maven is correctly installed.

# START SERVER AND CLIENT:
1. Start the server, and only after that, start the client.
2. If the Client is in the same machine of the server, you can start the client without any problem, but pay attention to use the correct IP address. Read the JavaDoc of the class 'ClientMain.java' for more information.
3. If the Client is in a different machine of the server, you have to change the IP address of the server in the class 'ClientMain.java' with the IP address declared in the class. Read the JavaDoc of the class 'ClientMain.java' for more information.

