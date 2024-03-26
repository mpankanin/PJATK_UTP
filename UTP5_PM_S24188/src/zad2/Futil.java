package zad2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Futil {



    public static void processDir(String dirName, String resultFileName){

        Path startingDir = Paths.get(dirName);
        String resultFilePath = dirName + '/' + resultFileName;               //zmienić tu i w main \\ na / (test prawdopodobnie jest odpalany na linuxie)

        File resultFile = new File((resultFilePath));

        try {
            Files.write(resultFile.toPath(),
                        getPaths(startingDir)
                            .stream()
                            .map(Futil::getLines)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList()),
                        StandardCharsets.UTF_8);
        }catch (IOException ex){
                ex.printStackTrace();
            }

    }

    public static List<Path> getPaths (Path startPath) throws IOException{

        List<Path> paths = new ArrayList<>();

        Files.walkFileTree(startPath, new SimpleFileVisitor<>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                if(file.toString().endsWith(".txt"))
                    paths.add(file);

                return FileVisitResult.CONTINUE;
            }
        });

        return paths;
    }


    public static List<String> getLines (Path path) {
        try {
            return Files.readAllLines(path, Charset.forName("windows-1250"));
        }catch (IOException ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

}
