package av.saprykin;

import java.io.*;
import java.util.*;

public  class FolderScanner {
    private Map<String, String> fileList = new HashMap<>();

    public FolderScanner() {
    }

    public Map<String, String> getFileList() {
        return fileList;
    }

    private Optional<Map.Entry<String, String>> findKey(String value) {
        return fileList.entrySet().stream()
                .filter(entry -> entry.getValue().equals(value))
                .findAny();
    }

    private void copyTextToFile(String copiedFilePath, String finalFilePath) {

        try(        FileReader fr = new FileReader(copiedFilePath);
                    FileWriter writer = new FileWriter(finalFilePath, true);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
        ) {
            StringBuilder temp = new StringBuilder();
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                temp.append(scan.nextLine());
                temp.append("\n");
            }
            bufferWriter.write(String.valueOf(temp));
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
//поиск всех файлов в папке и добавление их в список файлов
    public void findAllTextFiles(File path) {
        for (File item : path.listFiles()) {
            if (item.isDirectory()) {
                findAllTextFiles(item);
            } else fileList.put(item.getAbsolutePath(), item.getName());
        }
    }
//копирование текста из всех файлов из списка в файл
    public File writeAllTextFilesToFile(File finalFilePath){
        this.fileList.entrySet().stream()
                .sorted(Map.Entry.<String, String>comparingByValue())
                .forEach(s-> {
                        copyTextToFile(s.getKey(), finalFilePath.getAbsolutePath());
                });

        return finalFilePath;
    }




}
