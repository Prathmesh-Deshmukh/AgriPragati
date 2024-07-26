#include <WiFi.h>
#include "ThingSpeak.h" // always include thingspeak header file after other header files and custom macros
#define RXp2 16
#define TXp2 17

char ssid[] = "mywifi";   // your network SSID (name) 
char pass[] = "suyash111";   // your network password
int keyIndex = 0;            // your network key Index number (needed only for WEP)
WiFiClient  client;

// unsigned long myChannelNumber = 2095784;   // old
unsigned long myChannelNumber = 2353739;
// const char * myWriteAPIKey = "FXIU4UXBV7FK53XR";  // old
const char * myWriteAPIKey = "J7YTLOIVLCLH1RQ7";  


// Initialize our values
char * field1; //N
char * field2; //P
char * field3; //K
char * field4; //Temp
char * field5; //Moist
char * field6; //PH
char * field7; //Rainfall
String myStatus = "";

void setup() {
  Serial.begin(115200);  //Initialize serial
  Serial2.begin(9600, SERIAL_8N1, RXp2, TXp2);

  
  WiFi.mode(WIFI_STA);   
  ThingSpeak.begin(client);  // Initialize ThingSpeak
}

void loop() {

  // Connect or reconnect to WiFi
  if(WiFi.status() != WL_CONNECTED){
    Serial.print("Attempting to connect to SSID: ");
    Serial.println("mywifi");
    while(WiFi.status() != WL_CONNECTED){
      WiFi.begin(ssid, pass);  // Connect to WPA/WPA2 network. Change this line if using open or WEP network
      Serial.print(".");
      delay(5000);     
    } 
    Serial.println("\nConnected.");
  }
// break str into values
if(WiFi.status() == WL_CONNECTED){
  String inputString = Serial2.readString();
    char inputChar[inputString.length() + 1];
  inputString.toCharArray(inputChar, inputString.length() + 1);

  const char* delimiter = "&";
  char* part;

  char* parts[10];
  int i = 0;

  part = strtok(inputChar, delimiter);
  while (i < 8) {
    parts[i] = part;
    i++;

    part = strtok(NULL, delimiter);
  }

  
  field1 = parts[1]; //N
  field2 = parts[2]; //P
  field3 = parts[3]; //K
  field4 = parts[4]; //Temp
  field5 = parts[5]; //Moist
  field6 = parts[6]; //PH
  field7 = parts[7]; //Rainfaill

  Serial.println(field1);
  Serial.println(field2);
  Serial.println(field3);
  Serial.println(field4);
  Serial.println(field5);
  Serial.println(field6);
  Serial.println(field7);

  // set the fields with the values
  ThingSpeak.setField(1, field1);
  ThingSpeak.setField(2, field2);
  ThingSpeak.setField(3, field3);
  ThingSpeak.setField(4, field4);
  ThingSpeak.setField(5, field5);
  ThingSpeak.setField(6, field6);
  ThingSpeak.setField(7, field7);

//  // figure out the status message
//  if(number1 > number2){
//    myStatus = String("field1 is greater than field2"); 
//  }
//  else if(number1 < number2){
//    myStatus = String("field1 is less than field2");
//  }
//  else{
//    myStatus = String("field1 equals field2");
//  }
//  
//  // set the status
//  ThingSpeak.setStatus(myStatus);
  
  // write to the ThingSpeak channel
  int x = ThingSpeak.writeFields(myChannelNumber, myWriteAPIKey);
  if(x == 200){
    Serial.println("Channel update successful.");
  }
  else{
    Serial.println("Problem updating channel. HTTP error code " + String(x));
  }
  
  // change the values
//  number1++;
//  if(number1 > 99){
//    number1 = 0;
//  }

  i = 0;
  delay(18000); // Wait 18 seconds to update the channel again
}
}
