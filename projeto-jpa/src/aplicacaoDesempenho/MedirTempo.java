/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package aplicacaoDesempenho;

import java.util.Calendar;
import java.util.GregorianCalendar;
import fachada.Fachada;

public class MedirTempo {

	public static void main(String[] args) {
		Calendar hinicial, hfinal;

		Fachada.inicializar();
		
		// GRAVANDO DADOS NO BANCO
		
		hinicial = new GregorianCalendar();
		System.out.println("\ninicio da gravação " + hinicial.getTime());
		try{
			for (int i = 1; i <= 1000; i++){
				String nome = "genero"+i;
				Fachada.cadastrarGenero(nome);
				Fachada.adicionarFilmeGenero(nome, "Avatar");
//				Fachada.adicionarFilmeGenero(nome, "Hulk");
			}
		}
		catch(Exception e){
			System.out.println("erro:"+ e.getMessage());
		}
		System.out.println("fim da gravação    " + new GregorianCalendar().getTime());			

		// LENDO DADOS DO BANCO
		
		System.out.println("\n\ninicio da consulta =  " + new GregorianCalendar().getTime());	
		@SuppressWarnings("unused")
		String s = Fachada.listarGeneros();
		System.out.println("fim da consulta =     " + new GregorianCalendar().getTime());
		hfinal = new GregorianCalendar();

		// CALCULANDO TEMPO TOTAL
		
		System.out.println( 
				"\n\n Hora inicial= " +hinicial.getTime() +
				"\n Hora final=   "+ hfinal.getTime() +
				"\n Total (seg)=  "+(hfinal.getTimeInMillis()-hinicial.getTimeInMillis())/1000.0
				);

		Fachada.finalizar();
	}

}
