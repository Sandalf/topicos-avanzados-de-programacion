package combos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ArchivoMunicipios {
	
	private static String nombreArchivo = "Municipios.dat";
	private RandomAccessFile archivo;

	public ArchivoMunicipios() throws FileNotFoundException {
		this.archivo = new RandomAccessFile(nombreArchivo,"rw" );
	}

	public ArchivoMunicipios(String nombreArchivo) throws FileNotFoundException {
		this.archivo = new RandomAccessFile(nombreArchivo,"rw" );
	}
	
	//------------------------------------------------------------------------//
	public ArrayList<Municipio> leerMunicipios() throws Exception {

		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		long longitudArchivo = archivo.length();
		int longitudRenglon = 60;
		int cantidadRegistros = (int) (longitudArchivo/longitudRenglon);

		reiniciarPuntero();
		for(int i = 0; i < cantidadRegistros; i++) {
			Municipio municipio = new Municipio();
			municipio.setEstadoId(archivo.readInt());
			municipio.setMunicipioId(archivo.readInt());
			municipio.setNombreMunicipio(archivo.readUTF());
			municipios.add(municipio);
		}

		return municipios;		
	}
	
	//------------------------------------------------------------------------//
	public ArrayList<Municipio> buscarMunicipios(int estadoId) throws Exception {

		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		long longitudArchivo = archivo.length();
		int longitudRenglon = 60;
		int cantidadRegistros = (int) (longitudArchivo/longitudRenglon);

		reiniciarPuntero();
		for(int i = 0; i < cantidadRegistros; i++) {
			Municipio municipio = new Municipio();
			municipio.setEstadoId(archivo.readInt());
			municipio.setMunicipioId(archivo.readInt());
			municipio.setNombreMunicipio(archivo.readUTF());
			if (municipio.getEstadoId() == estadoId) {
				municipios.add(municipio);
			}
		}

		return municipios;		
	}
		
	//------------------------------------------------------------------------//
	public void reiniciarPuntero() throws IOException {
		archivo.seek(0);
	}
	
}
