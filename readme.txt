I made assumption that account can have negative state.
Limit of amount parameter is 10 digits.
If user inputs invalid message, applications just says about wrong format, it's not critical situation and application
can continue working well.
If some line in file has wrong format, the application continues working. Message about wrong format will be printed in console.




How to run:

type into the command line next commands:

   mvn clean package
   java -jar target/bsc-test-1.0-SNAPSHOT.jar <filename>

filename is optional parameter, you can specify file with initial data like described in the document.