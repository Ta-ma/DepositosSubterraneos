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
	List<Dep�sito> dep�sitos;
	int pet;
	boolean rebasan;
	int petExcedente;
	int c;
	int profundidad;
	
	public Procesador(File archivo) throws IOException
	{
		Scanner sc = new Scanner(archivo);
		
		int tam = sc.nextInt();
		dep�sitos = new ArrayList<Dep�sito>(tam);
		
		for (int i = 0; i < tam; i++)
		{
			dep�sitos.add(new Dep�sito(sc.nextInt(), sc.nextInt()));
		}
			
		pet = sc.nextInt();
		
		sc.close();
	}

	public void procesar()
	{
		// verifico si rebasan
		int acum = 0;
		for (int i = 0; i < dep�sitos.size(); i++)
		{
			acum += dep�sitos.get(i).getVolumen();
		}
			
		if (pet > acum)
		{
			rebasan = true;
			petExcedente = pet - acum;
			return;
		}
		
		// si no rebasan
		profundidad = dep�sitos.get(0).getProfundidad();
		c = 1;
		
		// si es uno solo
		if (dep�sitos.size() == 1)
		{
			int v = 0;
			int volumenTotal = 0;	
			while (pet > volumenTotal)
			{
				v = 0;
				profundidad--;
				for (int i = 0; i < c; i++)
					v += dep�sitos.get(i).getVolumen(profundidad);
				volumenTotal = v;
			}
			
			return;
		}
		
		// son m�s de 1
		
		boolean falta = true;
		
		while (falta)
		{
			Dep�sito depoActual = dep�sitos.get(c);	
			profundidad = depoActual.getProfundidad();
			int v = 0;
			
			for (int i = 0; i < c; i++)
				v += dep�sitos.get(i).getVolumen(profundidad);
			
			if (v <= pet)
				c++;
			else
			{
				// ya se cuantos dep�sitos necesito para cargar todo el petr�leo
				falta = false;
				v = 0;
				int volumenTotal = 0;
				for (int i = 0; i < c; i++)
					v += dep�sitos.get(i).getVolumen(profundidad);
				volumenTotal = v;
				
				while (pet > volumenTotal)
				{
					v = 0;
					profundidad--;
					for (int i = 0; i < c; i++)
						v += dep�sitos.get(i).getVolumen(profundidad);
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
