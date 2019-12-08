/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package aplicacaoJUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fachada.Fachada;
import modelo.Filme;
import modelo.Genero;

public class TesteJUnitCadastro {
	
	@BeforeClass
	public static void inicializarRecursosExternos() {
		Fachada.inicializar();
	}

	// TESTANDO PERSISTENCIA
	
	@Test
	public void test1() throws Exception {
		Genero g = Fachada.cadastrarGenero("Art");
		assertTrue(g.getNome().equals("Art"));
	}

	// TESTANDO PERSISTENCIA ENTRE RELACIONAMENTOS ( FILME E GENERO )
	
	@Test
	public void test2() throws Exception {
		Filme f = Fachada.cadastrarFilme("Avatar");
		Genero g1 = Fachada.adicionarGeneroFilme("Avatar", "Art");
		Genero g2 = Fachada.adicionarGeneroFilme("Avatar", "Abstract");
		assertTrue(g1.getFilmes().get(0).getTitulo().equals("Avatar"));
		assertEquals(g2.getFilmes().get(0).getTitulo(),"Avatar");
//		assertTrue(g2.getFilmes().get(0).getTitulo().equals("Avatar"));
		assertSame(g1.getFilmes().get(0),f);
		assertSame(g2.getFilmes().get(0),f);
	}

	// TESTANDO UNICIDADE DE OBJETOS
	
	@Test
	public void test3()  {
		try {
			Fachada.cadastrarGenero("Fantasia");
			Fachada.cadastrarGenero("Fantasia");
			fail("nao pode cadastrar dois generos com mesmo nome");
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals(
					"Cadastrar Genero - genero ja cadastrado: Fantasia"));
		}
	}

	@AfterClass
	public static void depois()  {
		Fachada.finalizar();
		new File("banco.db4o").delete();

	}

}
