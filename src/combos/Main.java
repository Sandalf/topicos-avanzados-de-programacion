package combos;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			ArchEdoMunCds.init();
			new Aplicacion();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
