package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Deletar {

	public Deletar(){
		Fachada.inicializar();
		try {
			System.out.println("deletando...");
			
			Fachada.excluirGeneroFilme("Avatar alterado","Action");
			System.out.println("deletou Action do filme Avatar");		
			
			Fachada.excluirFilme("Need for Speed");
			System.out.println("deletou Need for Speed");
			
			Fachada.excluirFilme("Avatar alterado");
			System.out.println("deletou Avatar");
			
			Fachada.excluirGenero("Action");
			System.out.println("deletou Acao");
			
			Fachada.excluirVisualizacoes("Mauricio");
			System.out.println("deletou as Visualizações de Mauricio");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	public static void main(String[] args) {
		new Deletar();
	}
}

