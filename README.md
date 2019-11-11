# Rabobank Customer Statement Processor
Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. 
These records need to be validated.

## Input
The format of the file is a simplified format of the MT940 format. The format is as follows:

Transaction reference  - A numeric value
Account number  -  An IBAN 
Account - IBAN 
Start Balance - The starting balance in Euros 
Mutation - Either and addition (+) or a deducation (-) 
Description - Free text 
End Balance - The end balance in Euros 

Monthly statements to be processed are kept under "src/main/resources" directory.

## Output
There are two validations:
1. All transaction references should be unique
2. The end balance needs to be validated

At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.

## Steps to run the application:

1.	Clone the application RabobankApplication from GitHub

     In prompt window, enter the following command
           git clone https://github.com/navin2jms001/RaboRankApplication.git
		   
    If GIT is not installed, download the zip and unzip to local computer.
	

2.	Navigate to the folder where we saved the project.

3.	Enter the following command to run the project

            gradlew bootrun
     
4.	Wait until we get the log, "started RabobankApplication" for the application to get started.

5.  Application works as below.
    
      1. If we want to run and see the results based on XML file, use below URL
      http://localhost:8080/rabobank/processStatement/XML
      
      2. If we want to run and see the results based on CSV file, use below URL
      http://localhost:8080/rabobank/processStatement/CSV
      
      3. If we want to run and see the results based on both files, use below URL
      http://localhost:8080/rabobank/processStatement
      
      4. If we want to get the report based on our custom inputs, we can use postman with below URL
      http://localhost:8080/rabobankservices/processStatement/
      
      Request Method: POST
      Under Body Tab, select "form-data" and upload the file with key as 'file' and value as actual file.
      
      Note: With already given inputs, we will get the report in tabular format.
            With custom input , we will get the report in JSON format.
      
     

## Steps to test the application:

     Enter the following command
	 
        gradlew clean test

    Test results can be viewed in html format from below path,
    
     build\reports\tests\test\index.html 
	 

Note: For more detailed working of the application, refer Rabobank-RunBook.

