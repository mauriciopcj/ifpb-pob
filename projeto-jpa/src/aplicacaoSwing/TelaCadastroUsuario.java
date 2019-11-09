package aplicacaoSwing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import fachada.Fachada;

public class TelaCadastroUsuario {

	private JFrame frame;
	private JLabel label1;
	private JTextField textField;
	private JButton button2;
	private JLabel lblSenha;
	private JLabel lblEmail;
	private JTextField textField_2;
	private JLabel lblNascimento;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_4;

	/**
	 * Create the application.
	 */
	public TelaCadastroUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setResizable(false);
		frame.setTitle("Cadastro");
		frame.setBounds((width-279)/2, (heigth-475)/2, 279, 475);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label1 = new JLabel("Nome");
		label1.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		label1.setBounds(36, 114, 46, 14);
		frame.getContentPane().add(label1);
		
		textField = new JTextField();
		textField.setBounds(36, 140, 200, 25);
		textField.setColumns(10);
		frame.getContentPane().add(textField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(36, 262, 200, 25);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		textField_3.setBounds(36, 322, 40, 25);
		frame.getContentPane().add(textField_3);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		textField_1.setBounds(88, 322, 40, 25);
		frame.getContentPane().add(textField_1);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(140, 322, 96, 25);
		frame.getContentPane().add(textField_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(36, 201, 200, 25);
		frame.getContentPane().add(passwordField);
		
		button2 = new JButton("Cadastrar");
		button2.setBackground(new Color(51, 102, 204));
		button2.setForeground(new Color(255, 255, 255));
		button2.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Fachada.cadastrarUsuario(textField.getText(), 
							textField_2.getText(),
							new String (passwordField.getPassword()),
							LocalDate.of(Integer.parseInt(textField_4.getText()),
									Integer.parseInt(textField_1.getText()),
									Integer.parseInt(textField_3.getText())));
					textField.setText(""); textField_1.setText(""); textField_2.setText(""); 
					textField_3.setText(""); textField_4.setText(""); passwordField.setText("");
				}
				catch(Exception e) {
					
				}
			}
		});
		button2.setBounds(81, 382, 110, 25);
		frame.getContentPane().add(button2);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		lblSenha.setBounds(36, 176, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		lblEmail.setBounds(36, 237, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		lblNascimento = new JLabel("Data de Nascimento");
		lblNascimento.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		lblNascimento.setBounds(36, 297, 138, 14);
		frame.getContentPane().add(lblNascimento);
		
		JLabel lblYourflix = new JLabel("MAURICIO");
		lblYourflix.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourflix.setForeground(new Color(51, 102, 204));
		lblYourflix.setFont(new Font("Rockwell Condensed", Font.PLAIN, 50));
		lblYourflix.setBounds(37, 23, 200, 62);
		frame.getContentPane().add(lblYourflix);
		
		frame.setVisible(true);
	}
}
