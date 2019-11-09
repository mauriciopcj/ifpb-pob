/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package aplicacaoConsole;

import fachada.Fachada;

public class Listar {

	public Listar(){
		Fachada.inicializar();
		try {
			
			System.out.println(Fachada.listarFilmes());
			System.out.println(Fachada.listarGeneros());
			System.out.println(Fachada.listarUsuarios());
			System.out.println(Fachada.listarVisualizacoes());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
	}

	public static void main(String[] args) {
		new Listar();
	}
}
