package ThreadProgramming;

import java.util.ArrayList;
import java.util.List;

public class OperateMatrix {
	public static List<Integer> multiply(List<Integer> MatrixA, List<Integer> MatrixB, int dimension){
		List<Integer> MatrixResultado = new ArrayList<Integer>();
		
		int i, j, k, sum;
		
		for(i = 0; i < dimension; i++) {
			for(j = 0; j < dimension; j++) {
				sum = 0;
				for(k = 0; k < dimension; k++) {
					sum = sum +
						   MatrixA.get(k+i*dimension) * 
						   MatrixB.get(j+k*dimension);
				}
				MatrixResultado.add(sum);
			}
		}
		return MatrixResultado;
	}
	
	public static void print(List<Integer> Matrix, int dimension) {
		System.out.println("");
		System.out.println(dimension + " " + dimension);
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				System.out.print(Matrix.get(j+(i*dimension)) + " ");
			}
			System.out.println("");			
		}
	}
}
