package ThreadProgramming;

import java.util.ArrayList;
import java.util.List;

public class OperateMatrix {
	public static List<Integer> multiply(List<Integer> MatrizA, List<Integer> MatrizB, int dimensao){
		List<Integer> MatrizResultado = new ArrayList<Integer>();
		
		int i, j, k, soma;
		
		for(i = 0; i < dimensao; i++) {
			for(j = 0; j < dimensao; j++) {
				soma = 0;
				for(k = 0; k < dimensao; k++) {
					soma = soma +
						   MatrizA.get(k+i*dimensao) * 
						   MatrizB.get(j+k*dimensao);
				}
				MatrizResultado.add(soma);
			}
		}
		return MatrizResultado;
	}
	
	public static void print(List<Integer> Matriz, int dimensao) {
		System.out.println("");
		System.out.println(dimensao + " " + dimensao);
		for(int i = 0; i < dimensao; i++) {
			for(int j = 0; j < dimensao; j++) {
				System.out.print(Matriz.get(j+(i*dimensao)) + " ");
			}
			System.out.println("");			
		}
	}
}
