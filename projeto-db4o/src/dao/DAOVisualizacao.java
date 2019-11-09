/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package dao;

import java.time.LocalDateTime;
import java.util.List;
import com.db4o.query.Query;

import modelo.Visualizacao;

public class DAOVisualizacao  extends DAO<Visualizacao> {
	
	public Visualizacao read (Object chave) {
		LocalDateTime data = (LocalDateTime) chave;
		Query q = manager.query();
		q.constrain(Visualizacao.class);
		q.descend("data").constrain(data);
		List<Visualizacao> resultados = q.execute();
		if (resultados.size() > 0) {
			return resultados.get(0);
		} else {
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                          TODAS AS CONSULTAS DE VISUALIZA��O  //

	public List<Visualizacao> consultarVisualizacaoPorUsuario(String prefixo) {
		Query q = manager.query();
		q.constrain(Visualizacao.class);
		q.descend("usuario").descend("nome").constrain(prefixo).startsWith(true);
		List<Visualizacao> result = q.execute(); 
		return result;
	}
	
	public List<Visualizacao> consultarVisualizacaoPorFilme(String prefixo) {
		Query q = manager.query();
		q.constrain(Visualizacao.class);
		q.descend("filme").descend("titulo").constrain(prefixo).startsWith(true);
		List<Visualizacao> result = q.execute(); 
		return result;
	}
	
	//	quantidade de filmes que o usuario X assistiu do genero X	
	
	public int consultarVisualizacaoUsuarioPorGenero(String usuario, String genero) {
		Query q = manager.query();
		q.constrain(Visualizacao.class);
		q.descend("usuario").descend("nome").constrain(usuario).contains();
		q.descend("filme").descend("generos").descend("nome").constrain(genero);
		int result = q.execute().size(); 
		return result;
	}
	
	// 
}
