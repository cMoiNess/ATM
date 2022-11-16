import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileGestion implements Serializable {

    // ---- Constructor
    public FileGestion() {}

    // ---- Methods

    // ---- Method that creates a file

    // Method to create a file if it does not exist
    public void createFileWithVerif(String s){
        File myObj=new File(s);
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // ---- Methods that write to a file

    // Method that allows to write in a file without line break
    public void writeToFile(String fileName, String content) {
        File myObj=new File(fileName);
        try {
            FileWriter myWriter = new FileWriter(fileName, true); // The addition of true allows not to overwrite the previous text
            myWriter.write(content); // Written in the file
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Something has happened not previewed");
            e.printStackTrace();
        }
    }

    // Method that allows to write in a file with line break
    public void writeToFileLineBreak(String fileName, String content) {
        File myObj=new File(fileName);
        try {
            // FileWriter myWriter = new FileWriter(fileName);
            FileWriter myWriter = new FileWriter(fileName, true);
            myWriter.write(content + "\n");
            myWriter.close();
            // System.out.println("Write was Successfull");
        } catch (IOException e) {
            System.out.println("Something has happened not previewed");
            e.printStackTrace();
        }
    }

    // Method to write a user to the backup file
    public void writeToFileAnObject(String fileName, User objectName) {
        String outputText = objectName.getLogin() + "|" + objectName.getPin() + "|" + objectName.getBalance();
        writeToFileLineBreak(fileName, outputText); // Write the user to the backup file
    }

    // Method to write an admin user to the backup file
    public void writeToFileAnObjectAdmin(String fileName, Admin objectName) {
        String outputText = objectName.getLogin() + "|" + objectName.getPin();
        writeToFileLineBreak(fileName, outputText); // Write the user to the backup file
    }



    // Method to write users to the backup file
    public void writeToFileObjects(String fileName, ArrayList<User> listUser) throws FileNotFoundException {
        eraseToFile(fileName);
        for (int i = 0; i < listUser.size(); i++) {
            User userIndex = listUser.get(i);
            String outpuText = userIndex.getLogin() + "|" + userIndex.getPin() + "|" + userIndex.getBalance();
            writeToFileLineBreak(fileName, outpuText); // Write the user i to the backup file
        }
    }

    // Method to write admin users to the backup file
    public void writeToFileObjectsAdmin(String fileName, ArrayList<Admin> listAdmin) throws FileNotFoundException {
        eraseToFile(fileName);
        for (int i = 0; i < listAdmin.size(); i++) {
            Admin userIndex = listAdmin.get(i);
            String outpuText = userIndex.getLogin() + "|" + userIndex.getPin();
            writeToFileLineBreak(fileName, outpuText); // Write the admin i to the backup file
        }
    }

    // ---- Methods that read from a file

    // Method to display the content of a file
    public void readFromFile(String fileName){
        File myObj=new File(fileName);
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Method that allows to read the content of a file and that allows to store the users in an ArrayList
    public ArrayList<User> readUsersFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        ArrayList<User> userList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] items = line.split("\\|");

            String login = items[0];
            int pin = Integer.parseInt(items[1]);
            Double balance = Double.parseDouble(items[2]);

            User newUser = new User(login, pin, balance);

            userList.add(newUser);

        }
        return userList;
    }

    public ArrayList<Admin> readAdminsFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        ArrayList<Admin> adminList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] items = line.split("\\|");

            String login = items[0];
            int pin = Integer.parseInt(items[1]);

            Admin newAdmin = new Admin(login, pin);

            adminList.add(newAdmin);

        }
        return adminList;
    }

    // ---- Methods that delete in a file

    // Method to delete the contents of a file
    public void eraseToFile (String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }



    // Method that allows you to delete the contents of a file and then to write in this file with line break
    public void EraseAndWriteToFileLineBreak(String fileName, String content) {
        File myObj=new File(fileName);
        try {
            // FileWriter myWriter = new FileWriter(fileName);
            FileWriter myWriter = new FileWriter(fileName, false);
            myWriter.write(content + "\n");
            myWriter.close();
            // System.out.println("Write was Successfull");
        } catch (IOException e) {
            System.out.println("Something has happened not previewed");
            e.printStackTrace();
        }
    }

    public ArrayList<User> eraseToFileAnObject(String fileName, ArrayList<User> listUser, String deleteUser) throws FileNotFoundException {
        eraseToFile(fileName);
        ArrayList<User> returnListUser = new ArrayList<User>();
        for (int i = 0; i < listUser.size(); i++) {
            User userIndex = listUser.get(i);
            if (!userIndex.getLogin().equals(deleteUser)) {
                returnListUser.add(listUser.get(i));
                String outpuText = userIndex.getLogin() + "|" + userIndex.getPin() + "|" + userIndex.getBalance();
                writeToFileLineBreak(fileName, outpuText); // Write the user i to the backup file
            }
        }
        return returnListUser;
    }



    // ---- Methods that check in a file

    // Method to authenticate a user
    public boolean verifAccountLoginAndPassword(String fileName, String login, int pin) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] items = line.split("\\|");

            String loginItem = items[0];
            int pinItem = Integer.parseInt(items[1]);

            if (login.equals(loginItem) && pin == pinItem) {
                return true;
            }
        }
        return false;
    }

    // Method to check if an account exists
    public boolean verifAccountExist(String fileName, String login) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] items = line.split("\\|");

            String loginItem = items[0];

            if (login.equals(loginItem)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if a file exists
    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    // Method that returns the index of the desired account in a file
    public int findIndexAccount(String fileName, String login) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int nbIndex = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] items = line.split("\\|");

            String loginItem = items[0];

            if (login.equals(loginItem)) {
                return nbIndex++;
            }
            nbIndex++;
        }
        throw new RuntimeException("Le compte n'existe pas !");
    }

    // Method that returns the list of all users
    public void printUsersForAdmin(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] items = line.split("\\|");

            String login = items[0];

            System.out.println(login);

        }
    }


}
