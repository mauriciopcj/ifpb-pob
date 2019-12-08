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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD
@Table(name = "genero", indexes = { @Index( name= "index_nome_genero", columnList="nome" )})
=======
@Table( indexes = @Index( columnList = "nome" ))
>>>>>>> 66b93d0b292d7f52f832756eac007dfd98a681d5
public class Genero{
	
	private String nome;
	
	//MAPEAMENTO
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany
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
