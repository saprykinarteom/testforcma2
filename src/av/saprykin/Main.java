package av.saprykin;


import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FolderScanner folderScanner = new FolderScanner();
        Scanner in = new Scanner(System.in);
    // например /home/someuser/somefolder
        System.out.print("Input a root directory path: ");
        String path = in.nextLine();
        while (!new File(path).isDirectory()) {
            System.out.println("path not valid. Please try again");
            path = in.nextLine();
        }
        folderScanner.findAllTextFiles(new File(path));

        // например /home/someuser/somefolder/somefile.txt

        System.out.println("Found all files in that folder and subfolders.\nFor to copy the text from them to the file, enter the path to it: ");

        path = in.nextLine();
        while (!new File(path).isFile()) {
            System.out.println("path not valid. Please try again");
            path = in.nextLine();
        }
        in.close();
        path = folderScanner.writeAllTextFilesToFile(new File(path)).getAbsolutePath();
        System.out.println("Done! File is exist in " + path);
    }
}
