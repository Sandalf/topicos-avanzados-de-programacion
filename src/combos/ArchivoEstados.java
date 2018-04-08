package combos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ArchivoEstados {

	private static String nombreArchivo = "Estados.dat";
	private RandomAccessFile archivo;

	public ArchivoEstados() throws FileNotFoundException {
		this.archivo = new RandomAccessFile(nombreArchivo,"rw" );
	}

	public ArchivoEstados(String nombreArchivo) throws FileNotFoundException {
		this.archivo = new RandomAccessFile(nombreArchivo,"rw" );
	}

	public ArrayList<Estado> leerEstados() throws Exception {

		ArrayList<Estado> estados = new ArrayList<Estado>();
		long longitudArchivo = archivo.length();
		int longitudRenglon = 56;
		int cantidadRegistros = (int) (longitudArchivo/longitudRenglon);

		reiniciarPuntero();
		for(int i = 0; i < cantidadRegistros; i++) {
			Estado estado = new Estado();
			estado.setEstadoId(archivo.readInt());
			estado.setNombreEstado(archivo.readUTF().trim());
			estados.add(estado);
		}

		return estados;		
	}
	
	public Estado buscarEstado(String nombreEstado) throws Exception {
		Estado estado = new Estado();
		long longitudArchivo = archivo.length();
		int longitudRenglon = 56;
		int cantidadRegistros = (int) (longitudArchivo/longitudRenglon);

		reiniciarPuntero();
		for(int i = 0; i < cantidadRegistros; i++) {
			estado.setEstadoId(archivo.readInt());
			estado.setNombreEstado(archivo.readUTF().trim());
			
			if (estado.getNombreEstado().equals(nombreEstado)) {
				return estado;
			}
		}

		return null;		
	}


	public void reiniciarPuntero() throws IOException {
		archivo.seek(0);
	}

}
