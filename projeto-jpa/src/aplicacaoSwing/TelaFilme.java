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
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Filme;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TelaFilme extends JFrame {
	private JPanel contentPane;
	private JButton right;
	private JButton left;
	private JLabel capa;
	private JLabel titulo;
	private List<Filme> filmes = Fachada.listarFilmesObj();
	private int step = 0;
	private JLabel duracao;
	private JLabel classificacao;
	private JLabel ano;
	private JTextPane descricao;
	private JTextPane generos;

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
	public TelaFilme() {
		
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        
		setTitle("Filmes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((width-604)/2, (heigth-393)/2, 604, 393);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		right = new JButton(">>");
		right.setForeground(new Color(255, 255, 255));
		right.setBorder(null);
		right.setBackground(new Color(51, 102, 204));
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				step++;
				if(step > filmes.size() - 1) {
					step = 0;
				}
				try {
					nextFilme();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		right.setBounds(544, 153, 30, 30);
		getContentPane().add(right);
		
		left = new JButton("<<");
		left.setForeground(new Color(255, 255, 255));
		left.setBackground(new Color(51, 102, 204));
		left.setBorder(null);
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step--;
				if(step < 0) {
					step = filmes.size() - 1;
				}
				try {
					nextFilme();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		left.setBounds(24, 153, 30, 30);
		getContentPane().add(left);
		
		capa = new JLabel("");
		capa.setBounds(66, 25, 162, 240);
		getContentPane().add(capa);
		
		titulo = new JLabel("");
		titulo.setFont(new Font("Dialog", Font.BOLD, 16));
		titulo.setBackground(new Color(230, 230, 250));
		titulo.setOpaque(true);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(238, 24, 298, 27);
		getContentPane().add(titulo);
		
		duracao = new JLabel("");
		duracao.setFont(new Font("Dialog", Font.BOLD, 12));
		duracao.setBackground(new Color(230, 230, 250));
		duracao.setOpaque(true);
		duracao.setHorizontalAlignment(SwingConstants.CENTER);
		duracao.setBounds(238, 277, 92, 27);
		getContentPane().add(duracao);
		
		ano = new JLabel("");
		ano.setFont(new Font("Dialog", Font.BOLD, 12));
		ano.setBackground(new Color(230, 230, 250));
		ano.setOpaque(true);
		ano.setHorizontalAlignment(SwingConstants.CENTER);
		ano.setBounds(444, 277, 92, 27);
		getContentPane().add(ano);
		
		classificacao = new JLabel("");
		classificacao.setFont(new Font("Dialog", Font.BOLD, 12));
		classificacao.setBackground(new Color(230, 230, 250));
		classificacao.setOpaque(true);
		classificacao.setHorizontalAlignment(SwingConstants.CENTER);
		classificacao.setBounds(340, 277, 92, 27);
		getContentPane().add(classificacao);
		
		descricao = new JTextPane();
		descricao.setFont(new Font("Dialog", Font.PLAIN, 14));
		descricao.setEditable(false);
		descricao.setBounds(240, 63, 296, 149);
		getContentPane().add(descricao);
		
	
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for(Filme f : Fachada.listarFilmesObj()) {
			model.addElement(f.getTitulo());
		}
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				step = comboBox.getSelectedIndex();
				try {
					nextFilme();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		comboBox.setOpaque(false);
		comboBox.setModel(model);
		comboBox.setBounds(66, 316, 162, 25);
		getContentPane().add(comboBox);
		
		JButton btnAssistir = new JButton("Assistir  \u25B6");
		btnAssistir.setForeground(new Color(255, 255, 255));
		btnAssistir.setBorder(null);
		btnAssistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Fachada.cadastrarVisualizacao(titulo.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAssistir.setBackground(new Color(51, 102, 204));
		btnAssistir.setBounds(238, 315, 92, 25);
		getContentPane().add(btnAssistir);
		
		JButton btnTrailer = new JButton("Trailer");
		btnTrailer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTrailer.setForeground(new Color(255, 255, 255));
		btnTrailer.setBorder(null);
		btnTrailer.setBackground(new Color(51, 102, 204));
		btnTrailer.setBounds(444, 316, 92, 25);
		getContentPane().add(btnTrailer);
		
		generos = new JTextPane();
		generos.setFont(new Font("Dialog", Font.PLAIN, 12));
		generos.setEditable(false);
		generos.setBounds(238, 223, 298, 42);
		getContentPane().add(generos);
		
		try {
			nextFilme();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addFilme(Filme f) throws Exception {
		filmes.add(f);
	}
	
	public void nextFilme() throws Exception {
		Filme f = (Filme) filmes.get(step);
		ImageIcon im = new ImageIcon(f.getCapa());
		capa.setIcon(new ImageIcon(im.getImage().getScaledInstance(
				capa.getWidth(),capa.getHeight(), Image.SCALE_DEFAULT)));
		titulo.setText(f.getTitulo());
		descricao.setText(f.getDescricao());
		generos.setText(("Genere: "+f.getGeneros()).replace("[","").replace("]",""));
		classificacao.setText(f.getClassificacao());
		duracao.setText(f.getDuracao());
		ano.setText(""+f.getAno());
	}
}
