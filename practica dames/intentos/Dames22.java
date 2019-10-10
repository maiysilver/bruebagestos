package menu;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;

public class Dames {

	private JFrame frmDamas;
	private JTextField input;
	private JPasswordField pwdContraseña;
	private JTextField input2;
	private JPasswordField pwdContraseña2;
	JLabel etiqueta = new JLabel("");
	private String patata = "";
	private String pass = "";
	private String patata2 = "";
	private String pass2 = "";
	String codi="";
	String codi2="";
	boolean vacio=false;
	boolean exist=false;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField aviso;
	private JTextField txtAsssssssssssssssssssssss;
	private JTextField txtErererer;
	//0 = nada / 1 = negra / 3 = blanca / /
	int piezas[][] =   {{0,1,0,1,0,1,0,1},
						{1,0,1,0,1,0,1,0},
						{0,1,0,1,0,1,0,1},
						{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0},
						{3,0,3,0,3,0,3,0},
						{0,3,0,3,0,3,0,3},
						{3,0,3,0,3,0,3,0}};
	//0 = nada / 1 = negra / 3 = blanca / /
	int resaltar[][] =   {{0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},};
	private static int tileSize =374/8;
	int turno =0;
	int puntos_1 = 0;
	int puntos_2 = 0;
	int memo1 = 0;
	int memo11 = 0;
	int memo2 = 0;
	int memo22 = 0;
	//false=blanco  /   true=negro
	boolean color = false;
	boolean movida = false;
	int borrar1=0;
	int borrar11=0;
	int num_fichas1=12;
	int num_fichas2=12;
	boolean entro=false;
	boolean volver_al_menu=false;
	int lado=0;//1-izquierda   2- derecha

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dames window = new Dames();
					window.frmDamas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dames() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDamas = new JFrame();
		frmDamas.setResizable(false);
		frmDamas.setTitle("Damas");
		frmDamas.setBounds(100, 100, 450, 475);//tamaño del frame
		frmDamas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDamas.getContentPane().setLayout(null);
		pantalla_inicio();
	}	
	
	private void pantalla_inicio() {
		frmDamas.getContentPane().setLayout(null);
		//boton iniciar partida
		JButton button1 = new JButton("Iniciar partida");
		button1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button1.setBounds(133, 13, 170, 64);
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reiniciar_menu();
				selec_usuario();
			}
		});
		frmDamas.getContentPane().add(button1);
		JButton button2 = new JButton("Crear usuario");
		button2.setFont(new Font("Tahoma", Font.BOLD, 13));
		button2.setBounds(157, 90, 130, 41);
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reiniciar_menu();
				crear_usuario();
			}
		});
		frmDamas.getContentPane().add(button2);
		JButton button3 = new JButton("Puntuaciones");
		button3.setFont(new Font("Tahoma", Font.BOLD, 13));
		button3.setBounds(157, 146, 130, 41);
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reiniciar_menu();
				puntuaciones();
			}
		});
		frmDamas.getContentPane().add(button3);
	}
	
	private void puntuaciones(){
		String nom1="";String nom11="";String nom2="";String nom22="";String nom3="";String nom33="";
		String nom4="";String nom44="";String nom5="";String nom55="";String punt1="";String punt11="";
		String punt2="";String punt22="";String punt3="";String punt33="";String punt4="";String punt44="";
		String punt5="";String punt55="";Date fecha1 = null;Date fecha2= null;Date fecha3= null;Date fecha4= null;Date fecha5= null;
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dames", "danidele", "danidele");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Puntuaciones order by fecha desc;");
			int cont=1;
			while (rs.next()){
				if(cont==1){
					nom1=rs.getString("nombre1");
					punt1=rs.getString("puntuacion1");
					nom11=rs.getString("nombre2");
					punt11=rs.getString("puntuacion2");
					fecha1=rs.getDate("fecha");
				}
				if(cont==2){
					nom2=rs.getString("nombre1");
					punt2=rs.getString("puntuacion1");
					nom22=rs.getString("nombre2");
					punt22=rs.getString("puntuacion2");
					fecha2=rs.getDate("fecha");
				}
				if(cont==3){
					nom3=rs.getString("nombre1");
					punt3=rs.getString("puntuacion1");
					nom33=rs.getString("nombre2");
					punt33=rs.getString("puntuacion2");
					fecha3=rs.getDate("fecha");
				}
				if(cont==4){
					nom4=rs.getString("nombre1");
					punt4=rs.getString("puntuacion1");
					nom4=rs.getString("nombre2");
					punt4=rs.getString("puntuacion2");
					fecha4=rs.getDate("fecha");
				}
				if(cont==5){
					nom5=rs.getString("nombre1");
					punt5=rs.getString("puntuacion1");
					nom55=rs.getString("nombre2");
					punt55=rs.getString("puntuacion2");
					fecha5=rs.getDate("fecha");
				}
				cont+=1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JLabel lblPuntuaciones = new JLabel("Puntuaciones");
		lblPuntuaciones.setFont(new Font("Verdana", Font.BOLD, 16));
		lblPuntuaciones.setBounds(156, 52, 129, 21);
		frmDamas.getContentPane().add(lblPuntuaciones);
		textField = new JTextField();
		textField.setText("J.1- "+nom1+ " - "+punt1+" | J.2- "+nom11+" - "+punt11+" | "+fecha1);
		textField.setBackground(Color.CYAN);
		textField.setForeground(Color.BLACK);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Verdana", Font.BOLD, 13));
		textField.setBounds(25, 131, 400, 22);
		frmDamas.getContentPane().add(textField);
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setText("J.1- "+nom2+ " - "+punt2+" | J.2- "+nom22+" - "+punt22+" | "+fecha2);
		textField_1.setForeground(Color.BLACK);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.GREEN);
		textField_1.setBounds(25, 176, 400, 22);
		frmDamas.getContentPane().add(textField_1);
		textField_2 = new JTextField();
		textField_2.setText("J.1- "+nom3+ " - "+punt3+" | J.2- "+nom33+" - "+punt33+" | "+fecha3);
		textField_2.setForeground(Color.BLACK);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_2.setColumns(10);
		textField_2.setBackground(Color.CYAN);
		textField_2.setBounds(25, 223, 400, 22);
		frmDamas.getContentPane().add(textField_2);
		textField_3 = new JTextField();
		textField_3.setText("J.1- "+nom4+ " - "+punt4+" | J.2- "+nom44+" - "+punt44+" | "+fecha4);
		textField_3.setForeground(Color.BLACK);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.GREEN);
		textField_3.setBounds(25, 271, 400, 22);
		frmDamas.getContentPane().add(textField_3);
		textField_4 = new JTextField();
		textField_4.setText("J.1- "+nom5+ " - "+punt5+" | J.2- "+nom55+" - "+punt55+" | "+fecha5);
		textField_4.setForeground(Color.BLACK);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_4.setColumns(10);
		textField_4.setBackground(Color.CYAN);
		textField_4.setBounds(25, 319, 400, 22);
		frmDamas.getContentPane().add(textField_4);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reiniciar_menu();
				pantalla_inicio();
			}
		});
		btnVolver.setBounds(167, 371, 97, 25);
		frmDamas.getContentPane().add(btnVolver);
	}
	
	private void selec_usuario(){
		input = new JTextField();
		input.setBounds(87, 13, 174, 41);
		frmDamas.getContentPane().add(input);
		input.setColumns(10);
		pwdContraseña = new JPasswordField();
		pwdContraseña.setText("contrase\u00F1a");
		pwdContraseña.setBounds(87, 67, 135, 22);
		frmDamas.getContentPane().add(pwdContraseña);
		JLabel lblJugador = new JLabel("Jug. 1");
		lblJugador.setBounds(19, 25, 56, 16);
		frmDamas.getContentPane().add(lblJugador);
		input2 = new JTextField();
		input2.setColumns(10);
		input2.setBounds(85, 100, 174, 41);
		frmDamas.getContentPane().add(input2);
		JLabel lblJugador_1 = new JLabel("Jug. 2");
		lblJugador_1.setBounds(17, 112, 56, 16);
		frmDamas.getContentPane().add(lblJugador_1);
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean paria=false;
				reiniciar_menu();				
				patata=input.getText();
				pass=String.valueOf(pwdContraseña.getPassword());
				patata2=input2.getText();
				pass2=String.valueOf(pwdContraseña2.getPassword());
				
				if(comprobarUsuario(patata,pass)==true && comprobarUsuario(patata2,pass2)==true && !patata.equals(patata2)) {
					aviso = new JTextField();
					aviso.setFont(new Font("Verdana", Font.BOLD, 14));
					aviso.setBackground(Color.green);
					aviso.setHorizontalAlignment(SwingConstants.CENTER);
					aviso.setText("Jug. 1 "+patata+" aceptado");
					aviso.setBounds(100, 130, 250, 22);
					frmDamas.getContentPane().add(aviso);
					aviso.setColumns(10);
					aviso = new JTextField();
					aviso.setFont(new Font("Verdana", Font.BOLD, 14));
					aviso.setBackground(Color.green);
					aviso.setHorizontalAlignment(SwingConstants.CENTER);
					aviso.setText("Jug. 2 "+patata2+" aceptado");
					aviso.setBounds(100, 175, 250, 22);
					frmDamas.getContentPane().add(aviso);
					aviso.setColumns(10);
					JButton btnVolver = new JButton("Iniciar");
					btnVolver.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							reiniciar_menu();
							tablero();
						}
					});
					btnVolver.setFont(new Font("Verdana", Font.BOLD, 14));
					btnVolver.setBounds(171, 220, 97, 25);
					frmDamas.getContentPane().add(btnVolver);
				}else{
					aviso = new JTextField();
					aviso.setFont(new Font("Verdana", Font.BOLD, 14));
					aviso.setBackground(Color.red);
					aviso.setHorizontalAlignment(SwingConstants.CENTER);
					aviso.setText("Error, vuelve a intentarlo.");
					aviso.setBounds(121, 190, 210, 22);
					frmDamas.getContentPane().add(aviso);
					aviso.setColumns(10);
					JButton btnVolver = new JButton("volver");
					btnVolver.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							reiniciar_menu();
							pantalla_inicio();
						}
					});
					btnVolver.setFont(new Font("Verdana", Font.BOLD, 14));
					btnVolver.setBounds(171, 220, 97, 25);
					frmDamas.getContentPane().add(btnVolver);
				}
				
			}
		});
		btnStart.setBounds(164, 178, 97, 25);
		frmDamas.getContentPane().add(btnStart);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reiniciar_menu();
				pantalla_inicio();
			}
		});
		btnVolver.setBounds(30, 178, 97, 25);
		frmDamas.getContentPane().add(btnVolver);
		pwdContraseña2 = new JPasswordField();
		pwdContraseña2.setText("contrase\u00F1a");
		pwdContraseña2.setBounds(88, 149, 135, 22);
		frmDamas.getContentPane().add(pwdContraseña2);
	}

	private void pintar_naranja_negro(int y, int x){
		if(y<7 && y>-1 && x<7 && x>0 ){
			System.out.println("entra al 1");
			resaltar[y+1][x+1]=1;
			resaltar[y+1][x-1]=1;
			memo1 = y+1;
			memo11 = x-1;
			memo2 = y+1;
			memo22 = x+1;
		}else{
			if(x==0 && y>-1 && y<7 ){//izquierda tablero
				System.out.println("entra al 1..1");
				resaltar[y+1][x+1]=1;
				memo1 = y+1;
				memo11 = x+1;
				lado=1;
				dos=false;
			}
			if(x==7 && y>-1 && y<7){//derecha tablero
				System.out.println("entra al 1..2");
				resaltar[y+1][x-1]=1;
				memo1 = y+1;
				memo11 = x-1;
				lado=2;
				dos=false;
			}
		}
		color=true;
		borrar1=y;
		borrar11=x;
	}
	
	private void pintar_naranja_blanco(int y, int x){
		if(y<8 && y>0 && x<7 && x>0 ){
			System.out.println("entra al 2");
			resaltar[y-1][x-1]=1;
			resaltar[y-1][x+1]=1;
			memo1 = y-1;
			memo11 = x-1;
			memo2 = y-1;
			memo22 = x+1;
			dos =true;
		}else{
			if(x==0 && y>0 && y<8 ){//izquierda tablero
				System.out.println("entra al 2..1");
				resaltar[y-1][x+1]=1;
				memo1 = y-1;
				memo11 = x+1;
				lado=1;
				dos=false;
			}
			if(x==7 && y>0 && y<8){//derecha tablero
				System.out.println("entra al 2..2");
				resaltar[y-1][x-1]=1;
				memo1 = y-1;
				memo11 = x-1;
				lado=2;
				dos=false;
			}
		}
		color=false;
		borrar1=y;
		borrar11=x;
	}
	
	private void turno1(int tipo, int y,int x){
		if(pintar == true){
			if(tipo==1){//1 = negra
				pintar_naranja_negro(y,x);
			}
			pintar =false;
		}else{//false=blanco  /   true=negro
			if(y==memo1 && x==memo11 ){
				if(color==true ){
					mover_ficha_negra(y,x,1);
					movida=true;
				}
				if(color==false){
					if(piezas[y][x]==1){
						puntos_2+=2;
						num_fichas1-=1;
						System.out.println("punto para las blancas1-"+puntos_1);
						if(num_fichas1==0){
							reiniciar_menu();
							guardar_ganador(patata2,puntos_2);
							pantalla_inicio();
							volver_al_menu=true;
						}
						if(num_fichas2==0){
							reiniciar_menu();
							guardar_ganador(patata,puntos_1);
							pantalla_inicio();
							volver_al_menu=true;
						}
					}
					piezas[y][x]=3;
					piezas[borrar1][borrar11]=0;
					movida=true;
				}
			}
			if(y==memo2 && x==memo22){
				if(color==true ){
					mover_ficha_negra(y,x,2);
					movida=true;
				}
				if(color==false ){
					if(piezas[y][x]==1){
						puntos_2+=2;
						num_fichas1-=1;
						System.out.println("punto para las blancas2-"+puntos_1);
						if(num_fichas1==0){
							reiniciar_menu();
							guardar_ganador(patata2,puntos_2);
							pantalla_inicio();
							volver_al_menu=true;
						}
						if(num_fichas2==0){
							reiniciar_menu();
							guardar_ganador(patata,puntos_1);
							pantalla_inicio();
							volver_al_menu=true;
						}
					}
					piezas[y][x]=3;
					piezas[borrar1][borrar11]=0;
					movida=true;
				}
			}
			resaltar[memo1][memo11]=0;
			resaltar[memo2][memo22]=0;
			pintar=true;
			turno+=1;
		}
	}
	
	private void turno2(int tipo, int y, int x){
		if(pintar == true){
			if(tipo==3 ){// 3 = blanca
				pintar_naranja_blanco(y,x);
				movida=true;
			}
			pintar =false;
		}else{//false=blanco  /   true=negro
			if(y==memo1 && x==memo11){
				if(color==true){
					if(piezas[y][x]==3){
						puntos_1+=2;
						num_fichas2-=1;
						System.out.println("punto para las negras4");
						if(num_fichas1==0){
							reiniciar_menu();
							guardar_ganador(patata2,puntos_2);
							pantalla_inicio();
							volver_al_menu=true;
						}
						if(num_fichas2==0){
							reiniciar_menu();
							guardar_ganador(patata,puntos_1);
							pantalla_inicio();
							volver_al_menu=true;
						}
					}
					piezas[y][x]=1;
					piezas[borrar1][borrar11]=0;
				}
				if(color==false ){
					mover_ficha_blanca(y,x,1);
				}
				movida=true;
				
			}
			if(y==memo2 && x==memo22 ){
				if(color==true){
					if(piezas[y][x]==3){
						puntos_1+=2;
						num_fichas2-=1;
						System.out.println("punto para las negras5");
						if(num_fichas1==0){
							reiniciar_menu();
							guardar_ganador(patata2,puntos_2);
							pantalla_inicio();
							volver_al_menu=true;
						}
						if(num_fichas2==0){
							reiniciar_menu();
							guardar_ganador(patata,puntos_1);
							pantalla_inicio();
							volver_al_menu=true;
						}
					}
					piezas[y][x]=1;
					piezas[borrar1][borrar11]=0;
				}
				if(color==false){
					mover_ficha_blanca(y,x,2);
				}
				movida=true;
			}
			resaltar[memo1][memo11]=0;
			resaltar[memo2][memo22]=0;
			pintar=true;
			turno+=1;
		}
	}
	
	boolean pintar = true;
	boolean dos=false;
	
	private void tablero(){
		movida=false;
		if(volver_al_menu==true){
			String color="";
			if (num_fichas1==0){
				color="Blancas";
			}else{
				color="Negras";
			}
			reiniciar_menu();
			aviso = new JTextField();
			aviso.setFont(new Font("Verdana", Font.BOLD, 14));
			aviso.setBackground(Color.red);
			aviso.setHorizontalAlignment(SwingConstants.CENTER);
			aviso.setText("Ganador: "+ color);
			aviso.setBounds(121, 190, 210, 22);
			frmDamas.getContentPane().add(aviso);
			aviso.setColumns(10);
			JButton btnVolver = new JButton("volver");
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					reiniciar_menu();
					pantalla_inicio();
				}
			});
			btnVolver.setFont(new Font("Verdana", Font.BOLD, 14));
			btnVolver.setBounds(171, 220, 97, 25);
			frmDamas.getContentPane().add(btnVolver);
		}
		String ficha_b = "ficha_b.png";
		String ficha_n = "ficha_n.png";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int x = i;
				int y = j;
				int tipo = piezas[j][i];
				int resalto = resaltar[j][i];
				if((i+j)%2==0 ){
					JPanel panel_1 = new JPanel();
					panel_1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(tipo==3 && pintar==true){	
							}	
							reiniciar_menu();
							tablero();
						}
					});
					if(resalto==1){
						panel_1.setBackground(Color.orange);
					}else{
						panel_1.setBackground(Color.white);
					}
					panel_1.setBounds(i*56, j*55, 56, 56);
					if(tipo==1 ){
						ImageIcon icon1 = new ImageIcon(ficha_n);
						JLabel fit1 = new JLabel();
						fit1.setBounds(0, 0, 50, 50);
						fit1.setIcon(icon1);
						panel_1.add(fit1);
					}
					if(tipo==3 ){
						ImageIcon icon1 = new ImageIcon(ficha_b);
						JLabel fit1 = new JLabel();
						fit1.setBounds(0, 0, 50, 50);
						fit1.setIcon(icon1);
						panel_1.add(fit1);
					}
					frmDamas.getContentPane().add(panel_1);
				}else{
					JPanel panel_1 = new JPanel();
					panel_1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							try{
								if(turno%2!=0){
									turno1(tipo,y,x);
								}else{
									turno2(tipo,y,x);
								}
								if(puntos_1>24 || puntos_2>24){
									if(puntos_1>puntos_2){
										guardar_ganador(patata,puntos_1);
									}else{
										guardar_ganador(patata2,puntos_2);
									}
									reiniciar_menu();
									pantalla_inicio();
								}
								reiniciar_menu();
								tablero();
							}catch (Exception v){
								v.printStackTrace();
							}
						}
					});
					if(resalto==1){
						panel_1.setBackground(Color.orange);
					}else{
						panel_1.setBackground(Color.BLACK);
					}
					panel_1.setBounds(i*56, j*55, 56, 56);
					if(tipo==1 ){
						ImageIcon icon1 = new ImageIcon(ficha_n);
						JLabel fit1 = new JLabel();
						fit1.setBounds(0, 0, 50, 50);
						fit1.setIcon(icon1);
						panel_1.add(fit1);
					}
					if(tipo==3 ){
						ImageIcon icon1 = new ImageIcon(ficha_b);
						JLabel fit1 = new JLabel();
						fit1.setBounds(0, 0, 50, 50);
						fit1.setIcon(icon1);
						panel_1.add(fit1);
					}
					frmDamas.getContentPane().add(panel_1);
				}
			}
		}
	}
	
	private void mover_ficha_negra(int y, int x, int num){
		if(num==1){
			if(piezas[y][x]==3 && piezas[y+1][x-1]==0){
				puntos_1+=2;
				num_fichas2-=1;
				System.out.println("punto para las negras1-"+puntos_1+" - "+num_fichas2);
				piezas[y+1][x-1]=1;
				piezas[y][x]=0;
				piezas[y-1][x+1]=0;
				entro =true;
			}
			if(piezas[y][x]==0 && entro==false){
				piezas[y][x]=1;
				piezas[borrar1][borrar11]=0;
				System.out.println("1.1");
			}
			if(num_fichas1==0){
				System.out.println("ganador player 1 blancas 1");
				guardar_ganador(patata2,puntos_2);
				reiniciar_menu();
				pantalla_inicio();
				volver_al_menu=true;
			}
			if(num_fichas2==0){
				System.out.println("ganador player 2 negras 1");
				guardar_ganador(patata,puntos_1);
				reiniciar_menu();
				pantalla_inicio();
				volver_al_menu=true;
			}
			entro=false;
		}
		if(num==2){
			if(piezas[y][x]==3 && piezas[y+1][x+1]==0){
				puntos_1+=2;
				num_fichas2-=1;
				System.out.println("punto para las negras2-"+puntos_1+" - "+num_fichas2);
				piezas[y+1][x+1]=1;
				piezas[y][x]=0;
				piezas[y-1][x-1]=0;
				entro=true;
			}
			if(piezas[y][x]==0 && entro==false){
				piezas[y][x]=1;
				piezas[borrar1][borrar11]=0;
				System.out.println("2.1");
			}
			if(num_fichas1==0){
				System.out.println("ganador player 1 blancas 2");
				guardar_ganador(patata2,puntos_2);
				reiniciar_menu();
				pantalla_inicio();
				volver_al_menu=true;
			}
			if(num_fichas2==0){
				System.out.println("ganador player 2 negras 2");
				guardar_ganador(patata,puntos_1);
				reiniciar_menu();
				pantalla_inicio();
				volver_al_menu=true;
			}
			entro=false;
		}
	}
	
	private void mover_ficha_blanca(int y, int x, int num){
		if(num==1){
			if(piezas[y][x]==1 && piezas[y-1][x-1]==0 && lado==1){
				puntos_2+=2;
				num_fichas1 -=1;
				System.out.println("punto para las blancas4.0 "+puntos_2+" - "+num_fichas1);
				piezas[y-1][x-1]=3;
				piezas[y][x]=0;
				piezas[y+1][x+1]=0;
				entro=true;
			}
			if(piezas[y][x]==0 && entro==false){
				piezas[y][x]=3;
				piezas[borrar1][borrar11]=0;
				System.out.println("4.1");
			}
			if(num_fichas1==0){
				System.out.println("ganador player 1 blancas 3");
				guardar_ganador(patata2,puntos_2);
				reiniciar_menu();
				pantalla_inicio();
				volver_al_menu=true;
			}
			if(num_fichas2==0){
				System.out.println("ganador player 2 negras 3");
				guardar_ganador(patata,puntos_1);
				reiniciar_menu();
				pantalla_inicio();
				volver_al_menu=true;
			}
			entro=false;
		}else{
			if(num==2){
				if(piezas[y][x]==1 && piezas[y-1][x+1]==0){
					puntos_2+=2;
					num_fichas1 -=1;
					System.out.println("punto para las blancas5"+puntos_2+" - "+num_fichas1);
					piezas[y-1][x+1]=3;
					piezas[y][x]=0;
					piezas[y+1][x-1]=0;
					entro=true;
				}
				if(piezas[y][x]==0 && entro==false){
					piezas[y][x]=3;
					piezas[borrar1][borrar11]=0;
					System.out.println("5.1");
				}
				if(num_fichas1==0){
					System.out.println("ganador player 1 blancas 4");
					guardar_ganador(patata2,puntos_2);
					reiniciar_menu();
					pantalla_inicio();
					volver_al_menu=true;
				}
				if(num_fichas2==0){
					System.out.println("ganador player 2 negras 4");
					guardar_ganador(patata,puntos_1);
					reiniciar_menu();
					pantalla_inicio();
					volver_al_menu=true;
				}
				entro=false;
			}
		}
		
	}
	
	private void guardar_ganador(String nombre,int punt){
		try{
			String pun = Integer.toString(punt);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dames", "danidele", "danidele");
			Statement st = con.createStatement();
			int filesAfectades=st.executeUpdate("insert into Puntuaciones values ('"+patata+"','"+pass+"','"+patata2+"','"+pass2+"',sysdate());");
			reiniciar_menu();
			pantalla_inicio();
		}catch (Exception v){
			v.printStackTrace();
		}
	}
	
	private void crear_usuario(){
		input = new JTextField();
		input.setBounds(150, 13, 174, 41);
		frmDamas.getContentPane().add(input);
		input.setColumns(15);
		pwdContraseña = new JPasswordField();
		pwdContraseña.setBounds(150, 67, 135, 22);
		frmDamas.getContentPane().add(pwdContraseña);
		JLabel lblJugador = new JLabel("Usuario");
		lblJugador.setBounds(25, 25, 56, 16);
		frmDamas.getContentPane().add(lblJugador);
		JButton btnStart = new JButton("Crear");
		btnStart.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				reiniciar_menu();	
				patata=input.getText();
				pass=String.valueOf(pwdContraseña.getPassword());
				try{
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dames", "danidele", "danidele");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("Select nombre from Usuarios");
					while (rs.next()){
						codi=rs.getString("nombre");
						if(patata.equals(codi)){
							exist=true;
						}
					}	
					if(exist){
						aviso = new JTextField();
						aviso.setFont(new Font("Verdana", Font.BOLD, 14));
						aviso.setBackground(Color.red);
						aviso.setHorizontalAlignment(SwingConstants.CENTER);
						aviso.setText("El usuario "+patata+" ya existe");
						aviso.setBounds(121, 190, 200, 22);
						frmDamas.getContentPane().add(aviso);
						aviso.setColumns(10);
						JButton btnVolver = new JButton("volver");
						btnVolver.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								reiniciar_menu();
								pantalla_inicio();
							}
						});
						btnVolver.setFont(new Font("Verdana", Font.BOLD, 14));
						btnVolver.setBounds(171, 220, 97, 25);
						frmDamas.getContentPane().add(btnVolver);
					}else{
						int filesAfectades=st.executeUpdate("insert into Usuarios values ('"+patata+"','"+pass+"');");
						aviso = new JTextField();
						aviso.setFont(new Font("Verdana", Font.BOLD, 14));
						aviso.setBackground(Color.green);
						aviso.setHorizontalAlignment(SwingConstants.CENTER);
						aviso.setText("Usuario creado: "+patata+" - "+pass);
						aviso.setBounds(50, 190, 320, 22);
						frmDamas.getContentPane().add(aviso);
						aviso.setColumns(10);
						JButton btnVolver = new JButton("volver");
						btnVolver.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								reiniciar_menu();
								pantalla_inicio();
							}
						});
						btnVolver.setFont(new Font("Verdana", Font.BOLD, 14));
						btnVolver.setBounds(171, 220, 97, 25);
						frmDamas.getContentPane().add(btnVolver);
					}
					exist=false;
				}catch (Exception v){
					v.printStackTrace();
				}
				//pantalla_inicio();
			}
		});
		btnStart.setBounds(164, 178, 97, 25);
		frmDamas.getContentPane().add(btnStart);
		JButton btnVolver = new JButton("volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reiniciar_menu();
				pantalla_inicio();
			}
		});
		btnVolver.setBounds(30, 178, 97, 25);
		frmDamas.getContentPane().add(btnVolver);
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(19, 70, 85, 16);
		frmDamas.getContentPane().add(lblContrasea);
	}
	
	private boolean comprobarUsuario(String patata, String pass){
		boolean existe=false;
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dames", "danidele", "danidele");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Usuarios");
			while (rs.next()){
				codi=rs.getString("nombre");
				codi2=rs.getString("contraseña");
				if(patata.equals(codi) && pass.equals(codi2)){
					existe=true;
				}
			}	
		}catch(Exception b){
			System.out.println("Error al conectar con la base de datos!");
			b.printStackTrace();
		}
		return existe;
	}
	
	private void reiniciar_menu(){
		frmDamas.getContentPane().removeAll();
		frmDamas.repaint();
	}
}
