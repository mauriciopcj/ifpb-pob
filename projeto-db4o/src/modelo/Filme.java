/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package modelo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Filme {
	
	private String titulo;
	private int ano;
	private String duracao;
	private byte[] capa;
	private String classificacao;
	private String descricao;
	private List<Genero> generos = new ArrayList<Genero>();
	private List<Visualizacao> visualizacoes = new ArrayList<Visualizacao>();
	
	public Filme(String titulo, int ano, String duracao, 
			BufferedImage bf, String classificacao, String descricao){
		this.titulo = titulo;
		this.ano = ano;
		this.duracao = duracao;
		this.classificacao = classificacao;
		this.descricao = descricao;
		setCapa(bf);
	}
	
	//  ADD  //  REMOVE  //  FIND  //
	
	public void adicionar(Genero g){
		this.generos.add(g);
	}
	
	public void adicionar(Visualizacao v){
		this.visualizacoes.add(v);
	}
	
	public void remover(Genero g){
		this.generos.remove(g);
	}
	
	public void remover(Visualizacao v){
		this.visualizacoes.remove(v);
	}
	
	public Genero localizar(String nome){
		for(Genero g : generos) {
			if (g.getNome().equals(nome)) {
				return g;
			}
		}
		return null;
	}
	
	public Visualizacao localizar(LocalDateTime data){
		for(Visualizacao v : visualizacoes) {
			if (v.getInicio().equals(data)) {
				return v;
			}
		}
		return null;
	}
	
	//  GETTERS E SETTERS
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getDuracao() {
		return duracao;
	}
	
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
	public BufferedImage getCapa() throws Exception{
		try {
			InputStream in = new ByteArrayInputStream(this.capa);
			BufferedImage bf = ImageIO.read(in);
			return bf;
		} catch (IOException e) {
			throw new Exception("leitura de arquivo invalida");
		}
	}
	
	public void setCapa(BufferedImage bf) {
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bf, "jpg", baos );
			baos.flush();
			this.capa = baos.toByteArray();	
			baos.close();
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	public String getClassificacao() {
		return classificacao;
	}
	
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}

	public void setVisualizacoes(List<Visualizacao> visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
	// TO STRING

	public String toString() {
		return "-----------------------------------" + 
				"\nTitulo        | " + this.titulo + 
				"\nClassificação | " + this.classificacao + 
				"\nAno           | " + this.ano + 
				"\nDuração       | " + this.duracao + 
				"\nGeneros       | " + (""+this.generos).replace("[","").replace("]","") + 
				"\n-----------------------------------";
	}
}
