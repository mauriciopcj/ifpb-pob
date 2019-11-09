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

public class Consultar {

	public Consultar(){
		Fachada.inicializar();
		try {
			System.out.println(Fachada.consultarGeneroPorNome("Act") );
			System.out.println(Fachada.consultarGeneroPorFilme("Avatar"));
			
			System.out.println(Fachada.consultarFilmePorTitulo("va"));
			System.out.println(Fachada.consultarFilmePorGenero("Action"));
			
			System.out.println(Fachada.consultarVisualizacaoPorUsuario("Mauricio"));
			System.out.println(Fachada.consultarVisualizacaoPorFilme("Avatar"));
		
			System.out.println(Fachada.consultarVisualizacaoUsuarioPorGenero("Mauricio","Action"));
			System.out.println(Fachada.consultarGeneroMaisAssistido());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
		System.out.println("\nfim do programa");
	}



	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}

