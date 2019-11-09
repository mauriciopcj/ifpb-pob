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

public class Atualizar {

	public Atualizar(){
		
		Fachada.inicializar();
		
		try {
			System.out.println("alterando...");
			Fachada.alterarTituloFilme("Avatar", "Avatar alterado");
			Fachada.alterarNomeGenero("Terror", "Terror alterado");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		
		System.out.println("fim do programa");
	}

	public static void main(String[] args) {
		new Atualizar();
	}
}
