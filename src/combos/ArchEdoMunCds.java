package combos;

import java.io.*;

public class ArchEdoMunCds {

	public static void main(String[] args) throws IOException {
		File FEdo = new File("Estados.Dat");
		File FMun = new File("Municipios.Dat");
		File FCds = new File("Ciudades.Dat");

		RandomAccessFile ArchEdo = new RandomAccessFile(FEdo, "rw");
		RandomAccessFile ArchMun = new RandomAccessFile(FMun, "rw");
		RandomAccessFile ArchCds = new RandomAccessFile(FCds, "rw");
		String[] VE = { "BCS", "BC", "SONORA", "SINALOA", "NAYARIT", "COLIMA", "JALISCO", "MICHOACAN", "DURANGO",
				"CHIHUAHUA" };

		// ArchEdo.seek(ArchEdo.length());
		// Grabar estados
		for (int i = 0; i < 10; i++) {
			ArchEdo.writeInt(i + 1);
			String Texto = Rutinas.PonBlancos(VE[i], 50);
			ArchEdo.writeUTF(Texto);
			for (int j = 0; j < Rutinas.nextInt(3, 5); j++) {
				ArchMun.writeInt(i + 1);
				ArchMun.writeInt(j + 1);
				ArchMun.writeUTF(Rutinas.PonBlancos("MUN" + (j + 1) + VE[i], 50));
				for (int k = 0; k < Rutinas.nextInt(3, 7); k++) {
					ArchCds.writeInt(i + 1);
					ArchCds.writeInt(j + 1);
					ArchCds.writeInt(k + 1);
					ArchCds.writeUTF(Rutinas.PonBlancos("Cd" + (k + 1), 50));
				}
			}
		}

		// Imrimir el contenido de los estados
		ArchEdo.seek(0);
		int EdoCveEdo;
		String EdoNombre;
		int MunCveEdo, MunCveMun;
		String MunNombre;
		int CdsCveEdo, CdsCveMun, CdsCveCd;
		String CdNombre;

		for (int i = 0; i < ArchEdo.length() / 56; i++) {
			EdoCveEdo = ArchEdo.readInt();
			EdoNombre = ArchEdo.readUTF();
			System.out.println(EdoCveEdo + " " + EdoNombre);
			ArchMun.seek(0);

			for (int j = 0; j < ArchMun.length() / 60; j++) {
				MunCveEdo = ArchMun.readInt();
				MunCveMun = ArchMun.readInt();
				MunNombre = ArchMun.readUTF();
				if (EdoCveEdo == MunCveEdo) {
					System.out.println("\t" + MunCveEdo + " " + MunCveMun + " " + MunNombre);
					ArchCds.seek(0);
					for (int k = 0; k < ArchCds.length() / 64; k++) {
						CdsCveEdo = ArchCds.readInt();
						CdsCveMun = ArchCds.readInt();
						CdsCveCd = ArchCds.readInt();
						CdNombre = ArchCds.readUTF();

						if (EdoCveEdo == CdsCveEdo && MunCveMun == CdsCveMun)
							System.out.println("\t\t" + CdsCveEdo + " " + CdsCveMun + " " + CdsCveCd + " " + CdNombre);
					}

				}
			}

		}

		ArchEdo.close();
		ArchMun.close();
		ArchCds.close();
	}

}
