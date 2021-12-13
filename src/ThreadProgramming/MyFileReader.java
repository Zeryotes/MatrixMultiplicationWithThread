package ThreadProgramming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://www.devmedia.com.br/leitura-e-escrita-de-arquivos-de-texto-em-java/25529
// https://stackoverflow.com/questions/3806062/how-to-open-a-txt-file-and-read-numbers-in-java

public class MyFileReader {
	public static void onlyRead(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) 
		{
			if (linha != null) {
				System.out.println(linha);
			} else
				break;
			
			linha = buffRead.readLine();
		}
		buffRead.close();
	}
	
	public static List<Integer> read(String path, int dimensao) throws IOException {
		Path filePath = Paths.get(path);
		Scanner scanner = new Scanner(filePath);
		List<Integer> integers = new ArrayList<>();
		scanner.nextLine();
		while (scanner.hasNext()) {
		    if (scanner.hasNextInt()) {
		        integers.add(scanner.nextInt());
		    } else {
		        scanner.next();
		    }
		}
		
		scanner.close();

		OperateMatrix.print(integers, dimensao);
		
		return integers;
	}
}
