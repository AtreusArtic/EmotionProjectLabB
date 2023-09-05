
## Folder Structure
The workspace contains four folders by default, where:

- `src`: the folder to maintain java classes
- `data`: the folder to maintain data file resources: songs and server data.
- `bin` : the folder to maintain java file.class, relative to scr folder content.
- `doc`: the folder where user manual and technical manual are saved, and also the javadoc folder.
- `DatabaseConfig`: the folder team configurations where saved, in order to connect with the database, and also there is the .sql file backup, to load the emotion database project. 



## Notes!!!
-  To load the database, you need to import the .sql file in the DatabaseConfig folder, in your dbms, and change the configurations in the DatabaseConfig folder, in the file named "profdbconfig.properties", and change the reference to the property in the class ServerImpl.java


## How start application:
    #Server:
    - Open shell/cmd: select the path where progect folder is located;
    - Digit command: [ java -jar Server.jar ]
    if a permission error occur, please digit this command: [ java --enable-preview -jar Server.jar ]

    #Client:
    - Open out/artifacts/Client_exe folder, and double click on Client.exe file.

    - To test application there is also a registered account: 
        • username: Artiiic98
        • password: enrico98
