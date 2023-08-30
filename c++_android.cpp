
#include <WiFi.h>
#include <WiFiClient.h>
 


const char* ssid = "your ssid";
const char* password = "your password";
const int ledPin = 18;

IPAddress local_IP(192, 168, 1, 12); 
IPAddress subnet(255, 255, 255, 0);
IPAddress gateway(192, 168, 1, 1);
IPAddress primaryDNS(192, 168, 1, 1); 
IPAddress secondaryDNS(0, 0, 0, 0); 

WiFiServer server(80);
 
void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);

 if (!WiFi.config(local_IP, gateway, subnet, primaryDNS, secondaryDNS))
    {
      Serial.println("Configuration Failed!");
    }
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }
  Serial.println("Connected to Wifi \n");
  Serial.println(WiFi.localIP());
  
  pinMode(ledPin, OUTPUT);
  server.begin();
}
 
void loop() {
  WiFiClient client = server.available();
  if (client) {
    Serial.println("Client connected.");
    
    while (client.connected()) {
      if (client.available()) {
        String request = client.readStringUntil('\r');
        request.trim();
        Serial.println("Received: " + request); 
        if(request == "ON"){
          digitalWrite(ledPin, HIGH);
        }else{
          digitalWrite(ledPin, LOW);

        }
        
 
       
       
      }
      

    }
    Serial.println("Client disconnected.");
  }
}
