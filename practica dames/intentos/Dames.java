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
	int borrar1=0;
	int borrar11=0;
	int num_fichas1=12;
	int num_fichas2=12;

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
		//tamaño del frame
		frmDamas.setBounds(100, 100, 450, 475);
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
		String nom1="";
		String nom2="";
		String nom3="";
		String nom4="";
		String nom5="";
		String punt1="";
		String punt2="";
		String punt3="";
		String punt4="";
		String punt5="";
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dames", "danidele", "danidele");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select nombre, puntuacion from Puntuaciones order by puntuacion desc;");
			int cont=1;
			while (rs.next()){
				if(cont==1){
					nom1=rs.getString("nombre");
					punt1=rs.getString("puntuacion");
				}
				if(cont==2){
					nom2=rs.getString("nombre");
					punt2=rs.getString("puntuacion");
				}
				if(cont==3){
					nom3=rs.getString("nombre");
					punt3=rs.getString("puntuacion");
				}
				if(cont==4){
					nom4=rs.getString("nombre");
					punt4=rs.getString("puntuacion");
				}
				if(cont==5){
					nom5=rs.getString("nombre");
					punt5=rs.getString("puntuacion");
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
		textField.setText(nom1+ "       "+ punt1);
		textField.setBackground(Color.CYAN);
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Verdana", Font.BOLD, 13));
		textField.setBounds(93, 131, 255, 22);
		frmDamas.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText(nom2+ "       "+ punt2);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.GREEN);
		textField_1.setBounds(93, 176, 255, 22);
		frmDamas.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText(nom3+ "       "+ punt3);
		textField_2.setForeground(Color.BLACK);
		textField_2.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_2.setColumns(10);
		textField_2.setBackground(Color.CYAN);
		textField_2.setBounds(93, 223, 255, 22);
		frmDamas.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText(nom4+ "       "+ punt4);
		textField_3.setForeground(Color.BLACK);
		textField_3.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.GREEN);
		textField_3.setBounds(93, 271, 255, 22);
		frmDamas.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText(nom5+ "       "+ punt5);
		textField_4.setForeground(Color.BLACK);
		textField_4.setFont(new Font("Verdana", Font.BOLD, 13));
		textField_4.setColumns(10);
		textField_4.setBackground(Color.CYAN);
		textField_4.setBounds(93, 319, 255, 22);
		frmDamas.getContentPane().add(textField_4);
		
		JLabel label = new JLabel("1-");
		label.setFont(new Font("Verdana", Font.BOLD, 16));
		label.setBounds(65, 131, 29, 21);
		frmDamas.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("2-");
		label_1.setFont(new Font("Verdana", Font.BOLD, 16));
		label_1.setBounds(65, 176, 29, 21);
		frmDamas.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("3-");
		label_2.setFont(new Font("Verdana", Font.BOLD, 16));
		label_2.setBounds(65, 223, 29, 21);
		frmDamas.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("4-");
		label_3.setFont(new Font("Verdana", Font.BOLD, 16));
		label_3.setBounds(65, 271, 29, 21);
		frmDamas.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("5-");
		label_4.setFont(new Font("Verdana", Font.BOLD, 16));
		label_4.setBounds(65, 319, 29, 21);
		frmDamas.getContentPane().add(label_4);
		
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
		
		JLabel lblJugador = new JLabel("Jugador 1");
		lblJugador.setBounds(19, 25, 56, 16);
		frmDamas.getContentPane().add(lblJugador);
		
		input2 = new JTextField();
		input2.setColumns(10);
		input2.setBounds(85, 100, 174, 41);
		frmDamas.getContentPane().add(input2);
		
		JLabel lblJugador_1 = new JLabel("Jugador 2");
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
				
				if(comprobarUsuario(patata,pass)==true && comprobarUsuario(patata2,pass2)==true) {
				
					aviso = new JTextField();
					aviso.setFont(new Font("Verdana", Font.BOLD, 14));
					aviso.setBackground(Color.green);
					aviso.setHorizontalAlignment(SwingConstants.CENTER);
					aviso.setText("Usuario "+patata+" aceptado");
					aviso.setBounds(121, 130, 200, 22);
					frmDamas.getContentPane().add(aviso);
					aviso.setColumns(10);
				
					aviso = new JTextField();
					aviso.setFont(new Font("Verdana", Font.BOLD, 14));
					aviso.setBackground(Color.green);
					aviso.setHorizontalAlignment(SwingConstants.CENTER);
					aviso.setText("Usuario "+patata2+" aceptado");
					aviso.setBounds(121, 175, 200, 22);
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

	boolean pintar = true;
	boolean dos=false;
	
	private void tablero(){
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
								if(y<7 && y>0 && x<7 && x>0 ){
									System.out.println("entra al 1");
									resaltar[y+1][x+1]=1;
									resaltar[y+1][x-1]=1;
								}else{
									if(y==0){
										resaltar[y+1][x+1]=1;
									}
									if(y==7){
										resaltar[y+1][x-1]=1;
									}
									if(x==0){
										resaltar[y+1][x+1]=1;
									}
									if(x==7){
										resaltar[y+1][x-1]=1;
									}
								}
								System.out.println(y+x+tipo);
								
							}	
							//jugar(y,x);
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
									if(pintar == true){
										if(tipo==1){
											if(y<7 && y>0 && x<7 && x>0 ){
												System.out.println("entra al 1");
												resaltar[y+1][x+1]=1;
												resaltar[y+1][x-1]=1;
												memo1 = y+1;
												memo11 = x-1;
												memo2 = y+1;
												memo22 = x+1;
											}else{
												if(y==0){
													resaltar[y+1][x+1]=1;
													memo1 = y+1;
													memo11 = x+1;
													dos=false;
												}
												if(y==7){
													resaltar[y+1][x-1]=1;
													memo1 = y+1;
													memo11 = x-1;
													dos=false;
												}
												if(x==0){
													resaltar[y+1][x+1]=1;
													memo1 = y+1;
													memo11 = x+1;
													dos=false;
												}
												if(x==7){
													resaltar[y+1][x-1]=1;
													memo1 = y+1;
													memo11 = x-1;
													dos=false;
												}
											}
											color=true;
											borrar1=y;
											borrar11=x;
										}
										pintar =false;
									}else{//false=blanco  /   true=negro
										if(y==memo1 && x==memo11){
											if(color==true){
												if(piezas[y][x]==3){
													puntos_1+=2;
													num_fichas2-=1;
													System.out.println("punto para las negras");
													if(num_fichas1==0){
														reiniciar_menu();
														guardar_ganador(patata2,puntos_2);
													}
													if(num_fichas2==0){
														reiniciar_menu();
														guardar_ganador(patata,puntos_1);
													}
													
													
												}
												piezas[y][x]=1;
												piezas[borrar1][borrar11]=0;
											}
											if(color==false){
												if(piezas[y][x]==1){
													puntos_2+=2;
													num_fichas1-=1;
													System.out.println("punto para las blancas"+puntos_1);
													if(num_fichas1==0){
														reiniciar_menu();
														guardar_ganador(patata2,puntos_2);
													}
													if(num_fichas2==0){
														reiniciar_menu();
														guardar_ganador(patata,puntos_1);
													}
												}
												piezas[y][x]=3;
												piezas[borrar1][borrar11]=0;
											}
										}
										if(y==memo2 && x==memo22){
											if(color==true){
												if(piezas[y][x]==3){
													puntos_1+=2;
													num_fichas2-=1;
													System.out.println("punto para las negras"+puntos_2);
													if(num_fichas1==0){
														reiniciar_menu();
														guardar_ganador(patata2,puntos_2);
													}
													if(num_fichas2==0){
														reiniciar_menu();
														guardar_ganador(patata,puntos_1);
													}
												}
												piezas[y][x]=1;
												piezas[borrar1][borrar11]=0;
											}
											if(color==false){
												if(piezas[y][x]==1){
													puntos_2+=2;
													num_fichas1-=1;
													System.out.println("punto para las blancas"+puntos_1);
													if(num_fichas1==0){
														reiniciar_menu();
														guardar_ganador(patata2,puntos_2);
													}
													if(num_fichas2==0){
														reiniciar_menu();
														guardar_ganador(patata,puntos_1);
													}
												}
												piezas[y][x]=3;
												piezas[borrar1][borrar11]=0;
											}
										}
										resaltar[memo1][memo11]=0;
										resaltar[memo2][memo22]=0;
										pintar=true;
										turno+=1;
									}
								}else{
									if(pintar == true){
										if(tipo==3){
											if(y<7 && y>0 && x<7 && x>0 ){
												resaltar[y-1][x-1]=1;
												resaltar[y-1][x+1]=1;
												memo1 = y-1;
												memo11 = x-1;
												memo2 = y-1;
												memo22 = x+1;
												dos =true;
											}else{
												if(y==0){
													resaltar[y-1][x-1]=1;
													resaltar[y-1][x+1]=1;
													memo1 = y-1;
													memo11 = x-1;
													memo2 = y-1;
													memo22 = x+1;
													dos=true;
												}
												if(y==7){
													resaltar[y-1][x-1]=1;
													resaltar[y-1][x+1]=1;
													memo1 = y-1;
													memo11 = x-1;
													memo2 = y-1;
													memo22 = x+1;
													dos=true;
												}
												if(x==0){//izquierda tablero
													resaltar[y-1][x+1]=1;
													memo1 = y-1;
													memo11 = x+1;
													dos=false;
												}
												if(x==7){//derecha tablero
													resaltar[y-1][x-1]=1;
													memo1 = y-1;
													memo11 = x-1;
													dos=false;
												}
											}
											color=false;
											borrar1=y;
											borrar11=x;
										}
										pintar =false;
									}else{//false=blanco  /   true=negro
										if(y==memo1 && x==memo11){
											if(color==true){
												if(piezas[y][x]==3){
													puntos_1+=2;
													System.out.println("punto para las negras");
												}
												piezas[y][x]=1;
												piezas[borrar1][borrar11]=0;
											}
											if(color==false){
												if(piezas[y][x]==1){
													puntos_2+=2;
													System.out.println("punto para las blancas");
												}
												piezas[y][x]=3;
												piezas[borrar1][borrar11]=0;
											}
										}
										if(y==memo2 && x==memo22){
											if(color==true){
												if(piezas[y][x]==3){
													puntos_1+=2;
													System.out.println("punto para las negras");
												}
												piezas[y][x]=1;
												piezas[borrar1][borrar11]=0;
											}
											if(color==false){
												if(piezas[y][x]==1){
													puntos_2+=2;
													System.out.println("punto para las blancas");
												}
												piezas[y][x]=3;
												piezas[borrar1][borrar11]=0;
											}
										}
										if(dos){
											resaltar[memo1][memo11]=0;
											resaltar[memo2][memo22]=0;
										}else{
											resaltar[memo1][memo11]=0;
										}
										pintar=true;
										turno+=1;
									}
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
	
	private void guardar_ganador(String nombre,int punt){
		try{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dames", "danidele", "danidele");
		Statement st = con.createStatement();
		int filesAfectades=st.executeUpdate("insert into Puntuaciones values ('"+nombre+"','"+punt+"');");
		}catch (Exception v){
			v.printStackTrace();
		}
		reiniciar_menu();
		pantalla_inicio();
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
			b.printStackTrace();
		}
		return existe;
	}
	
	private void reiniciar_menu(){
		frmDamas.getContentPane().removeAll();
		frmDamas.repaint();
	}
}
