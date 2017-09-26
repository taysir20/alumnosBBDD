package vista;

import controlador.Controlador;
import modelo.Modelo;

public interface Vista {
	public void setModelo(Modelo modelo);
	public void setControlador(Controlador controlador);
}
