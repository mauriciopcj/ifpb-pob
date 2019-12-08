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

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Filme;
import modelo.Visualizacao;

public class DAOVisualizacao  extends DAO<Visualizacao> {
	
	public Visualizacao read (Object chave) {
		
		try{
			LocalDateTime data = (LocalDateTime) chave;
			javax.persistence.Query q = manager.createQuery("select v from Visualizacao v where v.data = :x");
			q.setParameter("x", data);
			return (Visualizacao) q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                          TODAS AS CONSULTAS DE VISUALIZA��O  //

	public List<Visualizacao> consultarVisualizacaoPorUsuario(String prefixo) {
		Query q = manager.createQuery("select v from Visualizacao v where v.usuario.nome LIKE ?1");
		q.setParameter(1, prefixo+"%");
		@SuppressWarnings("unchecked")
		List<Visualizacao> result = q.getResultList();
		return result;
	}
	
	public List<Visualizacao> consultarVisualizacaoPorFilme(String prefixo) {
		Query q = manager.createQuery("select v from Visualizacao v where v.filme.titulo LIKE ?1");
		q.setParameter(1, prefixo+"%");
		@SuppressWarnings("unchecked")
		List<Visualizacao> result = q.getResultList();
		return result;
	}
	
	//	quantidade de filmes que o usuario X assistiu do genero X	
	
	public int consultarVisualizacaoUsuarioPorGenero(String usuario, String genero) {
		Query q = manager.createQuery("SELECT count(v) FROM Visualizacao v, IN(v.filme.generos) g "
				+ "WHERE v.usuario.nome LIKE ?1 AND g.nome LIKE ?2");
		q.setParameter(1, usuario);
		q.setParameter(2, genero);
		int result = ((Long) q.getSingleResult()).intValue();
		return result;
	}
	
	 
}
