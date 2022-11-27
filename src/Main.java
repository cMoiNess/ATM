import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Uncommented the two next lines for the CLI version (remember to comment GUI's lines)

        //CLI cli = new CLI();
        //cli.powerCLI();

        // Uncommented the two next lines for the GUI version (remember to comment CLI's lines)

        ATM_GUI GUI = new ATM_GUI();
        GUI.powerGUI();
    }
}