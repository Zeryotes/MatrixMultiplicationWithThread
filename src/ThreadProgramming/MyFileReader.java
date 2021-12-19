package ThreadProgramming;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://www.devmedia.com.br/leitura-e-escrita-de-arquivos-de-texto-em-java/25529
// https://stackoverflow.com/questions/3806062/how-to-open-a-txt-file-and-read-numbers-in-java

public class MyFileReader {
	public static List<Integer> read(String path, int dimension) throws IOException {
		Path filePath = Paths.get(path);
		Scanner scanner = new Scanner(filePath);
		List<Integer> integersList = new ArrayList<>();
		scanner.nextLine();
		while (scanner.hasNext()) {
		    if (scanner.hasNextInt()) {
		        integersList.add(scanner.nextInt());
		    } else {
		        scanner.next();
		    }
		}
		
		scanner.close();
		
		return integersList;
	}
}
