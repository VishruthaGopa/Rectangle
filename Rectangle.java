import java.io.*;

/**
 * <code>Rectangle</code> Class. Responsible for creating Rectangle objects.
 *
 * @authors Aiden Garrett
 * @authors Vishrutha Gopa
 */
class Rectangle {
    private int left;
    private int bottom;
    private int width;
    private int height;

    // constructors

    /**
     * Constructor to instanciate the {@link Rectangle}'s values to zero given no
     * parameters
     **/
    public Rectangle() {
        this.set(0, 0, 0, 0);
    }

    /**
     * Second constructor to instanciate the {@link Rectangle}'s values to the
     * appropiate parameter
     * 
     * @param left   The x value for the bottom left corrner of the
     *               {@link Rectangle} (Integer)
     * @param bottom The y value for the bottom left corrner of the
     *               {@link Rectangle} (Integer)
     * @param width  an integer for the width of the {@link Rectangle}. Distance
     *               from leftmost point of {@link Rectangle} * to right point of
     *               {@link Rectangle}.
     * @param height an integer for the height of the {@link Rectangle}. Distance
     *               from bottom of {@link Rectangle} to top of {@link Rectangle}
     **/
    public Rectangle(int left, int bottom, int width, int height) {
        this.set(left, bottom, width, height);
    }

    /**
     * Third constructor for the {@link Rectangle} given a {@link Rectangle} as a
     * parameter. Sets the new rectangles parameter to be equal to the
     * {@link Rectangle} f, which is passed as a parameter.
     * 
     * @param f a {@link Rectangle} object to be duplicated
     **/
    public Rectangle(Rectangle f) {
        this.set(f.left, f.bottom, f.width, f.height);
    }

    // Mutator methods

    /**
     * A mutator method to instanciate a new {@link Rectangle} object given bottom
     * left x value, bottom left y value, width and height. Makes sure the height
     * and width are both positive. If negative, the variable is set to zero.
     * 
     * @param left   an integer value for the bottom left corner of the
     *               {@link Rectangle}'s x coordinate
     * @param bottom an integer value for the bottom left corner of the
     *               {@link Rectangle}'s y coordinate
     * @param width  an integer for the width of the {@link Rectangle}. Distance
     *               from leftmost point of {@link Rectangle} to right point of
     *               {@link Rectangle}.
     * @param height an integer for the height of the {@link Rectangle}. Distance
     *               from bottom of {@link Rectangle} to top of {@link Rectangle}
     */
    public void set(int left, int bottom, int width, int height) {
        this.left = left;
        this.bottom = bottom;
        this.width = width;
        this.height = height;

        if (this.height < 0 && this.width < 0) {
            this.height = 0;
            this.width = 0;
        } else if (this.width < 0) {
            this.width = 0;
        } else if (this.height < 0) {
            this.height = 0;
        }

    }

    // Instance methods

    /**
     * Instance method to output {@link Rectangle}'s values in a nicely formatted
     * String. The values output include the bottom left x and y coordinate, the
     * width, and height.
     *
     * @return a String with the {@link Rectangle}'s the bottom left x and y
     *         coordinate, the width, and height formated into a single String
     */
    public String toString() {
        return String.format("base: (%d,%d) w: %d h: %d", left, bottom, width, height);
    }

    /**
     * Instance method to calculate the area of a {@link Rectangle}.
     *
     * @return the area of the {@link Rectangle} is returned as an integer value.
     */
    public int area() {
        return this.height * this.width; // calculate and return area of rectangle
    }

    /**
     * Instance method to calculate the perimeter of the {@link Rectangle}.
     *
     * @return the perimeter of the {@link Rectangle} is returned as an integer. For
     *         a straight line or a point, the perimeter is the line’s length.
     * 
     */
    public int perimeter() {

        // calculate and return the perimeter of line/point or rectangle
        return (height == 0 || width == 0) ? width + height : 2 * (this.width + this.height);
    }

    /**
     * Instance method to check if the explicit parameter {@link Rectangle} is
     * within the implict parameter.
     *
     * @param otherRectangle the {@link Rectangle} object to check if within the
     *                       implict {@link Rectangle}.
     * @return true if the explicit {@link Rectangle} parameter is within the
     *         implict {@link Rectangle} parameter.
     */
    public boolean contains(Rectangle otherRectangle) {
        int aRight, aTop;
        int bRight, bTop;

        aRight = this.left + this.width;
        aTop = this.bottom + this.height;

        bRight = otherRectangle.left + otherRectangle.width;
        bTop = otherRectangle.bottom + otherRectangle.height;

        return (otherRectangle.left >= this.left && otherRectangle.bottom >= this.bottom && bRight <= aRight
                && bTop <= aTop); // check if rectangle is fully contained within other rectangle
    }

    // Class methods
    /**
     * Class method to check if two {@link Rectangle}s intersect and return the
     * {@link Rectangle} formed by the overlapping area of the two
     * {@link Rectangle}s.
     *
     * @param a the first {@link Rectangle} parameter to check if intersects with
     *          {@link Rectangle} <code>b</code>
     * @param b the second {@link Rectangle} parameter to check if interesects with
     *          {@link Rectangle} <code>a</code>
     *
     * @return a {@link Rectangle} object that is the {@link Rectangle} formed by
     *         the overlapping area of the two {@link Rectangle} parameters. If no
     *         intersection betweeen the {@link Rectangle} parameters, a
     *         {@link Rectangle} is returned with zeros as all parameters. If
     *         rectangles touch without overlapping, the length or width is set to
     *         zero and the rest of the values are calculated.
     */
    public static Rectangle intersection(Rectangle a, Rectangle b) {
        int aRight = 0, aTop = 0;
        int bRight = 0, bTop = 0;
        int rtSect = 0, ltSect = 0, topSect = 0, botSect = 0;
        int area = 0;
        int widthSect = 0, heightSect = 0;

        Rectangle obj = null;
        FileWriter f = null;

        aRight = a.left + a.width;
        aTop = a.bottom + a.height;

        bRight = b.left + b.width;
        bTop = b.bottom + b.height;

        boolean rectanglesNotIntersect = (a.left > bRight || b.left > aRight || b.bottom > aTop || a.bottom > bTop);

        try {
            f = new FileWriter("Rectangles.txt", true);
            if (rectanglesNotIntersect) {
                // System.out.println("The rectangle does not intersect.");
                f.write("The rectangle does not intersect.\n");
                f.close();
                return new Rectangle();

            } else if (a.contains(b)) {

                obj = new Rectangle(b);
                // System.out.println("Intersection Rectangle of Rect. A & Rect. B: " + obj);
                f.write("Intersection Rectangle of Rect. A & Rect. B: " + obj + "\n");
                f.close();
                return obj;
            } else if (b.contains(a)) {

                obj = new Rectangle(a);
                // System.out.println("Intersection Rectangle of Rect. A & Rect. B: " + obj);
                f.write("Intersection Rectangle of Rect. A & Rect. B: " + obj + "\n");
                f.close();
                return obj;

            } else {
                ltSect = Math.max(a.left, b.left);
                botSect = Math.max(a.bottom, b.bottom);
                rtSect = Math.min(aRight, bRight);
                topSect = Math.min(aTop, bTop);

                widthSect = rtSect - ltSect;
                heightSect = topSect - botSect;

                obj = new Rectangle(ltSect, botSect, widthSect, heightSect);
                area = obj.area();

                // System.out.println("Intersection Rectangle of Rect. A & Rect. B: " + obj);
                f.write("Intersection Rectangle of Rect. A & Rect. B: " + obj + "\n");

                f.close();
                return obj;
            }
        } catch (Exception e) {
            System.out.println("Could not write into the file.");
            return obj;

        }
    }

    /**
     * Calculates the total perimeter formed by the two {@link Rectangle}
     * parameters.
     *
     * @param a one of the {@link Rectangle} objects to calculate the perimeter of
     * @param b one of the {@link Rectangle} objects to calculate the perimeter of
     * @return the perimeter of the shape formed by {@link Rectangle} a and b
     */
    public static int totalPerimeter(Rectangle a, Rectangle b) {

        int perimeterA = a.perimeter();
        int perimeterB = b.perimeter();

        boolean aOrBIsLine = (a.width == 0 || a.height == 0) || (b.width == 0 || b.height == 0);

        // calling intersection function
        Rectangle c = intersection(a, b);

        if (a.contains(b))
            return a.perimeter();
        else if (b.contains(a))
            return b.perimeter();

        try {
            FileWriter fr = new FileWriter("AppletFile.txt", true);
            writeObjToFile(c);

            if (c.height == 0 && c.width == 0) {
                return perimeterA + perimeterB;

            } else if ((c.height == 0 || c.width == 0) && aOrBIsLine) {
                return perimeterA + perimeterB - ((c.height + c.width));
            } else {

                return perimeterA + perimeterB - (2 * (c.height + c.width));
            }
        } catch (Exception e) {
            System.out.println("Error while writing into file");
            return 0;
        }

    }

    /**
     * Write the output of the {@link Rectangle}s properties to the Rectangles.txt
     * file.
     * 
     * @param r1 the first {@link Rectangle}
     * @param r2 the second {@link Rectangle}
     */
    public static void writeToFile(Rectangle r1, Rectangle r2) {
        try {
            FileWriter f = new FileWriter("Rectangles.txt");
            f.write("Rectangle Class:\n\n");
            writeRectToFile('A', r1, f);
            writeRectToFile('B', r2, f);
            f.close();
        } catch (IOException err) {
            System.out.println("Error occured.");
        }
    }

    /**
     * Writes the contents of a single rectangle to file. This includes the x and y
     * coordinate of the bottom left point, width, height, perimeter, and area.
     *
     * @param letter the letter/ single character name of the {@link Rectangle}
     *               being passed.
     * @param rect   the {@link Rectangle} thats contents will be output.
     * @param f      The FileWriter that will be used to write the contents to file.
     * @throws IOException If an output exception occurred
     */
    private static void writeRectToFile(char letter, Rectangle rect, FileWriter f) throws IOException {

        f.write("X coordinate of Rectangle " + letter + ": " + rect.left + "\t\t" + "y coordinate of Rectangle "
                + letter + ": " + rect.bottom + "\n\n");
        f.write("Width " + letter + ": " + rect.width + "\t\t" + "Height " + letter + ": " + rect.height + "\n\n");
        f.write("Area " + letter + ": " + rect.area() + " px^2\t\t" + "Perimeter " + letter + ": " + rect.perimeter()
                + " px\n\n");
    }

    /**
     * Reads and outputs the content from the text file to the console.
     *
     */
    public static void viewFile() {
        try {
            FileReader fr = new FileReader("Rectangles.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();

        } catch (IOException err) {
            System.out.println("Err reading from file");
        }
    }

    /**
     * Writes the contents of the intersecting rectangle to text file. This includes
     * the * x and y coordinate of the bottom left point, width, height.
     *
     * @throws IOException If an exception occurred
     * @param a the {@link Rectangle} thats contents will be output.
     */
    public static void writeObjToFile(Rectangle a) throws IOException {

        FileWriter f = new FileWriter("AppletFile.txt", true);
        f.write(a.left + "\n");
        f.write(a.bottom + "\n");
        f.write(a.width + "\n");
        f.write(a.height + "\n");
        f.close();
    }
}