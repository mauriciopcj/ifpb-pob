/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package aplicacaoConsole;

import java.time.LocalDate;

import fachada.Fachada;
import modelo.Filme;
import modelo.Genero;
import modelo.Usuario;
import modelo.Visualizacao;

public class Cadastrar {

	public Cadastrar(){
		Filme f;
		Genero g;
		Usuario u;
		Visualizacao v;
		
		try {
			Fachada.inicializar();
			
			System.out.println("cadastrando...");
			
			// FILMES
			
			f = Fachada.cadastrarFilme("Avatar");
			System.out.println(f);
			f = Fachada.cadastrarFilme("Need for Speed");
			System.out.println(f);
			f = Fachada.cadastrarFilme("The Avengers");
			System.out.println(f);
			f = Fachada.cadastrarFilme("Doctor Strange");
			System.out.println(f);
			f = Fachada.cadastrarFilme("Joker");
			System.out.println(f);
			f = Fachada.cadastrarFilme("Captain Marvel");
			System.out.println(f);
			f = Fachada.cadastrarFilme("Alita");
			System.out.println(f);
			f = Fachada.cadastrarFilme("Logan");
			System.out.println(f);
			f = Fachada.cadastrarFilme("Aquaman");
			System.out.println(f);
			
			// USUARIOS
			
			u = Fachada.cadastrarUsuario("Mauricio", "mauricio@mauricio", "123456", LocalDate.of(1988, 9, 22));
			System.out.println(u);
			u = Fachada.cadastrarUsuario("Romero", "romero@romero", "123", LocalDate.of(2000, 2, 11));
			System.out.println(u);
			u = Fachada.cadastrarUsuario("Rafael", "rafael@rafael", "456", LocalDate.of(1999, 5, 30));
			System.out.println(u);
			
			// GENEROS
						
			g = Fachada.cadastrarGenero("Ação");
			System.out.println(g);
			g = Fachada.cadastrarGenero("Aventura");
			System.out.println(g);
			g = Fachada.cadastrarGenero("Fantasia");
			System.out.println(g);
			g = Fachada.cadastrarGenero("Terror");
			System.out.println(g);
			
			// GENEROS AOS FILMES
			
			g = Fachada.adicionarGeneroFilme("Avatar","Ação");
			g = Fachada.adicionarGeneroFilme("Avatar","Aventura");
			g = Fachada.adicionarGeneroFilme("Avatar","Fantasia");
			g = Fachada.adicionarGeneroFilme("Avatar","Terror");
			
			// VISUALIZACOES
			
			v = Fachada.cadastrarVisualizacao("Avatar");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Aquaman");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Doctor Strange");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Avatar");
			System.out.println(v);
			
			Fachada.setUserlogado("Romero");
			
			v = Fachada.cadastrarVisualizacao("Joker");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Logan");
			System.out.println(v);
			
			Fachada.setUserlogado("Rafael");
			
			v = Fachada.cadastrarVisualizacao("The Avengers");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Need for Speed");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Doctor Strange");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Captain Marvel");
			System.out.println(v);
			v = Fachada.cadastrarVisualizacao("Alita");
			System.out.println(v);
						
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
		}
		System.out.println("fim do programa");
	}
	
	public static void main(String[] args) {
		new Cadastrar();
	}
}
