package componentes;

public class Baraja 
{
	private int nombre;
	private int numero;
	
	public Baraja() 
	{
		
	}
	public Baraja(int n,int nu) 
	{
		nombre=n;
		numero=nu;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}	
}
