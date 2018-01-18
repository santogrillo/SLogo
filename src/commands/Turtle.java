package commands;

import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/**
 * @author Madhavi Rajiv, Ben Welton
 */

public interface Turtle{
     
     public abstract void makeVisible();
     
     public abstract void makeInvisible();
     
     public abstract void turnLeft(double degrees);
     
     public abstract void turnRight(double degrees);
     
     public abstract double setHeading(double degrees);
     
     public abstract void penDown();
     
     public abstract void penUp();
     
     public abstract double goHome();
     
     public abstract double getX();
     
     public abstract double getY();
     
     public abstract double setLoc(double x, double y);
     
     public abstract double setTowards(double x, double y);
     
     public abstract void goForward(double dist);
     
     public abstract void goBack(double dist);
     
     public abstract boolean isPenDown();
     
     public abstract boolean isShowing();
     
     public abstract double getHeading();
     
     public abstract void setPenColor(Color col);
     
     public abstract void setPenSize(double pixels);
     
     public abstract double clearScreen();
     
     public abstract int getID();

     public void setImage(Image image, String imageName, int index);

}

