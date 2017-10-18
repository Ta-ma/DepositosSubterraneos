package ds;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Procesador
{
	List<Depósito> depósitos;
	int pet;
	boolean rebasan;
	int petExcedente;
	int c;
	int profundidad;
	
	public Procesador(File archivo) throws IOException
	{
		Scanner sc = new Scanner(archivo);
		
		int tam = sc.nextInt();
		depósitos = new ArrayList<Depósito>(tam);
		
		for (int i = 0; i < tam; i++)
		{
			depósitos.add(new Depósito(sc.nextInt(), sc.nextInt()));
		}
			
		pet = sc.nextInt();
		
		sc.close();
	}

	public void procesar()
	{
		// verifico si rebasan
		int acum = 0;
		for (int i = 0; i < depósitos.size(); i++)
		{
			acum += depósitos.get(i).getVolumen();
		}
			
		if (pet > acum)
		{
			rebasan = true;
			petExcedente = pet - acum;
			return;
		}
		
		// si no rebasan
		profundidad = depósitos.get(0).getProfundidad();
		c = 1;
		
		// si es uno solo
		if (depósitos.size() == 1)
		{
			int v = 0;
			int volumenTotal = 0;	
			while (pet > volumenTotal)
			{
				v = 0;
				profundidad--;
				for (int i = 0; i < c; i++)
					v += depósitos.get(i).getVolumen(profundidad);
				volumenTotal = v;
			}
			
			return;
		}
		
		// son más de 1
		
		boolean falta = true;
		
		while (falta)
		{
			Depósito depoActual = depósitos.get(c);	
			profundidad = depoActual.getProfundidad();
			int v = 0;
			
			for (int i = 0; i < c; i++)
				v += depósitos.get(i).getVolumen(profundidad);
			
			if (v <= pet)
				c++;
			else
			{
				// ya se cuantos depósitos necesito para cargar todo el petróleo
				falta = false;
				v = 0;
				int volumenTotal = 0;
				for (int i = 0; i < c; i++)
					v += depósitos.get(i).getVolumen(profundidad);
				volumenTotal = v;
				
				while (pet > volumenTotal)
				{
					v = 0;
					profundidad--;
					for (int i = 0; i < c; i++)
						v += depósitos.get(i).getVolumen(profundidad);
					volumenTotal = v;
				}
				
			}	
		}
	}

	public void mostrarResultados(File file) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(file));
		
		if (rebasan)
			out.println("Rebasan : " + petExcedente);
		else
		{
			out.println(c);
			out.println(profundidad);
		}
		
		out.close();
	}

}
