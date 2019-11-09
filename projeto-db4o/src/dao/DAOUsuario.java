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
import modelo.Usuario;

public class DAOUsuario  extends DAO<Usuario> {
	
	public Usuario read (Object chave) {
		String titulo = (String) chave;
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("nome").constrain(titulo);
		List<Usuario> resultados = q.execute();
		if (resultados.size() > 0) {
			return resultados.get(0);
		} else {
			return null;
		}
	}
}
