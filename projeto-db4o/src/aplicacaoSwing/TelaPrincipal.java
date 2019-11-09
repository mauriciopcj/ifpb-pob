/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package aplicacaoSwing;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
	private JPanel contentPane;
	private JButton btnCriar;
	private JButton btnListarGeneros;
	private JButton btnListarVisualizacoes;
	private JButton btnAssistirFilme;
	private JTextArea textArea;
	private JButton btnCadastrarGenero;
	private JButton btnListarUsuarios;
	private JButton btnCadastrarUsuario;
	private JButton btnCadastrarFilme;

	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaListagem window = new TelaListagem();
	//					window.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {

		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		setTitle("Tela Principal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((width-905)/2, (heigth-437)/2, 905, 437);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(204, 204, 255)));
		panel_1.setBounds(10, 12, 160, 185);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblFilmes = new JLabel("FILMES");
		lblFilmes.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilmes.setBounds(10, 10, 140, 25);
		panel_1.add(lblFilmes);

		btnCriar = new JButton("listar");
		btnCriar.setBounds(10, 45, 140, 25);
		panel_1.add(btnCriar);
		btnCriar.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCriar.setForeground(new Color(255, 255, 255));
		btnCriar.setBackground(new Color(51, 102, 204));

		btnCadastrarFilme = new JButton("cadastrar");
		btnCadastrarFilme.setBounds(10, 80, 140, 25);
		panel_1.add(btnCadastrarFilme);
		btnCadastrarFilme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String titulo = JOptionPane.showInputDialog("digite o titulo do Filme");
					if(titulo != null) {
						Fachada.cadastrarFilme(titulo);
					}
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCadastrarFilme.setForeground(Color.WHITE);
		btnCadastrarFilme.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCadastrarFilme.setBackground(new Color(51, 102, 204));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = JOptionPane.showInputDialog("Digite o titulo do Filme");
				try {
					Fachada.excluirFilme(titulo);
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnExcluir.setBackground(new Color(51, 102, 204));
		btnExcluir.setBounds(10, 115, 140, 25);
		panel_1.add(btnExcluir);
		
				btnAssistirFilme = new JButton("Assistir");
				btnAssistirFilme.setBounds(10, 150, 140, 25);
				panel_1.add(btnAssistirFilme);
				btnAssistirFilme.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFrame a = new TelaFilme();
						a.setVisible(true);
					}
				});
				btnAssistirFilme.setForeground(Color.WHITE);
				btnAssistirFilme.setFont(new Font("Dialog", Font.PLAIN, 14));
				btnAssistirFilme.setBackground(new Color(51, 102, 204));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(204, 204, 255)));
		panel_2.setLayout(null);
		panel_2.setBounds(354, 12, 160, 185);
		contentPane.add(panel_2);

		JLabel lblGeneros = new JLabel("GENEROS");
		lblGeneros.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneros.setBounds(10, 10, 140, 25);
		panel_2.add(lblGeneros);

		btnCadastrarGenero = new JButton("cadastrar");
		btnCadastrarGenero.setBounds(10, 80, 140, 25);
		panel_2.add(btnCadastrarGenero);
		btnCadastrarGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String nome = JOptionPane.showInputDialog("digite o nome do Genero");
					if(nome != null) {
						Fachada.cadastrarGenero(nome);
					}
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCadastrarGenero.setForeground(Color.WHITE);
		btnCadastrarGenero.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCadastrarGenero.setBackground(new Color(51, 102, 204));

		btnListarGeneros = new JButton("listar");
		btnListarGeneros.setBounds(10, 45, 140, 25);
		panel_2.add(btnListarGeneros);
		btnListarGeneros.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnListarGeneros.setForeground(new Color(255, 255, 255));
		btnListarGeneros.setBackground(new Color(51, 102, 204));
		
		JButton button_1 = new JButton("Excluir");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog("Digite o nome do Genero");
				try {
					Fachada.excluirGenero(nome);
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		button_1.setBackground(new Color(51, 102, 204));
		button_1.setBounds(10, 114, 140, 25);
		panel_2.add(button_1);
		btnListarGeneros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{

					textArea.setText(Fachada.listarGeneros());
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(204, 204, 255)));
		panel_3.setLayout(null);
		panel_3.setBounds(182, 12, 160, 185);
		contentPane.add(panel_3);

		JLabel lblUsuarios_1 = new JLabel("USUARIOS");
		lblUsuarios_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios_1.setBounds(10, 10, 140, 25);
		panel_3.add(lblUsuarios_1);

		btnListarUsuarios = new JButton("listar");
		btnListarUsuarios.setBounds(10, 45, 140, 25);
		panel_3.add(btnListarUsuarios);
		btnListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					textArea.setText(Fachada.listarUsuarios());
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnListarUsuarios.setForeground(Color.WHITE);
		btnListarUsuarios.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnListarUsuarios.setBackground(new Color(51, 102, 204));

		btnCadastrarUsuario = new JButton("cadastrar");
		btnCadastrarUsuario.setBounds(10, 80, 140, 25);
		panel_3.add(btnCadastrarUsuario);
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroUsuario();
			}
		});
		btnCadastrarUsuario.setForeground(Color.WHITE);
		btnCadastrarUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCadastrarUsuario.setBackground(new Color(51, 102, 204));
		
		JButton button = new JButton("Excluir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog("Digite o nome do usuário");
				try {
					Fachada.excluirUsuario(nome);
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 14));
		button.setBackground(new Color(51, 102, 204));
		button.setBounds(10, 115, 140, 25);
		panel_3.add(button);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(204, 204, 255)));
		panel_4.setLayout(null);
		panel_4.setBounds(354, 209, 160, 186);
		contentPane.add(panel_4);

		JLabel lblVisualizaes = new JLabel("VISUALIZA\u00C7\u00D5ES");
		lblVisualizaes.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizaes.setBounds(10, 10, 140, 25);
		panel_4.add(lblVisualizaes);

		btnListarVisualizacoes = new JButton("listar");
		btnListarVisualizacoes.setBounds(10, 45, 140, 25);
		panel_4.add(btnListarVisualizacoes);
		btnListarVisualizacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					textArea.setText(Fachada.listarVisualizacoes());
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnListarVisualizacoes.setForeground(Color.WHITE);
		btnListarVisualizacoes.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnListarVisualizacoes.setBackground(new Color(51, 102, 204));

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(526, 12, 364, 382);
		contentPane.add(scroll);
		scroll.setBorder(UIManager.getBorder("ScrollPane.border"));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		textArea = new JTextArea();
		textArea.setToolTipText("");
		textArea.setBorder(null);
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		scroll.setViewportView(textArea);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(204, 204, 255)));
		panel.setLayout(null);
		panel.setBounds(10, 209, 330, 186);
		contentPane.add(panel);

		JLabel lblConsulta = new JLabel("CONSULTA");
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setBounds(10, 10, 312, 25);
		panel.add(lblConsulta);

		JButton btnConsulta01 = new JButton("Filmes por parte do titulo");
		btnConsulta01.setToolTipText("Filmes por parte do titulo");
		btnConsulta01.setBounds(10, 45, 312, 25);
		panel.add(btnConsulta01);
		btnConsulta01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String titulo = JOptionPane.showInputDialog("digite parte do titulo");
					if(titulo != null) {
						textArea.setText(Fachada.consultarFilmePorTitulo(titulo));
					}
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnConsulta01.setForeground(Color.WHITE);
		btnConsulta01.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnConsulta01.setBackground(new Color(51, 102, 204));
		
		JButton btnFilmesDoGenero = new JButton("Filmes do genero");
		btnFilmesDoGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genero = JOptionPane.showInputDialog("digite o genero");
				if(genero != null) {
					textArea.setText(Fachada.consultarFilmePorGenero(genero));
				}

			}
		});
		btnFilmesDoGenero.setToolTipText("Filmes do genero");
		btnFilmesDoGenero.setForeground(Color.WHITE);
		btnFilmesDoGenero.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnFilmesDoGenero.setBackground(new Color(51, 102, 204));
		btnFilmesDoGenero.setBounds(10, 80, 312, 25);
		panel.add(btnFilmesDoGenero);
		
		JButton btnQuantidadeDeFilmes = new JButton("Quantidade de filmes por genero");
		btnQuantidadeDeFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genero = JOptionPane.showInputDialog("digite o genero");
				if(genero != null) {
					textArea.setText(Fachada.consultarVisualizacaoUsuarioPorGenero(Fachada.getUserlogado(),genero));
				}
			}
		});
		btnQuantidadeDeFilmes.setToolTipText("Quantidade de filmes por genero");
		btnQuantidadeDeFilmes.setForeground(Color.WHITE);
		btnQuantidadeDeFilmes.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnQuantidadeDeFilmes.setBackground(new Color(51, 102, 204));
		btnQuantidadeDeFilmes.setBounds(10, 115, 312, 25);
		panel.add(btnQuantidadeDeFilmes);
		
		JButton btnGenerosMaisAssistidos = new JButton("Generos mais assistidos pelo usu\u00E1rio");
		btnGenerosMaisAssistidos.setBounds(10, 150, 312, 25);
		panel.add(btnGenerosMaisAssistidos);
		btnGenerosMaisAssistidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(Fachada.consultarGeneroMaisAssistido());
			}
		});
		btnGenerosMaisAssistidos.setToolTipText("Generos mais assistidos pelo usu\u00E1rio");
		btnGenerosMaisAssistidos.setForeground(Color.WHITE);
		btnGenerosMaisAssistidos.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnGenerosMaisAssistidos.setBackground(new Color(51, 102, 204));

		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{

					textArea.setText(Fachada.listarFilmes());
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
	}
}
