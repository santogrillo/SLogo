SLOGO API
===================
#### mr284, sdg12, bw144, ck174
Madhavi, Santo, Ben, Raphael
### External 
#### Front End API
The Back End will have absolutely no access to the Front End. The Front End will delegate all logical command executing decisions to the BackEnd, which doesn't require the Front End's help to do these things.
####  Back End API
The BackEnd API interface will consist of a executeCommand(String s, Display d) which takes in a string entered by the user and figures out whether it should thrown an error due to incorrect syntax or logic, or interpret the command written in the string and perform actions based on the command. The Display object lets the backend know which display to edit. This method also returns a string which the front end will print on the GUI console. The interface will also consist of getVariableMap() and getUDMethodMap() which returns copies of its own Maps that give the front end access to data about variables and user defined methods so that users can view all the variables and user defined methods in their workspace. The BackEnd API also has a setLanguage(String l) so as to know which language of commands to interpret.z
### Internal
####  Front End Internal
The Front End internal interface consists of a method called render() which draws everything and structures and organizes the GUI. It also has method called addMenus(), which takes care of all the different menus. It also has a method called saveState(). Finally it has a method called findErrors(). 
#### Back End Internal
This API contains the following methods in the display: 

protected void moveTurtle(Point2D translate) which moves the turtle.

protected void turnRight(double translate) rotates the turtle translate degrees to the right

protected void turnLeft(double translate)  same as turnRight(), but rotates to the left

protected void turnTowards(Point2D target) rotates the turtle towards the specified point

protected double getHeading() which returns the current heading of the turtle.

protected Point2d getTurtleLocation() which returns the x,y location of the turtle

protected double setAbsoluteLocation(Point p) which sets the location of the turtle to a specified point. Also called when resetting the turtle to the home (0,0) location

protected void setPen(boolean up) sets the pen up/down. Up = true, Down = false

protected void setTurtleVisibility(boolean visible) sets the visibility of the turtle. Visible = true, Not Visible = false.

protected boolean getPen() returns the current state of the pet Up = true, down = false

protected boolean getTurtleVisibility() returns the current visibility state. Visible = true, Invis = false

protected void clear() resets the turtle to the home location and removes any drawn lines
