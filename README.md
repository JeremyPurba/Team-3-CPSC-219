# Team-3-CPSC-219

This is our Othello game for our final project in CPSC 219. 

Othello is a two-player game in which the goal is to have the most pieces of your color on the game board. In order to do this, you need to bound your opponent's pieces from both ends and overturn all the pieces in the middle to your color. 

All the classes for our Othello game for Demo 1 can be found in the "OthelloCompsci219" folder.
In order to play the Othello game on the console, you will need to compile the OthelloConsoleGame and run this file as well. 
A menu is the first thing you should see when running this program. 

All the classes for our Othello game for Demo 2 can be found in the "GUI(In Progress)" folder.
In order to play the GUI version of the othello game, you will need to compile the GameGUI and run this file as well. 
Assure that a jar file for javaFX is within the same folder as this demo's files if required. 

All the classes for our Othello game for Demo 3 can be found in the "GUI(In Progress)" folder as well. This GUI functions in accordance with the logic of the game now. 
In order to play the GUI version of the othello game, you will need to compile the GameGUI class and run this class as well. 
Assure that a jar file for javaFX is within the same folder as this demo's files if required.In order to compile the program in Windows, type: javac -cp.:jrxrt.jar *.java .
If you are compiling on a Linux/Mac type in: javac -cp .:jrxrt.jar *.java .

The fully functional console game is in the "OthelloCompsci219" folder and within the "Demo 1" folder.
The fully functional GUI game is in the "GUI(In Progress)" folder.
We have created a JUnit test for our flipping logic when tokens are placed. This jUnit can be found in the "OthelloCompsci219" folder as well and is called "LogicTest.java". This jUnit tests the OthelloConsoleGame, however, since logic is the same in both the console game and the GUI version, this test can be slightly modified to fit the GUI version as well. When running this test, assure that the two jar files (hamcrest and junit) are saved within the same folder when running it. In order to compile the LogicTest.java type in: avac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar LogicTest.java. In order to subsequently run the program, type in: java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore LogicTest.

Cloning and Installing Instructions:
Click the green button in the top right corner, and download as a zip file. Once downloaded, choose the ppropriate folder and compile and run as mentionned previously. 

References: 
1. https://github.com/mvtran/Java-Othello-Console/tree/master/src/othello
2. https://github.com/graysoncroom/Othello

Group: 3.
TA: Kanishka Singh. 
Tutorial: T01.
