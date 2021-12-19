package ThreadProgramming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainThread {
	
	static Integer[][] MatrizC;

	static class TestThread extends Thread {
		private Integer index;
		private List<Integer> MatrizA;
		private List<Integer> MatrizB;
		private int dimensao;

		public TestThread(Integer index, List<Integer> MatrizA, List<Integer> MatrizB, int dimensao) {
			this.index = index;
			this.MatrizA = MatrizA;
			this.MatrizB = MatrizB;
			this.dimensao = dimensao;
		}
		
		public void run() {
			multiply(this.index, this.MatrizA, this.MatrizB, this.dimensao, 4);
		}
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		String directory = "D:\\UFRN\\P8\\Concorrente\\MatrixMultiplicationWithThread\\Matrizes\\";
		String pathA = directory + "A" + args[0] + "x" + args[0] + ".txt";
		String pathB = directory + "B" + args[0] + "x" + args[0] + ".txt";
		String pathC = directory + "C" + args[0] + "x" + args[0] + "test.txt";
		
		String tipo = args[1];
		int dimensao = Integer.parseInt(args[0]);
		
		MatrizC = new Integer[dimensao][dimensao];
	
		List<Integer> MatrixA = new ArrayList<>();
		List<Integer> MatrixB = new ArrayList<>();

		System.out.print("Carregando Matriz A...");
		MatrixA = MyFileReader.read(pathA, dimensao);

		System.out.print("\nCarregando Matriz B...");
		MatrixB = MyFileReader.read(pathB, dimensao);
		
		if (tipo.equals("S")) {
			System.out.print("\nCalculando matriz " + dimensao + "x" + dimensao + " com versao serial...");
			multiply(0, MatrixA, MatrixB, dimensao, 1);
		} else if (tipo.equals("C")) {
			System.out.print("\nCalculando matriz " + dimensao + "x" + dimensao + " com versao concorrente...");
			TestThread t1 = new TestThread(0, MatrixA, MatrixB, dimensao);
			TestThread t2 = new TestThread(1, MatrixA, MatrixB, dimensao);
			TestThread t3 = new TestThread(2, MatrixA, MatrixB, dimensao);
			TestThread t4 = new TestThread(3, MatrixA, MatrixB, dimensao);
			
			t1.start();
			t2.start();
			t3.start();
			t4.start();

			try {
				t1.join();
				t2.join();
				t3.join();
				t4.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		MyFileWriter.writeMatrix(pathC, MatrizC, dimensao);
		
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("\n\nTempo de execução em Milisegundos: " + (elapsedTime/1000000));
		if((elapsedTime/1000000) >= 1000) {
			System.out.println("Tempo de execução em Segundos: " + (elapsedTime/1000000000));
		}
	}
	
	public static void multiply(
		Integer index,
		List<Integer> MatrizA,
		List<Integer> MatrizB,
		int dimensao,
		int gap
	){
		int i, j, k, soma;
		
		for(i = index; i < dimensao; i+=gap) {
			for(j = 0; j < dimensao; j++) {
				soma = 0;
				for(k = 0; k < dimensao; k++) {
					soma = soma +
						   MatrizA.get(k+i*dimensao) * 
						   MatrizB.get(j+k*dimensao);
				}
				MatrizC[i][j] = soma;
			}
		}
	}
}