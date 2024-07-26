#include <OneWire.h>
#include <DallasTemperature.h>
#include <SoftwareSerial.h>
#include <Wire.h>

// Data wire is plugged into digital pin 2 on the Arduino
#define ONE_WIRE_BUS 10
#define RE 8
#define DE 7
int analogPin = 0;
int moisture = 0; // variable to store the value read
int temperature = 0;
int soilmoisturepercent = 0;

const int AirValue = 520;   //you need to replace this value with Value_1
const int WaterValue = 200;  //you need to replace this value with Value_2

// Modbus RTU requests for reading NPK values
const byte nitro[] = {0x01,0x03, 0x00, 0x1e, 0x00, 0x01, 0xe4, 0x0c};
const byte phos[] = {0x01,0x03, 0x00, 0x1f, 0x00, 0x01, 0xb5, 0xcc};
const byte pota[] = {0x01,0x03, 0x00, 0x20, 0x00, 0x01, 0x85, 0xc0};

String msg = "";
byte values[11];

SoftwareSerial mod(2, 3);


// Setup a oneWire instance to communicate with any OneWire device
OneWire oneWire(ONE_WIRE_BUS);  

// Pass oneWire reference to DallasTemperature library
DallasTemperature sensors(&oneWire);

void setup(void)
{
  sensors.begin();  // Start up the library
  Serial.begin(9600);
  mod.begin(9600);

  // Define pin modes for RE and DE
  pinMode(RE, OUTPUT);
  pinMode(DE, OUTPUT);
  
  delay(500);
}

void loop(void)
{ 
  byte nitrogen_val,phosphorus_val,potassium_val;
  nitrogen_val = nitrogen();
  delay(250);
  phosphorus_val = phosphorous();
  delay(250);
  potassium_val = potassium();
  delay(250);
  // Send the command to get temperatures
  sensors.requestTemperatures(); 

  //get the temperature value in Celsius
  temperature = sensors.getTempCByIndex(0);
  //get the moisture value
  moisture = analogRead(analogPin); // read the input pin


  soilmoisturepercent = map(moisture, AirValue, WaterValue, 0, 100);
  {
    if (soilmoisturepercent >= 100)
    {
      soilmoisturepercent = 100;
    }
    else if (soilmoisturepercent <= 0)
    {
      soilmoisturepercent = 0;
    }
    else if (soilmoisturepercent > 0 && soilmoisturepercent < 100)
    {
      soilmoisturepercent = soilmoisturepercent;
    }
  }

  
  msg += "&" + String(nitrogen_val);   //N 1
  msg += "&" + String(phosphorus_val);   //P 2
  msg += "&" + String(potassium_val);   //K 3
  msg += "&" + String(sensors.getTempCByIndex(0)); //temperature  4
  msg += "&" + String(soilmoisturepercent);  //humidity  5
  msg += "&6";    //PH  6
  msg += "&72";    //rainfall  7



  Serial.println(msg);
  msg = "";
  delay(1500);
  
}

byte nitrogen(){
  digitalWrite(DE,HIGH);
  digitalWrite(RE,HIGH);
  delay(10);
  if(mod.write(nitro,sizeof(nitro))==8){
    digitalWrite(DE,LOW);
    digitalWrite(RE,LOW);
    for(byte i=0;i<7;i++){
    //Serial.print(mod.read(),HEX);
    values[i] = mod.read();
//    Serial.print(values[i],HEX);
    }
//    Serial.println();
  }
  return values[4];
}
 
byte phosphorous(){
  digitalWrite(DE,HIGH);
  digitalWrite(RE,HIGH);
  delay(10);
  if(mod.write(phos,sizeof(phos))==8){
    digitalWrite(DE,LOW);
    digitalWrite(RE,LOW);
    for(byte i=0;i<7;i++){
    //Serial.print(mod.read(),HEX);
    values[i] = mod.read();
//    Serial.print(values[i],HEX);
    }
//    Serial.println();
  }
  return values[4];
}
 
byte potassium(){
  digitalWrite(DE,HIGH);
  digitalWrite(RE,HIGH);
  delay(10);
  if(mod.write(pota,sizeof(pota))==8){
    digitalWrite(DE,LOW);
    digitalWrite(RE,LOW);
    for(byte i=0;i<7;i++){
    //Serial.print(mod.read(),HEX);
    values[i] = mod.read();
//    Serial.print(values[i],HEX);
    }
//    Serial.println();
  }
  return values[4];
}
