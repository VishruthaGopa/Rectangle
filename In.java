import java.io.*;

/**
 * <code>In</code> Class. Responsible for getting input from user.
 * 
 * @author Aiden Garrett
 * @author Vishrutha Gopa
 */
class In {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Get a integer value from user
     * 
     * @throws IOException If an input exception occurred
     * @return an integer entered by the user is returned.
     */
    public static int getInt() throws IOException {
        int num = 0;
        do {
            try {
                String numString = br.readLine();
                num = Integer.parseInt(numString);
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Enter an integer. Try again...");
            }
        } while (true);
    }

    /**
     * Get a positive integer value from user
     * 
     * @throws IOException If an input exception occurred
     * @return an integer entered by the user is returned.
     */
    public static int getPositiveInt() throws IOException {
        int num;
        do {
            num = getInt();
            if (num < 0)
                System.out.println("Enter a positive interger...");
        } while (num < 0);

        return num;
    }

}
