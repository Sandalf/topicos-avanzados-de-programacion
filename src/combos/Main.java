package combos;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		leerEstados();
	}
	
	public static void leerEstados() {
		try {
			ArchivoEstados archEdos = new ArchivoEstados();
			ArrayList<Estado> estados = archEdos.leerEstados();
			
			for(Estado edo: estados) {
				System.out.println(edo);
			}
			
		} catch (Exception e) {
			System.out.println("Ocurrio un error en el metodo leerEstados()");
			e.printStackTrace();
		}
	}

}
