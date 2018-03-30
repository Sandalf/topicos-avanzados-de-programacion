package combos;

public class Estado {

	private Integer estadoId;
	private String nombreEstado;
	
	public Estado() {
		super();
		this.estadoId = 0;
		this.nombreEstado = null;
	}

	public Estado(Integer estadoId, String nombreEstado) {
		super();
		this.estadoId = estadoId;
		this.nombreEstado = nombreEstado;
	}

	public Integer getEstadoId() {
		return estadoId;
	}
	
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	
	public String getNombreEstado() {
		return nombreEstado;
	}
	
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	@Override
	public String toString() {
		return "Estado [estadoId=" + estadoId + ", nombreEstado=" + nombreEstado + "]";
	}
	
}
