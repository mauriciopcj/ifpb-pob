/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@Table( indexes = @Index( columnList = "usuario" ))
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Visualizacao{
	
	// MAPEAMENTO
	
	@Id		
	@GeneratedValue
	@Field(name="_id")
	private String id;
	
//	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime data;
	
	@ManyToOne
	private Filme filme;
	
	@ManyToOne
	private Usuario usuario;
	
	// CONSTRUCTOR

	public Visualizacao() {}
	
	public Visualizacao(Filme filme, Usuario usuario) {
		this.data = LocalDateTime.now();
		this.filme = filme;
		this.usuario = usuario;
	}

	// GETTERS AND SETTERS

	public LocalDateTime getInicio() {
		return data;
	}

	public void setInicio(LocalDateTime inicio) {
		this.data = inicio;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	// TO STRING
	
	@Override
	public String toString() {
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return "-----------------------------\nData    | " + data.format(formatadorBarra) + 
				"\nFilme   | " + filme.getTitulo() + "\nUsuario | " + usuario.getNome() + 
				"\nVersão  | " + filme.getVersao() +
				"\n-----------------------------";
	}

}
