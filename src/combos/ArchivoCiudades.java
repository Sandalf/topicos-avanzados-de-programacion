package combos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
public class ArchivoCiudades {
	
	private static String nombreArchivo = "Ciudades.dat";
	private RandomAccessFile archivo;
	
	public ArchivoCiudades() throws FileNotFoundException {
		this.archivo = new RandomAccessFile(nombreArchivo,"rw" );
	}

	public ArchivoCiudades(String nombreArchivo) throws FileNotFoundException {
		this.archivo = new RandomAccessFile(nombreArchivo,"rw" );
	}
	
	public ArrayList<Ciudad> leerCiudades() throws IOException{
		
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		
		long longitudArchivo = archivo.length();
		int longitudRenglon = 64;
		int cantidadRegistros = (int) (longitudArchivo/longitudRenglon);
		reiniciarPuntero();
		for(int i = 0 ; i < cantidadRegistros ; i++){
			Ciudad ciudad = new Ciudad();
			ciudad.setEstadoId( archivo.readInt() );
			ciudad.setMunicipioId( archivo.readInt() );
			ciudad.setCiudadId( archivo.readInt() );
			ciudad.setNombreCiudad( archivo.readUTF() );
			ciudades.add(ciudad);
		}
		return ciudades;
	}
	
	
	public ArrayList<Ciudad> buscarCiudades(int estadoId, int municipioId) throws Exception {
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		

		long longitudArchivo = archivo.length();
		int longitudRenglon = 64;
		int cantidadRegistros = (int) (longitudArchivo/longitudRenglon);
		reiniciarPuntero();
		
		for(int i = 0; i < cantidadRegistros; i++) {
			Ciudad ciudad = new Ciudad();
			ciudad.setEstadoId(archivo.readInt());
			ciudad.setMunicipioId(archivo.readInt());
			ciudad.setCiudadId(archivo.readInt());
			ciudad.setNombreCiudad(archivo.readUTF().trim());
			
			if (ciudad.getEstadoId() == estadoId && ciudad.getMunicipioId() == municipioId) {
				ciudades.add(ciudad);
			}
		}
		
		return ciudades;
	}
	
	public void reiniciarPuntero() throws IOException {
		archivo.seek(0);
	}

	public Ciudad buscarCiudad(Integer estadoId, int municipioId, String nombreCiudad) throws IOException {
		Ciudad ciudad = new Ciudad();
		long longitudArchivo = archivo.length();
		int longitudRenglon = 64;
		int cantidadRegistros = (int) (longitudArchivo/longitudRenglon);

		reiniciarPuntero();
		for(int i = 0; i < cantidadRegistros; i++) {
			ciudad.setEstadoId(archivo.readInt());
			ciudad.setMunicipioId(archivo.readInt());
			ciudad.setCiudadId(archivo.readInt());
			ciudad.setNombreCiudad(archivo.readUTF().trim());
			
			if (ciudad.getNombreCiudad().equals(nombreCiudad) && ciudad.getEstadoId() == estadoId && ciudad.getMunicipioId() == municipioId) {
				return ciudad;
			}
		}
		
		return null;
	}

}
