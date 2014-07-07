//========== START PIN DEFINITION ================
//motor X axis
int pinMotorXTakt = 22;
int pinMotorXRichtung = 23;
int pinMotorXEnable = 28;
//motor Y axis
int pinMotorYTakt = 24;
int pinMotorYRichtung = 25;
int pinMotorYEnable = 29;
//motor Z axis
int pinMotorZTakt = 26;
int pinMotorZRichtung = 27;
int pinMotorZEnable = 30;
//limit switches
int pinEndschalterX = 37;
int pinEndschalterY = 36;
int pinEndschalterZ = 35;
//button forward
int pinTasterXpos = 49;
int pinTasterYpos = 47;
int pinTasterZpos = 45;
//button backward
int pinTasterXneg = 48;
int pinTasterYneg = 46;
int pinTasterZneg = 44;
//led outside the box
int pinLedX = 33;
int pinLedY = 32;
int pinLedZ = 31;
//========== END PIN DEFINITION ================

//========== START GLOBAL VARIABLES =========
//variables for timer exceptions
boolean xException;
boolean yException;
boolean zException;
//X,Y,Z position where the crane should go to
int posX = 0;
int posY = 0;
int posZ = 0;

//global position of the modul holder (tablet)
int globX = 0;
int globY = 0;
int globZ = 0;

//counter, because the Arduino is overwhelmed to do so many "writeln" in the period of time in which the exception is checked.
int inc = 0;

//variable for the motor rotation direction
//false == positiv direction in the coordinate system
boolean xRichtung;
boolean yRichtung;
boolean zRichtung;

boolean noInit;
//========== ENDE GLOBAL VARIABLES ==========

//========== START SETUP ROUTINE ======
void setup () {
  noInterrupts();

  pinMode(pinMotorXTakt, OUTPUT);
  pinMode(pinMotorXRichtung, OUTPUT);
  pinMode(pinMotorXEnable, OUTPUT);

  pinMode(pinMotorYTakt, OUTPUT);
  pinMode(pinMotorYRichtung, OUTPUT);
  pinMode(pinMotorYEnable, OUTPUT);

  pinMode(pinMotorZTakt, OUTPUT);
  pinMode(pinMotorZRichtung, OUTPUT);
  pinMode(pinMotorZEnable, OUTPUT);

  //this constantly holds 5V at pin output because the button is soldered to 0V.
  //if button pressed 0 else 1
  pinMode(pinTasterXpos, INPUT_PULLUP);
  pinMode(pinTasterYpos, INPUT_PULLUP);
  pinMode(pinTasterZpos, INPUT_PULLUP);
  pinMode(pinTasterXneg, INPUT_PULLUP);
  pinMode(pinTasterYneg, INPUT_PULLUP);
  pinMode(pinTasterZneg, INPUT_PULLUP);

  //this constantly holds 5V at pin output because the button is soldered to 0V.
  //if button pressed 0 else 1
  pinMode(pinEndschalterX, INPUT_PULLUP);
  pinMode(pinEndschalterY, INPUT_PULLUP);
  pinMode(pinEndschalterZ, INPUT_PULLUP);

  //led is soldered to 5V but on the PIN we need 0V for letting the led burn.
  //turn on led == 0, turn off == 1
  pinMode(pinLedX,OUTPUT);
  pinMode(pinLedY,OUTPUT);
  pinMode(pinLedZ,OUTPUT);

  digitalWrite(pinLedX,1);
  digitalWrite(pinLedY,1);
  digitalWrite(pinLedZ,1);

  //TIMER1 for end switch
  TCCR1A = 0;
  TCCR1B = 0;
  TCNT1  = 0;

  OCR1A = 600;              // compare match register 16MHz/256/2Hz
  TCCR1B |= (1 << WGM12);   // CTC mode
  TCCR1B |= (1 << CS12);    // 256 prescaler
  TIMSK1 |= (1 << OCIE1A);  // enable timer compare interrupt

  //TIMER2 for manual drive 
  TCCR2A = 0;
  TCCR2B = 0;
  TCNT2  = 0;

  OCR2A = 31250;            // 31250 -- compare match register 16MHz/256/2Hz
  TCCR2B |= (1 << WGM22);   // CTC mode
  TCCR2B |= (1 << CS22);    // 256 prescaler 
  TIMSK2 |= (1 << OCIE2A);  // enable timer compare interrupt


  //TIMER3 motor frequency X axis
  TCCR3A = 0;
  TCCR3B = 0;
  TCNT3  = 0;

  OCR3A = 380;              // compare match register 16MHz/256/2Hz war 500 --> 320 noch genau  380
  TCCR3B |= (1 << WGM32);   // CTC mode
  TCCR3B |= (1 << CS32);    // 256 prescaler
  TIMSK3 |= (1 << OCIE3A);  // enable timer compare interrupt

  //TIMER4 motor frequency Y axis
  TCCR4A = 0;
  TCCR4B = 0;
  TCNT4  = 0;

  OCR4A = 225;              // compare match register 16MHz/256/2Hz war 300 --> 150 noch genau
  TCCR4B |= (1 << WGM42);   // CTC mode
  TCCR4B |= (1 << CS42);    // 256 prescaler
  TIMSK4 |= (1 << OCIE4A);  // enable timer compare interrupt

  //TIMER5 motor frequency Z axis
  TCCR5A = 0;
  TCCR5B = 0;
  TCNT5  = 0;

  OCR5A = 100;              // compare match register 16MHz/256/2Hz war 120 --> 50 noch genau
  TCCR5B |= (1 << WGM52);   // CTC mode
  TCCR5B |= (1 << CS52);    // 256 prescaler
  TIMSK5 |= (1 << OCIE5A);  // enable timer compare interrupt

  //initialization of the boolean variables for the end switches
  //Auxiliary variables so that an exception is thrown only once and not in the frequency of the buttons query
  xException = true;
  yException = true;
  zException = true;
  
  noInit = true;

  setPinMotorXEnable(true);
  setPinMotorYEnable(true);
  setPinMotorZEnable(true);

  //USB query
  Serial.setTimeout(10);
  Serial.begin(9600);

  interrupts();             // enable all interrupts
}
//========== ENDE SETUP ROUTINE ======

//========== START INTERRUPT SERVICE ROUTINES ======
//checks whether a limit has been reached.
//is a limit switch is pressed, the led of the affected axis is lit and a exception is thrown.
ISR(TIMER1_COMPA_vect)    //interrupt service routine that wraps a user defined function supplied by attachInterrupt (ALT: TIMER1_OVF_vect)
{
  noInterrupts();

  if(digitalRead(pinEndschalterX) == 0 && xException){
    digitalWrite(pinLedX,0);
    xException = false;
    Serial.print("E_X_");
    Serial.print(xRichtung);
    Serial.println("_");
    posX = 0;
    setPinMotorXRichtung(!xRichtung);
  }
  else if(digitalRead(pinEndschalterX) == 0 && !xException){
    posX = 4;
  }
  else if(digitalRead(pinEndschalterX) == 1 && !xException){
    digitalWrite(pinLedX,1);
    xException = true;
    posX = 50; // 0
    if(xRichtung == false)
    {
      globX = -50; // 0 
    }
  }

  if(digitalRead(pinEndschalterY) == 0 && yException){
    digitalWrite(pinLedY,0);
    yException = false;
    Serial.print("E_Y_");
    Serial.print(yRichtung);
    Serial.println("_");
    posY = 0;
    setPinMotorYRichtung(!yRichtung);
  }
  else if(digitalRead(pinEndschalterY) == 0 && !yException){
    posY = 4;       
  }
  else if(digitalRead(pinEndschalterY) == 1 && !yException){
    digitalWrite(pinLedY,1);
    yException = true;
    posY = 56*5; // 0
    if(yRichtung)
    {
      globY = -56*5; // 0
    }
  }

  if(digitalRead(pinEndschalterZ) == 0 && zException){
    digitalWrite(pinLedZ,0);
    zException = false;
    Serial.print("E_Z_");
    Serial.print(zRichtung);
    Serial.println("_");
    posZ = 0;
    setPinMotorZRichtung(!zRichtung);
  }
  else if(digitalRead(pinEndschalterZ) == 0 && !zException){
    posZ = 10;
  }
  else if(digitalRead(pinEndschalterZ) == 1 && !zException){
    digitalWrite(pinLedZ,1);
    zException = true;
    if(noInit)
    {
      noInit = false;
      posZ = 0;
      globZ = 15000-339; //ropelength
    }else{
      posZ = 0;
    }
  }
  if(inc>=5){
    Serial.print("A_");
    Serial.print(globX);
    Serial.print("_");
    Serial.print(globY);
    Serial.print("_");
    Serial.print(globZ);
    Serial.println("_");
    inc=0;
  }
  inc++;
  interrupts();
}
//manual operation
ISR(TIMER2_COMPA_vect)          // timer compare interrupt service routine
{
  noInterrupts();
  if(digitalRead(pinTasterXpos) == 0 && xException)
  {
    digitalWrite(pinLedX,0);
    posX = 10;
    setPinMotorXRichtung(false);
  }
  else if(digitalRead(pinTasterXneg) == 0 && xException)
  {
    digitalWrite(pinLedX,0);
    posX = 10;
    setPinMotorXRichtung(true);
  }
  else if(digitalRead(pinTasterXpos) == 1 && digitalRead(pinTasterXneg) == 1)
  {
    digitalWrite(pinLedX,1);
    setPinMotorXRichtung(xRichtung);
  }

  if(digitalRead(pinTasterYpos) == 0 && yException)
  {
    digitalWrite(pinLedY,0);
    posY = 10;
    setPinMotorYRichtung(true);
  }
  else if(digitalRead(pinTasterYneg) == 0 && yException)
  {
    digitalWrite(pinLedY,0);
    posY = 10;
    setPinMotorYRichtung(false);
  }
  else if(digitalRead(pinTasterYpos) == 1 && digitalRead(pinTasterYneg) == 1)
  {
    digitalWrite(pinLedY,1);
    setPinMotorYRichtung(yRichtung);
  }

  if(digitalRead(pinTasterZpos) == 0 && zException)
  {
    digitalWrite(pinLedZ,0);
    posZ = 10;
    setPinMotorZRichtung(true);
  }
  else if(digitalRead(pinTasterZneg) == 0 && zException)
  {
    digitalWrite(pinLedZ,0);
    posZ = 10;
    setPinMotorZRichtung(false);
  }
  else if(digitalRead(pinTasterZpos) == 1 && digitalRead(pinTasterZneg) == 1)
  {
    digitalWrite(pinLedZ,1);
    setPinMotorZRichtung(zRichtung);
  }
  interrupts();
}

//motor timer for X axis
ISR(TIMER3_COMPA_vect)        // interrupt service routine that wraps a user defined function supplied by attachInterrupt
{
  noInterrupts();
  if(posX>0){
    digitalWrite(pinMotorXTakt, HIGH);
    digitalWrite(pinMotorXTakt, LOW);
    posX--;
    if(xRichtung)
    {
      globX--;
    }
    else
    {
      globX++;
    }
  }
  interrupts();
}

//motor timer for Y axis
ISR(TIMER4_COMPA_vect)        // interrupt service routine that wraps a user defined function supplied by attachInterrupt
{
  noInterrupts();
  if(posY>0){
    digitalWrite(pinMotorYTakt, HIGH);
    digitalWrite(pinMotorYTakt, LOW);
    posY--;
    if(yRichtung)
    {
      globY++;
    }
    else
    {
      globY--;
    }
  }
  interrupts();
}

//motor timer for Z axis
ISR(TIMER5_COMPA_vect)        // interrupt service routine that wraps a user defined function supplied by attachInterrupt
{
  noInterrupts();
  if(posZ>0){
    digitalWrite(pinMotorZTakt, HIGH);
    digitalWrite(pinMotorZTakt, LOW);
    posZ--;
    if(zRichtung)
    {
      globZ++;
    }
    else
    {
      globZ--;
      if(globZ<0){
        posZ = 1;
        zRichtung = !zRichtung;
      }
    }
  }
  interrupts();
}
//========== END INTERRUPT SERVICE ROUTINES ======

//========== START MAIN LOOP ===============
void loop() {
  while(Serial.available() > 0){

    int X = Serial.parseInt();
    int Y = Serial.parseInt();
    int Z = Serial.parseInt();

    noInterrupts();

    if(X>0){
      setPinMotorXRichtung(false);
    }
    else if(X<0){
      setPinMotorXRichtung(true);
    }

    if(Y>0){
      setPinMotorYRichtung(true);
    }
    else if(Y<0){
      setPinMotorYRichtung(false);
    }

    if(Z>0){
      setPinMotorZRichtung(true);
    }
    else if(Z<0){
      setPinMotorZRichtung(false);
    }

    posX = abs(X);
    posY = abs(Y);
    posZ = abs(Z);

    interrupts();
  }
}
//========== END MAIN LOOP ============

//========== START AUXILIARY FUNCTIONS ======
//abstraction for the direction, because the change of the direction is used often
void setPinMotorXRichtung(boolean a){
  xRichtung = a;
  if(a) {
    digitalWrite(pinMotorXRichtung, HIGH);
  }
  else {
    digitalWrite(pinMotorXRichtung, LOW);
  }
}
void setPinMotorYRichtung(boolean a){
  yRichtung = a;
  if(a) {
    digitalWrite(pinMotorYRichtung, HIGH);
  }
  else {
    digitalWrite(pinMotorYRichtung, LOW);
  }
}
void setPinMotorZRichtung(boolean a){
  zRichtung = a;
  if(a) {
    digitalWrite(pinMotorZRichtung, HIGH);
  }
  else {
    digitalWrite(pinMotorZRichtung, LOW);
  }
}

//enable function set the pin to high or low to control the motors
void setPinMotorZEnable(boolean a){
  if(a) {
    digitalWrite(pinMotorZEnable, HIGH);
  }
  else {
    digitalWrite(pinMotorZEnable, LOW);
  }
}
void setPinMotorYEnable(boolean a){
  if(a) {
    digitalWrite(pinMotorYEnable, HIGH);
  }
  else {
    digitalWrite(pinMotorYEnable, LOW);
  }
}
void setPinMotorXEnable(boolean a){
  if(a) {
    digitalWrite(pinMotorXEnable, HIGH);
  }
  else {
    digitalWrite(pinMotorXEnable, LOW);
  }
}
//========== END AUXILIARY FUNCTIONS ======
