package combos;

import java.util.Random;

public class Rutinas {
	static Random R = new Random();

	public static String PonBlancos(String Texto, int Tamano) {
		while (Texto.length() < Tamano)
			Texto += " ";
		return Texto;
	}

	public static int nextInt(int Valor) {
		return R.nextInt(Valor);
	}

	public static int nextInt(int Ini, int Fin) {
		return R.nextInt(Fin - Ini + 1) + Ini;
	}

}
