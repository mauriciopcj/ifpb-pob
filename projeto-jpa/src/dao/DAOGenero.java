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

import modelo.Genero;

public class DAOGenero  extends DAO<Genero>{
	
	public Genero read (Object chave) {
		
		try{
			String nome = (String) chave;
			Query q = manager.createQuery("select g from Professor g where g.nome= '" + nome +"'");
			return (Genero) q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                                TODAS AS CONSULTAS DE GENERO  //
	
//	public List<Genero> consultarGeneroPorNome(String prefixo) {
//		Query q = manager.query();
//		q.constrain(Genero.class);
//		q.descend("nome").constrain(prefixo).contains();
//		q.descend("nome").orderAscending();
//		List<Genero> result = q.execute(); 
//		return result;
//	}
//	
//	public List<Genero> consultarGeneroPorFilme(String prefixo) {
//		Query q = manager.query();
//		q.constrain(Genero.class);
//		q.descend("filmes").descend("titulo").constrain(prefixo).contains();
//		q.descend("nome").orderAscending();
//		List<Genero> result = q.execute(); 
//		return result;
//	}
//	
//	public List<Genero> consultarGeneroPorUsuario(String prefixo) {
//		Query q = manager.query();
//		q.constrain(Genero.class);
//		q.descend("filmes").descend("visualizacoes").descend("usuario").descend("nome").constrain(prefixo).contains();
//		q.descend("nome").orderAscending();
//		List<Genero> result = q.execute(); 
//		return result;
//	}
	
}
