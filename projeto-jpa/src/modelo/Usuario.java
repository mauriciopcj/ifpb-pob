/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", indexes = { @Index( name= "index_nome_usuario", columnList="nome" )})
public class Usuario {
	
	private String nome;
	private String email;
	private String senha;
	
	// MAPEAMENTO
	
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "DATE")
	private LocalDate dataNasc;
	
	@OneToMany(
			mappedBy = "usuario",
			fetch = FetchType.LAZY
			)
	private List<Visualizacao> visualizacoes = new ArrayList<Visualizacao>();

	// CONSTRUCTOR
	
	public Usuario() {}
	
	public Usuario(String nome, String email, String senha, LocalDate dataNasc){
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
	}
	
	//  ADD  //  REMOVE  //
	
	public void adicionar(Visualizacao v){
		this.visualizacoes.add(v);
	}
	
	public void remover(Visualizacao v){
		this.visualizacoes.remove(v);
	}
	
	public Visualizacao localizar(LocalDateTime data){
		for(Visualizacao v : visualizacoes) {
			if (v.getInicio().equals(data)) {
				return v;
			}
		}
		return null;
	}
	
	//  GETTERS AND SETTERS
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}

	public void setVisualizacoes(List<Visualizacao> visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
	
	// TO STRING
	
	public String toString() {
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "---------------------------------\n" +
				"Nome     | " + nome + 
				"\nEmail    | " + email + 
				"\nDataNasc | " + dataNasc.format(formatadorBarra) + 
				"\nSenha    | " + senha + 
				"\n---------------------------------";
	}

}
