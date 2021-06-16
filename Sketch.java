import processing.core.PApplet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Use processing to have your program become a visualized program. Have two rectangles start at opposite sides of the screen and move to their final resting places.

/**
 * Create a GUI for the {@link Rectangle}s from a text file.
 * 
 * @authors Aiden Garrett
 * @authors Vishrutha Gopa
 */
public class Sketch extends PApplet {
    float speed = 1;
    float x1 = 400;
    float y1 = 400;
    float x2 = 0;
    float y2 = 0;

    int[] s = new int[12];

    /**
     * Read from the <code>AppletFile.txt</code> and store values in an array
     * 
     * @throws IOException incase cannot read from file.
     */
    public void readFile() throws IOException {

        int i = 0;
        try {
            File file = new File("AppletFile.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                s[i] = Integer.parseInt(st);
                i++;
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Create the size of the GUI
     */
    public void settings() {
        size(400, 400);
    }

    /**
     * Intilize <code>s</code> array and set background
     */
    public void setup() {
        // background(255); //Grayscale
        background(116, 109, 213);
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        x2 -= this.s[6];
        y2 -= this.s[7];
    }

    /**
     * Output to the GUI
     */
    public void draw() { // Occurs ONCE every frame
        boolean rectanglesTouch = (x1 < (x2 + this.s[6]) && (x1 + speed) < x2 + this.s[6] && (y1 - this.s[3]) < y2
                && y1 + speed < (y2 + this.s[7]));
        // Set the background to black and turn off the fill color
        background(0);
        noFill();

        stroke(255);

        if (x1 >= this.s[0])
            x1 -= speed;
        if (y1 >= this.s[1])
            y1 -= speed;

        rect(x1, y1, (this.s[2]), (this.s[3]));

        if (x2 <= this.s[4])
            x2 += speed;
        if (y2 <= this.s[5])
            y2 += speed;

        rect(x2, y2, (this.s[6]), (this.s[7]));

        if ((x1 + speed) == this.s[0] && (x2 - speed) == this.s[4]) {
            fill(253, 248, 246);
            rect(this.s[8], this.s[9], (this.s[10]), (this.s[11]));
        }

        if (rectanglesTouch) {
            frameRate(10);
            fill(random(255), random(255), random(255));
            rect(x2, y2, (this.s[6]), (this.s[7]));
            fill(random(255), random(255), random(255));
            rect(x1, y1, (this.s[2]), (this.s[3]));
        }
    }

}
