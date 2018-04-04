package combos;

public class Ciudad {
	private Integer estadoId;
	private Integer municipioId;
	private Integer ciudadId;
	private String nombreCiudad;
	
	public Ciudad()
	{
		this.estadoId = 0;
		this.municipioId= 0;
		this.ciudadId = 0;
		this.nombreCiudad = null;
	}
	
	public Ciudad( int estadoId , int municipioId , int ciudadId , String nombreCiudad )
	{
		this.estadoId = estadoId;
		this.municipioId= municipioId;
		this.ciudadId = ciudadId;
		this.nombreCiudad = nombreCiudad;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public Integer getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(Integer municipioId) {
		this.municipioId = municipioId;
	}

	public Integer getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(Integer ciudadId) {
		this.ciudadId = ciudadId;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	
	public String toString()
	{
		return "Ciudad [ciudadId="+ ciudadId + ", nombreCiudad= "+ nombreCiudad +
				", EstadoId=" + estadoId +", municipioId=" +municipioId +"]";
	}
	

}
