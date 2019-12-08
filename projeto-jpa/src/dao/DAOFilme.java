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

public class DAOFilme  extends DAO<Filme> {
	
	public Filme read (Object chave) {
		try{
			String titulo = (String) chave;
			Query q = manager.createQuery("select f from Filme f where f.titulo = '" + titulo +"'");
			return (Filme) q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                                 TODAS AS CONSULTAS DE FILME  //

<<<<<<< HEAD
	public List<Filme> consultarFilmePorTitulo(String prefixo) {
		Query q = manager.createQuery("SELECT f FROM Filme f WHERE f.titulo LIKE ?1");
		q.setParameter(1, "%"+prefixo+"%");
		@SuppressWarnings("unchecked")
		List<Filme> result = q.getResultList();
		return result;
	}
	
	public List<Filme> consultarFilmePorGenero(String prefixo) {
		Query q = manager.createQuery("SELECT f FROM Filme f, IN (f.generos) g WHERE g.nome LIKE ?1");
		q.setParameter(1, "%"+prefixo+"%");
		@SuppressWarnings("unchecked")
		List<Filme> result = q.getResultList();
		return result;
	}
=======
//	public List<Filme> consultarFilmePorTitulo(String prefixo) {
//		Query q = manager.createQuery();
//		q.constrain(Filme.class);
//		q.descend("titulo").constrain(prefixo).contains();
//		List<Filme> result = q.execute(); 
//		return result;
//	}
//	
//	public List<Filme> consultarFilmePorGenero(String prefixo) {
//		Query q = manager.query();
//		q.constrain(Filme.class);
//		q.descend("generos").descend("nome").constrain(prefixo).contains();
//		List<Filme> result = q.execute(); 
//		return result;
//	}
>>>>>>> 66b93d0b292d7f52f832756eac007dfd98a681d5
	
}
