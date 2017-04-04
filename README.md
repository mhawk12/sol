# copartTest
Copart internship


First folder License Key has the solution to  <strong>Programming Assignment - License Keys (Simple) (10 points)</strong><br/>
Second folder StringtoInt has the solution to  <strong>Convert String to Integer (Simple) (10 points)</strong><br/>
new folder has solution to Coding Exercises - Problem statements (Node.js or Ruby)(30 points)</br>

<strong><u>For Most Appropriate Facility (Complex) problem:</u></strong>

Used the following APIs

OKHttp, OKio - For HTTP request to make a get request from location api at www.zipcodeapi.com
Gson - For JSON parsing
The jar files for above APIs included in the project folder

Add the jar files to eclipse project as follows:

Import project into eclipse
Project -> Properties -> Java Build Path -> Libraries -> Add external jars
Input files are available in project root dir

We took the Available Copart US location zipcodes from https://www.copart.com/Content/US/EN/Landing-Page/print-locations-by-state?intcmp=web_list_by_state
We are using www.zipcodeapi.com API, which allows only 50 requests per hour. There are more than 150 US locations.
So to compare the input postal code with each one is not possible. So we reduced the copart location list to 7 in the code

//long[] copartLocations = {99501, 36613, 35023, 36116, 35671, 79601, 97218};<br/>
for  problem <strong>Move attached CSV data into DB and fetch lat, Long coordinates for Location column using google map API. </strong>
 In the code, see for mysql connection                        
 remove that password and use some other password                        
  similarly, use another db name and table name


