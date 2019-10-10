package a;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Main {

	private JFrame frame;
	private JTextField textField_usuari;
	private JPasswordField textField_contra;
	private JTextField tf_usuari0;
	private JPasswordField tf_contra0;
	private JTextField tf_usuari1;
	private JPasswordField tf_contra1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 0, 720, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		M_PantallaInicial();
	}
	
	public void M_PantallaInicial() {
		frame.getContentPane().setLayout(null);
		//Botó al menu per a triar jugadors
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setForeground(Color.BLACK);
		btnJugar.setBounds(32,16,656,208);
		btnJugar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				M_BorrarMenu();
				M_TriarUsuaris();
			}
		});
		frame.getContentPane().add(btnJugar);
		
		//Botó al menu per a crear usuaris
		JButton btnCrearUsuari = new JButton("CREAR USUARI");
		btnJugar.setForeground(Color.BLACK);
		btnCrearUsuari.setBounds(32,240,656,208);
		btnCrearUsuari.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearUsuari.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				M_BorrarMenu();
				M_CrearUsuaris();
			}
		});
		frame.getContentPane().add(btnCrearUsuari);
		
		//Botó al menú per a veure el rànking
		
		JButton btnStats = new JButton("TAULA DE RÀNKING");
		btnJugar.setForeground(Color.BLACK);
		btnStats.setBounds(32,464,656,208);
		btnStats.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		frame.getContentPane().add(btnStats);	
	}
	
	public void M_CrearUsuaris() {
		
		frame.getContentPane().setLayout(null);
		
		textField_usuari = new JTextField();
		textField_usuari.setBounds(50, 100, 620, 80);
		textField_usuari.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_usuari.setText("NomUsuari");
		frame.getContentPane().add(textField_usuari);
		textField_usuari.setColumns(15);
		
		textField_contra = new JPasswordField();
		textField_contra.setBounds(50, 280, 620, 80);
		textField_contra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_contra.setText("Contrasenya");
		frame.getContentPane().add(textField_contra);
		textField_contra.setColumns(15);
		
		JButton btnCrearUsuari = new JButton("CREAR USUARI");
		btnCrearUsuari.setBounds(360, 468, 330, 204);
		btnCrearUsuari.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCrearUsuari.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(btnCrearUsuari);
		
		JButton btnTornar = new JButton("TORNAR");
		btnTornar.setBounds(20, 468, 320, 204);
		btnTornar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTornar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				M_BorrarMenu();
				M_PantallaInicial();
			}
		});
		frame.getContentPane().add(btnTornar);	
	}

	public void M_TriarUsuaris() {
		
		tf_usuari0 = new JTextField();
		tf_usuari0.setBounds(50, 25, 620, 80);
		tf_usuari0.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_usuari0.setText("Usuari1");
		frame.getContentPane().add(tf_usuari0);
		tf_usuari0.setColumns(15);
		
		tf_contra0 = new JPasswordField();
		tf_contra0.setBounds(50, 130, 620, 80);
		tf_contra0.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_contra0.setText("Contrassenya1");
		frame.getContentPane().add(tf_contra0);
		tf_contra0.setColumns(15);
		
		tf_usuari1 = new JTextField();
		tf_usuari1.setBounds(50, 235, 620, 80);
		tf_usuari1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_usuari1.setText("Usuari2");
		frame.getContentPane().add(tf_usuari1);
		tf_usuari1.setColumns(15);
		
		tf_contra1 = new JPasswordField();
		tf_contra1.setBounds(50, 340, 620, 80);
		tf_contra1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_contra1.setText("Contrassenya2");
		frame.getContentPane().add(tf_contra1);
		tf_contra1.setColumns(15);
		
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(358, 445, 343, 204);
		btnJugar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				M_BorrarMenu();
				Joc();
			}
		});
		frame.getContentPane().add(btnJugar);
		
		JButton btnTornar = new JButton("TORNAR");
		btnTornar.setBounds(12, 445, 331, 204);
		btnTornar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTornar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				M_BorrarMenu();
				M_PantallaInicial();
			}
		});
		frame.getContentPane().add(btnTornar);	
	}
	
	public void M_BorrarMenu() {
		frame.getContentPane().removeAll();
		frame.repaint();
	}

	public void Joc() {
        BufferedImage image;
		try {
			image = ImageIO.read(new File("C:\\Users\\Mateu\\Desktop\\tablero.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
}
