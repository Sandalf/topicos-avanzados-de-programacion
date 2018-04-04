package combos;

public class Municipio{

	private Integer estadoId;
	private Integer municipioId;
	private String nombreMunicipio;
	
	public Municipio() {
		super();
		this.estadoId = 0;
		this.municipioId = 0;
		this.nombreMunicipio = null;
	}
	
	public Municipio(int municipioId, String nombreMunicipio, int estadoId) {
		super();
		this.municipioId = municipioId;
		this.nombreMunicipio = nombreMunicipio;
		this.estadoId = estadoId;
	}

	public int getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(Integer municipioId) {
		this.municipioId = municipioId;
	}

	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	public int getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "Municipio [municipioId=" + municipioId + ", nombreMunicipio=" + nombreMunicipio + ", estadoId="
				+ estadoId + "]";
	}
	
}
