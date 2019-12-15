/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@Table(name = "genero", indexes = { @Index( name= "index_nome_genero", columnList="nome" )})
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Genero{
	
	private String nome;
	
	//MAPEAMENTO
	
	@Id		
	@GeneratedValue
	@Field(name="_id")
	private String id;
	
	@OneToMany(mappedBy="generos",fetch = FetchType.LAZY)
	private List<Filme> filmes = new ArrayList<Filme>();
	
	// CONSTRUTOR
	
	public Genero(){}
	
	public Genero(String n){
		this.nome = n;
	}

	//  ADD  //  REMOVE  //  FIND  //
	
	public void adicionar(Filme f){
		this.filmes.add(f);
	}
	
	public void remover(Filme f){
		this.filmes.remove(f);
	}
	
	public Filme localizar(String titulo){
		for(Filme f : filmes) {
			if (f.getTitulo().equals(titulo)) {
				return f;
			}
		}
		return null;
	}
	
	//  GETTERS E SETTERS
	
	public String getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String n) {
		this.nome = n;
	}
	
	public List<Filme> getFilmes() {
		return this.filmes;
	}
	
	public void setFilme(ArrayList<Filme> filmes){
		this.filmes = filmes;
	}
	
	// TO STRING
	
	@Override
	public String toString() {
		return "[" + nome + "]";
	}
	
}
