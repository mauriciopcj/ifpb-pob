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

import modelo.Usuario;

public class DAOUsuario  extends DAO<Usuario> {
	
	public Usuario read (Object chave) {
		try{
			String nome = (String) chave;
			Query q = manager.createQuery("select u from Usuario u where u.nome= '" + nome +"'");
			return (Usuario) q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
}
