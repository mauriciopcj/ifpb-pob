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

public class Usuario {
	
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNasc;
	private int vicioLVL = 0;
	private List<Visualizacao> visualizacoes = new ArrayList<Visualizacao>();

	// CONSTRUCTOR
	
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
	
	public int getVicioLVL() {
		return vicioLVL;
	}

	public void setVicioLVL(int vicioLVL) {
		this.vicioLVL = vicioLVL;
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
