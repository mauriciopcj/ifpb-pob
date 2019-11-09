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

public interface DAOInterface<T> {
	public void create(T obj);
	public T read(Object chave);
	public T update(T obj);
	public void delete(T obj) ;
	public List<T> readAll();
}
