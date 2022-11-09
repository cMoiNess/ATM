import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileGestion {

    public FileGestion() {}

    public void createFileWithVerif(String s){
        File myObj=new File(s);
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " +
                        myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printFileInfos(String s){
        File myObj=new File(s);
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " +
                    myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " +
                    myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }

    public void writeToFile(String fileName, String content) {
        File myObj=new File(fileName);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Write was Successfull");
        } catch (IOException e) {
            System.out.println("Something has happened not previewed");
            e.printStackTrace();
        }
    }

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




}
