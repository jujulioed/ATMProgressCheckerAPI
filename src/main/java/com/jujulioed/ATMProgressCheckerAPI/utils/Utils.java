package com.jujulioed.ATMProgressCheckerAPI.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Utils {

    public static String getFileData(String path) {
        String directoryPath = checkPathFormat(path);
        File file = new File(directoryPath);
        StringBuilder stringData = new StringBuilder();
        try (Scanner data = new Scanner(file);){
            while (data.hasNextLine()) {
                stringData.append(data.nextLine());
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return stringData.toString();
    }

    public static ArrayList<Path> getAllFilesInPath(String directoryPath) {
        ArrayList<Path> filesList = new ArrayList<>();
        String path = checkPathFormat(directoryPath);

        try (Stream<Path> files = Files.list(Paths.get(path))) {
            files.filter(Files::isRegularFile) // Incluir apenas arquivos e não outros paths
                    .forEach(filesList::add);
        } catch (IOException error) {
            System.err.println("An I/O error ocurred: " + error.getMessage());
        }

        return filesList;
    }

    private static String checkPathFormat(String pathToBeValidated) {
        if (pathToBeValidated.contains("\\")) {
            pathToBeValidated = pathToBeValidated.replace("\\", "\\\\");
        }

        return  pathToBeValidated;
    }
}