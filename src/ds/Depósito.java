package ds;

public class Depósito
{
	private int base;
	private int profundidad;
	
	public Depósito (int base, int profundidad)
	{
		this.base = base;
		this.profundidad = profundidad;
	}

	public int getBase()
	{
		return base;
	}

	public int getProfundidad()
	{
		return profundidad;
	}

	public int getVolumen()
	{
		return base * profundidad;
	}
	
	public int getVolumen(int nivelProfundidad)
	{
		int nivel = profundidad - nivelProfundidad;
		
		if (nivel <= 0)
			return 0;
		
		return base * nivel;
	}

	@Override
	public String toString()
	{
		return "Depósito [base=" + base + ", profundidad=" + profundidad + "]";
	}
}
