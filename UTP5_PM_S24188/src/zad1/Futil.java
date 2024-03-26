package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class Futil {


    public static void processDir(String dirName, String resultFileName){

        Path startingDir = Paths.get(dirName);
        String resultFilePath = dirName + '/' + resultFileName;               // zmienić tu i main \\ na / (test prawdopodobnie jest odpalany na linuxie)

        List<String> listOfReadLines = new ArrayList<>();
        List<Path> listOfPaths = new ArrayList<>();


        try{
            Files.walkFileTree(startingDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

                    if(file.toString().endsWith(".txt")){
                        listOfPaths.add(file);
                    }

                    return FileVisitResult.CONTINUE;
                }
            });

            for(Path path : listOfPaths)
                listOfReadLines.addAll(Files.readAllLines(path, Charset.forName("windows-1250")));

            File resultFile = new File((resultFilePath));

            Files.write(resultFile.toPath(), listOfReadLines, StandardCharsets.UTF_8);


        }catch (IOException ex){
            ex.printStackTrace();
        }



    }


}




