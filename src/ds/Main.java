package ds;

import java.io.File;
import java.io.IOException;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		File dir = new File(".//IN//");
		
		for (File archivo : dir.listFiles())
		{
			Procesador procesador = new Procesador(archivo);
			procesador.procesar();
			procesador.mostrarResultados(new File(".//OUT//" + archivo.getName().replaceAll(".in", ".out")));
		}

	}

}
