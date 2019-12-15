/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class DAO<T> implements DAOInterface<T> {
	
	protected static EntityManager manager;
	protected static EntityManagerFactory factory;
	
	public DAO(){}

	 //////////////////////////////////////////////////////////////////////////
	//                                               ABRIR E FECHAR BANCO   //	
	
	public static void open(){	
		
		if(manager==null){
			HashMap<String,String> properties = new HashMap<String,String>();
			factory = Persistence.createEntityManagerFactory("filmes", properties);
			manager = factory.createEntityManager();
		}
	}
	
	public static void close(){
		if(manager != null) {
			manager.close();
			factory.close();
		}
	}

	 //////////////////////////////////////////////////////////////////////////
	//                                                               CRUD   // 

	public void create(T obj){
		manager.persist( obj );
	}

	public abstract T read(Object chave);

	public T update(T obj){
		manager.merge(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.remove(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query query = manager.createQuery("select x from " + type.getSimpleName() + " x");
		return (List<T>) query.getResultList();
	}

	 //////////////////////////////////////////////////////////////////////////
	//                                                          TRANSA��O   //
	
	public static void begin(){
		if(!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}
	
	public static void commit(){
		if(manager.getTransaction().isActive()){
			manager.getTransaction().commit();
			manager.clear();		// ---- esvaziar o cache de objetos
		}
	}
	
	public static void rollback(){
		if(manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}

}
