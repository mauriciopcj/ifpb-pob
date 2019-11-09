/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package fachada;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import dao.DAO;
import dao.DAOFilme;
import dao.DAOGenero;
import dao.DAOUsuario;
import dao.DAOVisualizacao;
import modelo.Filme;
import modelo.Genero;
import modelo.Usuario;
import modelo.Visualizacao;

public class Fachada {
	
	private static DAOFilme daofilme = new DAOFilme();
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOGenero daogenero = new DAOGenero();
	private static DAOVisualizacao daovisualizacao = new DAOVisualizacao();
	private static String userlogado = "Mauricio";


	public static String getUserlogado() {
		return userlogado;
	}
	
	public static void setUserlogado(String userlogado) {
		Fachada.userlogado = userlogado;
	}
	
	public static void inicializar(){
		DAO.open();
	}
	
	public static void finalizar(){
		DAO.close();
	}

	///////////////////////////////////////////////////////////////////////////////////
	//                                                         CADASTRANDO OBJETOS  //
	
	public static Filme cadastrarFilme(String titulo) throws  Exception {
		DAO.begin();	
		Filme f = daofilme.read(titulo);
		if(f != null) {
			throw new Exception("Cadastrar Filme - filme ja cadastrado: " + titulo);
		}
		HashMap<String,String> dados = dadosFilme(titulo);
		
		f = new Filme(dados.get("Title"), Integer.parseInt(dados.get("Year")), dados.get("Runtime"),
				imagem(dados.get("Poster")), dados.get("Value"), dados.get("Plot"));
		
        daofilme.create(f);	
		DAO.commit();
		
        String[] generos = dados.get("Genre").split(",");
        
        for(String g: generos) {
        	adicionarGeneroFilme(f.getTitulo(),g.trim());
        }
        
		return f;
	}	
	
	public static Usuario cadastrarUsuario(String nome, String email, String senha, 
			LocalDate dataNasc) throws  Exception{
		DAO.begin();	
		Usuario u = daousuario.read(nome);
		if(u != null) {
			throw new Exception("Cadastrar Usuario - usuario ja cadastrado:" + nome);
		}
		  
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();
         
		u = new Usuario(nome, email, senhahex, dataNasc);
		daousuario.create(u);	
		DAO.commit();
		return u;
	}	

	public static Genero cadastrarGenero(String nome) throws  Exception{
		DAO.begin();	
		Genero g = daogenero.read(nome);
		if(g != null) {
			throw new Exception("Cadastrar Genero - genero ja cadastrado:" + nome);
		}
		g = new Genero(nome);
		daogenero.create(g);	
		DAO.commit();
		return (Genero) g;
	}
	
	public static Visualizacao cadastrarVisualizacao(String titulo) throws  Exception{
		DAO.begin();
		Filme f = daofilme.read(titulo);
		if(f == null) {
			throw new Exception("Cadastrar Visualizaçao - filme nao cadastrado: " + titulo);
		}
		Usuario u = daousuario.read(userlogado);
		if(u == null) {
			throw new Exception("Cadastrar Visualiza��o - usuario nao cadastrado:" + userlogado);
		}
		Visualizacao v = new Visualizacao(f, u);
		daovisualizacao.create(v);
		DAO.commit();
		DAO.begin();
		f.adicionar(v);
		u.adicionar(v);
		daofilme.update(f);
		daousuario.update(u);
		DAO.commit();
		return v;
	}

	///////////////////////////////////////////////////////////////////////////////////
	//                                                          ADICIONANDO OBJETOS //
	
	public static Genero adicionarGeneroFilme(String titulo, String nome) 
			throws  Exception{
		DAO.begin();	
		Filme f = daofilme.read(titulo);
		if(f == null) {
			throw new Exception("Adicionar Genero - filme nao cadastrada:" + titulo);
		}
		Genero g = daogenero.read(nome);
		if(g == null) {
			g = cadastrarGenero(nome);
		}
		f.adicionar(g);
		g.adicionar(f);
		daofilme.update(f);
		daogenero.update(g);
		DAO.commit();
		return g;
	}
	
	public static Filme adicionarFilmeGenero(String nome, String titulo) 
			throws  Exception{
		DAO.begin();	
		Genero g = daogenero.read(nome);
		if(g == null) {
			throw new Exception("Adicionar Filme - genero nao cadastrada:" + nome);
		}
		Filme f = daofilme.read(titulo);
		if(f == null) {
			throw new Exception("Adicionar Filme - filme nao cadastrada:" + nome);
		}
		g.adicionar(f);
		f.adicionar(g);
		daofilme.update(f);
		daogenero.update(g);	
		DAO.commit();
		return f;
	}
	
//	public static void adicionarVisualizacaoFilmeUsuario(String titulo, LocalDateTime data, String nome) throws Exception {
//		DAO.begin();
//		Filme f = daofilme.read(titulo);
//		if(f == null) {
//			throw new Exception("Adicionar Genero - filme nao cadastrada:" + titulo);
//		}
//		Usuario u = daousuario.read(nome);
//		if(u == null) {
//			throw new Exception("Adicionar Genero - filme nao cadastrada:" + nome);
//		}
//		Visualizacao v = daovisualizacao.read(data);
//		if(v == null) {
//			throw new Exception("Adicionar Genero - filme nao cadastrada:" + data);
//		}
//		f.adicionar(v);
//		u.adicionar(v);
//		daofilme.update(f);
//		daousuario.update(u);
//		DAO.commit();
//	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                                            EXCLUINDO OBJETOS //
	
	public static Genero excluirGeneroFilme(String titulo, String nome) 
			throws  Exception{
		DAO.begin();	
		Filme f = daofilme.read(titulo);
		if(f == null) {
			throw new Exception("Excluir Genero - filme nao cadastrada:" + titulo);
		}
		Genero g = daogenero.read(nome);
		if(g == null) {
			throw new Exception("Excluir Genero - genero nao cadastrado:" + nome);
		}
		g = f.localizar(nome);
		if(g == null) {
			throw new Exception("Excluir Genero - filme nao possui este genero:" + nome);
		}
		f.remover(g);
		g.remover(f);
		daogenero.update(g);
		daofilme.update(f);
		DAO.commit();
		return g;
	}
	
	public static void excluirFilme(String titulo) throws Exception {
		DAO.begin();
		Filme f = daofilme.read(titulo);
		if (f == null) {
			throw new Exception("Excluir Filme - titulo inexistente:" + titulo);
		}
		for(Genero g : f.getGeneros()) {
			g.remover(f);
			daogenero.update(g);
		}
		daofilme.delete(f);
		DAO.commit();
	}

	public static Filme excluirFilmeGenero(String nome, String titulo) 
			throws  Exception{
		DAO.begin();	
		Genero g = daogenero.read(nome);
		if(g == null) {
			throw new Exception("Excluir Filme - genero nao cadastrada:" + nome);
		}
		Filme f = daofilme.read(titulo);
		if(f == null) {
			throw new Exception("Excluir Filme - filme nao cadastrado:" + titulo);
		}
		f = g.localizar(titulo);
		if(f == null) {
			throw new Exception("Excluir Filme - genero nao possui este genero:" + titulo);
		}
		g.remover(f);
		daogenero.update(g);
		DAO.commit();
		return f;
	}
	
	public static void excluirGenero(String nome) throws Exception {
		DAO.begin();
		Genero g = daogenero.read(nome);
		if (g == null) {
			throw new Exception("Excluir Genero - nome inexistente:" + nome);
		}
		for(Filme f : g.getFilmes()) {
			f.remover(g);
			daofilme.update(f);
		}
		daogenero.delete(g);
		DAO.commit();
	}
	
	public static void excluirUsuario(String nome) throws Exception {
		DAO.begin();
		Usuario u = daousuario.read(nome);
		if (u == null) {
			throw new Exception("Excluir Usuario - nome inexistente:" + nome);
		}
		List<Visualizacao> visualizacoes = daovisualizacao.readAll();
		for(int i = daovisualizacao.readAll().size()-1; i >= 0; i--) {
			
			Filme f = visualizacoes.get(i).getFilme();
			f.remover(visualizacoes.get(i));
			daofilme.update(f);
		}
		daousuario.delete(u);
		DAO.commit();
	}
	
	public static void excluirVisualizacoes(String nome) throws Exception {
		DAO.begin();
		List<Visualizacao> visualizacoes = daovisualizacao.readAll();
		if (visualizacoes == null) {
			throw new Exception("Excluir Visualizacoes - usuario inexistente:" + nome);
		}
		for(int i = visualizacoes.size() - 1; i >= 0; i--) {
			daovisualizacao.delete(visualizacoes.get(i));
		}
//		for(Visualizacao v : visualizacoes) {
//			daovisualizacao.delete(v);
//		}
		DAO.commit();
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                                            ALTERANDO OBJETOS //
	
	public static void alterarTituloFilme(String titulo, String novotitulo) throws Exception{
		DAO.begin();
		Filme f = daofilme.read(titulo);
		if (f==null) {
			throw new Exception("Alterar titulo do filme - filme inexistente:" + titulo);
		}
		f.setTitulo(novotitulo);			
		f = daofilme.update(f);
		DAO.commit();
	}

	public static void alterarNomeGenero(String nome, String novonome) throws Exception{
		DAO.begin();		
		Genero g1 = daogenero.read(nome);	
		if (g1 == null) {
			throw new Exception("Alterar nome do genero - genero inexistente:" + nome);
		}
		Genero t2 = daogenero.read(novonome);	
		if (t2 != null) {
			throw new Exception("Alterar nome do genero - novo nome ja existe:" + novonome);
		}
		g1.setNome(novonome); 			
		g1 = daogenero.update(g1);     	
		DAO.commit();	
	}
	
	public static void alterarNomeUsuario(String nome, String novonome) throws Exception{
		DAO.begin();
		Usuario u = daousuario.read(nome);
		if (u == null) {
			throw new Exception("Alterar nome do usuario - usuario inexistente:" + nome);
		}
		u.setNome(novonome);			
		u = daousuario.update(u);
		DAO.commit();
	}
	
	public static void alterarSenhaUsuario(String nome, String novasenha) throws Exception{
		DAO.begin();
		Usuario u = daousuario.read(nome);
		if (u == null) {
			throw new Exception("Alterar senha do usuario - usuario inexistente:" + nome);
		}
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(novasenha.getBytes("UTF-8"));
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();
        
		u.setSenha(senhahex);			
		u = daousuario.update(u);
		DAO.commit();
	}

	///////////////////////////////////////////////////////////////////////////////////
	//                                                                   LISTAGENS  //
	
	public static String listarFilmes(){
		List<Filme> filmes = daofilme.readAll();
		String texto = "\n[ FILMES ]\n";
		for (Filme f : filmes) {
			texto += "\n" + f;
		}
		return texto;
	}

	public static List<Filme> listarFilmesObj(){
		List<Filme> filmes = daofilme.readAll();
		return filmes;
	}
	
	public static String listarGeneros(){
		List<Genero> generos = daogenero.readAll();
		String texto = "\n[ GENEROS ]\n";
		for (Genero g : generos) {
			texto += "\n" + g;
		}
		return texto;
	}

	public static String listarUsuarios() { 	
		List<Usuario> usuarios = daousuario.readAll();
		String texto = "\n[ USUARIOS ]\n";
		for(Usuario u: usuarios) {
			texto += "\n" + u; 
		}
		return texto;
	}
	
	public static String listarVisualizacoes() { 	
		List<Visualizacao> visualizacoes = daovisualizacao.readAll();
		String texto = "\n[ VISUALIZACOES ]\n";
		for(Visualizacao v: visualizacoes) {
			texto += "\n" + v; 
		}
		return texto;
	}

	///////////////////////////////////////////////////////////////////////////////////
	//                                                                   CONSULTAS  //
	
	/*
	public static String consultarFilmePorTitulo(String n) {			// FILME > TITULO
		List<Filme> result = daofilme.consultarFilmePorTitulo(n);
		String texto = "\nConsultar filmes em que o titulo tenha [ " + n + " ]\n";
		if (result.isEmpty()) {  
			texto += "\nConsulta vazia";
		}else {
			for(Filme f: result) texto += "\n" + f;
		}
		return texto;
	}
	
	public static String consultarFilmePorGenero(String n) {		// FILME > GENERO
		List<Filme> result = daofilme.consultarFilmePorGenero(n);
		String texto = "\nConsultar filmes de [ " + n + " ]\n";
		if (result.isEmpty()) {  
			texto += "\nConsulta vazia";
		}else {
			for(Filme f: result) texto += "\n" + f;
		}
		return texto;
	}
	
	public static String consultarGeneroPorNome(String n) {			// GENERO > NOME		
		List<Genero> result = daogenero.consultarGeneroPorNome(n);
		String texto = "\nConsultar generos em que o nome tenha [ " + n + " ]\n";
		if (result.isEmpty()) { 
			texto += "\nConsulta vazia";
		} else {
			for(Genero g: result) texto += "\n" + g;
		}
		return texto;
	}
	
	public static String consultarGeneroPorFilme(String filme) {		// GENERO > FILME
		List<Genero> result = daogenero.consultarGeneroPorFilme(filme);
		String texto = "\nConsultar generos do filme [ " + filme + " ]\n";
		if (result.isEmpty()) {
			texto += "\nConsulta vazia";
		}else { 
			for(Genero g: result) texto += "\n" + g;
		}
		return texto;
	}
	
	public static String consultarGeneroMaisAssistido() {		// GENERO > FILME
		List<Genero> result = daogenero.consultarGeneroPorUsuario(getUserlogado());
		String texto = "\nConsultar generos de filmes mais visualizados por [ " + getUserlogado() + " ]\n";
		if(result.isEmpty()) {
			texto += "\nConsulta vazia";
		} else {
			List<Integer> aux = new ArrayList<Integer>();
			for(Genero g: result) {
				int quant = daovisualizacao.consultarVisualizacaoUsuarioPorGenero(getUserlogado(),g.getNome());
				aux.add(quant);
			}
			int maior = aux.get(0);
			String gen = result.get(0).getNome();
			for(int i = 1; i < result.size(); i++) {
				if(aux.get(i) > maior) {
					maior = aux.get(i);
					gen = result.get(i).getNome();
				} else if(aux.get(i) == maior && !result.get(i).getNome().equals(gen)) {
					gen += ", " + result.get(i).getNome();
				}
			}
			texto += "\nGenero: " + gen + "\nQuantidade: " + maior;
		}
		return texto;
	}
	
	public static String consultarVisualizacaoUsuarioPorGenero(String usuario,String genero) {
		int result = daovisualizacao.consultarVisualizacaoUsuarioPorGenero(usuario,genero);
		String texto = "\nConsultar quantos filmes de [" + genero + "] [ " + usuario + " ] assitiu\n";
		if (result == 0) {
			texto += "\nConsulta vazia";
		}else { 
			texto += "\n" + result;
		}
		return texto;
	}
	
	public static String consultarVisualizacaoPorUsuario(String usuario) { // VISUALIZACAO > USUARIO
		List<Visualizacao> result = daovisualizacao.consultarVisualizacaoPorUsuario(usuario);
		String texto = "\nConsultar visualizacoes de [ " + usuario + " ]\n";
		if (result.isEmpty()) {
			texto += "\nConsulta vazia";
		}else { 
			for(Visualizacao v: result) texto += "\n" + v;
		}
		return texto;
	}
	
	public static String consultarVisualizacaoPorFilme(String n) { // VISUALIZACAO > FILME
		List<Visualizacao> result = daovisualizacao.consultarVisualizacaoPorFilme(n);
		String texto = "\nConsultar visualizacoes do filme [ " + n + " ]\n";
		if (result.isEmpty()) {
			texto += "\nConsulta vazia";
		}else { 
			for(Visualizacao v: result) texto += "\n" + v;
		}
		return texto;
	}
	*/
	
	///////////////////////////////////////////////////////////////////////////////////
	//                                                                       UTILS  //
	
	public static Filme encontrarFilme(String titulo) throws Exception {
		Filme f = daofilme.read(titulo);
		if(f == null) {
			throw new Exception("Cadastrar Filme - filme ja cadastrado: " + titulo);
		}
		return f;
	}
	
	public static BufferedImage pegarCapa(String titulo) throws Exception {
		Filme f = daofilme.read(titulo);
		if(f == null) {
			throw new Exception("Cadastrar Filme - filme ja cadastrado: " + titulo);
		}
		return f.getCapa();
	}
	
	public static boolean checkSenha(String nome, String senha) throws Exception {
		Usuario u = daousuario.read(nome);
		if (u == null) {
			throw new Exception("Login - dados invalidos:");
		}
		
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();
        
		if(u.getSenha().equals(senhahex)) {
			return true;
		} else {
			throw new Exception("Login - dados invalidos:");
		}
	}
	
	public static BufferedImage imagem(String url) throws Exception {
		
		BufferedImage image = ImageIO.read(new URL(url));
		return image;
		
	}
	
	public static HashMap<String,String> dadosFilme(String filme) throws Exception {
		
		URL url2 = new URL("http://www.omdbapi.com/?apikey=988c6f75&t=" + filme.replace(" ", "%20"));
		URLConnection conexao = url2.openConnection(); InputStream input = conexao.getInputStream();
        Scanner scan = new Scanner(input);
        
        String textojson = ""; String linha;
        
        while (scan.hasNext())  {
            linha = scan.nextLine(); linha = linha.trim(); textojson += linha;
        }
        scan.close();
        
        HashMap<String,String> mapa = new HashMap<String, String>();
        String key,value;
        Matcher matcher = Pattern.compile("\"\\D.*?\":\"(https://)?.*?\"").matcher(textojson);
        while (matcher.find()) {
            String[] par = matcher.group().split(":");
            key = par[0].replaceAll("\"", "").trim();
            if(key.equals("Poster")) {
            	value = par[1].replaceAll("\"", "").trim() + ":" + par[2].replaceAll("\"", "").trim();
            } else {
            	value = par[1].replaceAll("\"", "").trim();
            }
            mapa.put(key, value);
        }
        
//        Filme f = new Filme(mapa.get("Title"), Integer.parseInt(mapa.get("Year")), mapa.get("Runtime"),
//				imagem(mapa.get("Poster")), mapa.get("Value"), mapa.get("Plot"));
//        
//        String[] generos = mapa.get("Genre").split(",");
//        
//        for(String g: generos) {
//        	cadastrarGenero(g);
//        	adicionarGeneroFilme(f.getTitulo(),g);
//        }
        
		return mapa;
	}
}
