/* Simple Counter
* ——————
*
* This is a simple counter that takes a digital input
*
*/
int ledPin = 13; // choose the pin for the LED
int switchPin =2; // choose the input pin (for a pushbutton)
int val = 0; // variable for reading the pin status
int counter = 0;
int currentState = 0;
int previousState = 0;

void setup() {
  pinMode(ledPin, OUTPUT); // declare LED as output
  pinMode(switchPin, INPUT); // declare pushbutton as input
  Serial.begin(9600);
}

void loop(){
  val = digitalRead(switchPin); // read input value
  if (val == HIGH) { // check if the input is HIGH (button released)
      digitalWrite(ledPin, HIGH); // turn LED on
      currentState = 1;
  }
  else {
    digitalWrite(ledPin, LOW); // turn LED off
    currentState = 0; 
  }
  if(currentState != previousState){
      if(currentState == 1){
          counter = counter + 1;
          Serial.println(counter);
      }
  }
  previousState = currentState;
  delay(250);
}

