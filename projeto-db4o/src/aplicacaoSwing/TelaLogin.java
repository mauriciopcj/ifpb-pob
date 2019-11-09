/********************************************************
 * 
 * IFPB  - Curso Superior de Tec. em Sist. para Internet
 * POB   - Persistencia de Objetos
 * Prof. - Fausto Ayres
 * Aluno - Mauricio Pereira da Costa Junior
 * 
 ********************************************************/
package aplicacaoSwing;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fachada.Fachada;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

public class TelaLogin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setResizable(false);

		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
//				JOptionPane.showMessageDialog(null, "Sistema finalizado !");
			}
		});
		frame.setTitle("Login");
		frame.setBounds((width-279)/2, (heigth-401)/2, 279, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(37, 146, 200, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(37, 207, 200, 25);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Conectar");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(51, 102, 204));
		btnLogin.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Fachada.checkSenha(textField.getText(), new String (passwordField.getPassword()))) {
						JFrame a = new TelaPrincipal();
						a.setVisible(true);
						Fachada.setUserlogado(textField.getText());
						textField.setText(""); passwordField.setText("");
					}
				} catch (Exception e) {
					textField.setText(""); passwordField.setText("");
					JOptionPane.showMessageDialog(null, "Login ou senha inválidos!");
				}
			}
		});
		btnLogin.setBounds(81, 264, 110, 25);
		btnLogin.setOpaque(true);
		frame.getContentPane().add(btnLogin);
		
		
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		lblUsurio.setForeground(new Color(0, 0, 0));
		lblUsurio.setBounds(37, 121, 72, 14);
		frame.getContentPane().add(lblUsurio);
		
		JLabel lblOuCadastrese = new JLabel("ou cadastre-se");
		lblOuCadastrese.setForeground(new Color(51, 102, 204));
		lblOuCadastrese.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new TelaCadastroUsuario();
			}
		});
		lblOuCadastrese.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		lblOuCadastrese.setHorizontalAlignment(SwingConstants.CENTER);
		lblOuCadastrese.setBounds(87, 300, 101, 14);
		frame.getContentPane().add(lblOuCadastrese);
		
		JLabel lblYouflix = new JLabel("MAURICIO");
		lblYouflix.setForeground(new Color(51, 102, 204));
		lblYouflix.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouflix.setFont(new Font("Rockwell Condensed", Font.PLAIN, 50));
		lblYouflix.setBounds(37, 23, 200, 62);
		frame.getContentPane().add(lblYouflix);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		lblSenha.setForeground(new Color(0, 0, 0));
		lblSenha.setBounds(37, 182, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		
	}
}
