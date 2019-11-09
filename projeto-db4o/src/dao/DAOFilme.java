/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package dao;

import java.util.List;
import com.db4o.query.Query;
import modelo.Filme;

public class DAOFilme  extends DAO<Filme> {
	
	public Filme read (Object chave) {
		String titulo = (String) chave;
		Query q = manager.query();
		q.constrain(Filme.class);
		q.descend("titulo").constrain(titulo);
		List<Filme> resultados = q.execute();
		if (resultados.size() > 0) {
			return resultados.get(0);
		} else {
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                                 TODAS AS CONSULTAS DE FILME  //

	public List<Filme> consultarFilmePorTitulo(String prefixo) {
		Query q = manager.query();
		q.constrain(Filme.class);
		q.descend("titulo").constrain(prefixo).contains();
		List<Filme> result = q.execute(); 
		return result;
	}
	
	public List<Filme> consultarFilmePorGenero(String prefixo) {
		Query q = manager.query();
		q.constrain(Filme.class);
		q.descend("generos").descend("nome").constrain(prefixo).contains();
		List<Filme> result = q.execute(); 
		return result;
	}
	
}
