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
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;
import modelo.Filme;
import modelo.Genero;
import modelo.Usuario;

public abstract class DAO<T> implements DAOInterface<T> {
	
	protected static ObjectContainer manager;

	public static void open(){	
		if(manager == null){		
			abrirBancoLocal();							//  BANCO LOCAL
			//abrirBancoServidor();						//  OU SERVIDOR
		}
	}
	
	 //////////////////////////////////////////////////////////////////////////
	//                                                        BANCO LOCAL   //
	
	public static void abrirBancoLocal(){
		
		//Backup.criar("banco.db4o");					//  CRIA BACKUP
		//new File("banco.db4o").delete();				//  APAGA BANCO
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration();
		config.common().messageLevel(0);  // 0,1,2,3...
		
		config.common().objectClass(Filme.class).cascadeOnUpdate(true);
		config.common().objectClass(Filme.class).cascadeOnActivate(true);
		config.common().objectClass(Genero.class).cascadeOnUpdate(true);
		config.common().objectClass(Genero.class).cascadeOnActivate(true);
		config.common().objectClass(Usuario.class).cascadeOnUpdate(true);
		config.common().objectClass(Usuario.class).cascadeOnActivate(true);
		config.common().objectClass(Usuario.class).cascadeOnDelete(true);

		config.common().objectClass(Filme.class).objectField("titulo").indexed(true);	// INDICES
		config.common().objectClass(Genero.class).objectField("nome").indexed(true);
		config.common().objectClass(Usuario.class).objectField("nome").indexed(true);
		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
	}
	
	 //////////////////////////////////////////////////////////////////////////
	//                                                     BANCO SERVIDOR   //
	
	public static void abrirBancoServidor(){
		
		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);   //0,1,2,3,4
		
		config.common().objectClass(Filme.class).cascadeOnUpdate(true);
		config.common().objectClass(Filme.class).cascadeOnActivate(true);
		config.common().objectClass(Genero.class).cascadeOnUpdate(true);
		config.common().objectClass(Genero.class).cascadeOnActivate(true);
		config.common().objectClass(Usuario.class).cascadeOnUpdate(true);
		config.common().objectClass(Usuario.class).cascadeOnActivate(true);

		config.common().objectClass(Filme.class).objectField("titulo").indexed(true);	// INDICES
		config.common().objectClass(Genero.class).objectField("nome").indexed(true);
		config.common().objectClass(Usuario.class).objectField("nome").indexed(true);

		manager = Db4oClientServer.openClient(config,"10.0.4.179",34000,"usuario1","senha1");
												  // "10.0.4.179" <=> localhost
	}

	public static void close(){
		if(manager != null) {
			manager.close();
			manager = null;
		}
	}

	 //////////////////////////////////////////////////////////////////////////
	//                                                               CRUD   // 

	public void create(T obj){
		manager.store( obj );
	}

	public abstract T read(Object chave);

	public T update(T obj){
		manager.store(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}

	 //////////////////////////////////////////////////////////////////////////
	//                                                          TRANSAÇÃO   //
	
	public static void begin(){		// DEIXAR VAZIO
	}		

	public static void commit(){
		manager.commit();
	}
	
	public static void rollback(){
		manager.rollback();
	}

}
