
//Team Members
//1. Vishrutha Gopa
//2. Aiden Garrett
//Date Started: May 21st, 2021
//Date Completed: May 27th, 2021
//Brief Description: Create a rectangle class that allows users to create rectangle objects and calculate properties such as area, perimeter, point of intersection bewteen 2 rectangles, and properties of an intersecting rectangle formed by two overlapping rectangles. 

import processing.core.PApplet;
import java.io.*;

/**
 * Main Class
 * 
 * @author Aiden Garrett
 * @author Vishrutha Gopa
 */
class Main {

    /**
     * <code>Main</code> Method to execute the program. Allows user to enter
     * {@link Rectangle}s and outputs the two rectangles bottom left x and y
     * coordinates, area, perimeter, and the data of the {@link Rectangle} formed
     * from the two rectangles if it exists.
     * 
     * @param args argument list passed to main method
     */
    public static void main(String[] args) {

        int xcoordinate_rect1 = 0, ycoordinate_rect1 = 0;
        int width_rect1 = 0, height_rect1 = 0;

        int xcoordinate_rect2 = 0, ycoordinate_rect2 = 0;
        int width_rect2 = 0, height_rect2 = 0;

        int perimeter;

        FileWriter fw = null;
        Rectangle r1 = null;
        Rectangle r2 = null;

        try {
            FileWriter f = new FileWriter("AppletFile.txt");

            System.out.println("Let's get our OOP on folks!");

            System.out.println("Enter the x value of the bottom left coordinates of rectangle A:");
            xcoordinate_rect1 = In.getInt();
            f.write(xcoordinate_rect1 + "\n");

            System.out.println("Enter the y value of the bottom left coordinates of rectangle A:");
            ycoordinate_rect1 = In.getInt();
            f.write(ycoordinate_rect1 + "\n");

            System.out.println("Enter the width of the rectangle A");
            width_rect1 = In.getPositiveInt();
            f.write(width_rect1 + "\n");

            System.out.println("Enter the height of the rectangle A");
            height_rect1 = In.getPositiveInt();
            f.write(height_rect1 + "\n");

            System.out.println("Enter the x value of the bottom left coordinates of rectangle B:");
            xcoordinate_rect2 = In.getInt();
            f.write(xcoordinate_rect2 + "\n");

            System.out.println("Enter the y value of the bottom left coordinates of rectangle B:");
            ycoordinate_rect2 = In.getInt();
            f.write(ycoordinate_rect2 + "\n");

            System.out.println("Enter the width of the rectangle B");
            width_rect2 = In.getPositiveInt();
            f.write(width_rect2 + "\n");

            System.out.println("Enter the height of the rectangle B");
            height_rect2 = In.getPositiveInt();
            f.write(height_rect2 + "\n");
            f.close();
        } catch (Exception InputMismatchException) {
            System.out.println("Enter Integer Values only: ");
        }

        boolean areEqual = ((xcoordinate_rect1 == xcoordinate_rect2) && (ycoordinate_rect1 == ycoordinate_rect2)
                && (width_rect1 == width_rect2) && (height_rect1 == height_rect2));

        if (areEqual) {
            r1 = new Rectangle();
            r1.set(xcoordinate_rect1, ycoordinate_rect1, width_rect1, height_rect1);
            r2 = new Rectangle(r1);
        } else {
            r1 = new Rectangle();
            r2 = new Rectangle();

            r1.set(xcoordinate_rect1, ycoordinate_rect1, width_rect1, height_rect1);
            r2.set(xcoordinate_rect2, ycoordinate_rect2, width_rect2, height_rect2);

        }

        Rectangle.writeToFile(r1, r2);

        perimeter = Rectangle.totalPerimeter(r1, r2);

        try {
            fw = new FileWriter("Rectangles.txt", true);
            fw.write("\nTotal Perimeter for Both:" + "\t" + perimeter + " px");
            fw.close();

        } catch (IOException e) {
            System.out.println("An error occured.");
        }

        Rectangle.viewFile();

        PApplet.main("Sketch");
    }

}