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

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Filme;
import modelo.Genero;

public class DAOGenero  extends DAO<Genero>{
	
	public Genero read (Object chave) {
		
		try{
			String nome = (String) chave;
			Query q = manager.createQuery("SELECT g FROM Genero g WHERE g.nome= '" + nome +"'");
			return (Genero) q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                                TODAS AS CONSULTAS DE GENERO  //
	
	public List<Genero> consultarGeneroPorNome(String prefixo) {
		Query q = manager.createQuery("SELECT g FROM Genero g WHERE g.nome LIKE ?1 ORDER BY g.nome");
		q.setParameter(1, "%"+prefixo+"%");
		@SuppressWarnings("unchecked")
		List<Genero> result = q.getResultList();
		return result;
	}
	
	public List<Genero> consultarGeneroPorFilme(String prefixo) {
		Query q = manager.createQuery("SELECT f FROM Filme f WHERE f.titulo LIKE ?1");
		q.setParameter(1, prefixo);
		Filme f = (Filme) q.getSingleResult();
		q = manager.createNativeQuery("db.GENERO.aggregate([{ $match: {FILMES__id:'"+f.getId()+"'}}])", Genero.class);
		
//		Query q = manager.createQuery("SELECT g FROM Genero g, IN(g.filmes) f WHERE f.titulo LIKE ?1 ORDER BY g.nome");
//		q.setParameter(1, "%"+prefixo+"%");
		@SuppressWarnings("unchecked")
		List<Genero> result = q.getResultList();
		return result;
	}
	
	public List<Genero> consultarGeneroPorUsuario(String prefixo) {
		Query q = manager.createQuery("SELECT g FROM Genero g, IN(g.filmes) f, IN(f.visualizacoes) v "
				+ "WHERE v.usuario.nome LIKE ?1 ORDER BY g.nome");
		q.setParameter(1, "%"+prefixo+"%");
		@SuppressWarnings("unchecked")
		List<Genero> result = q.getResultList();
		return result;
	}
	
}
