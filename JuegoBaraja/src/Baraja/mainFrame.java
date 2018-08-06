package Baraja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import componentes.Baraja;
import componentes.VariosJuegos;
import componentes.tablaYgrafica;

public class mainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private UIManager.LookAndFeelInfo [] apariencias;
	private Vector<Baraja> j1 = new Vector<>(), 
						   j2 = new Vector<>(), 
						   j3 = new Vector<>(), 
						   j4 = new Vector<>();
	@SuppressWarnings("unused")
	private Vector<Baraja> baraja = new Vector<Baraja>();

	
	private JMenuBar menuApp;
	private JMenu juego, datosDelaPartida;
	private JMenuItem juegoNuevo, salir, tablaDeJugadas, graficaDeGanadores;
	
	private JPanel zonaCentro, zonaCentroSur, zonaCentroNorte,zonaCentroEste,zonaCentroOeste;
	
	private JToolBar jToolBarNorte, jToolBarOeste;
	private JButton jbLanzar, jbReiniciar, jbSalir;
	
	private JScrollPane sTabla;
	private JTable tInfo;
	private DefaultTableModel dtmDatos;
	private tablaYgrafica t,g;
	private VariosJuegos j;
	private DefaultPieDataset data;
	
	private JToolBar jTool;
	private JButton jugar,jugador1, jugador2,jugador3,jugador4;
	private JTextField tField;
	
	int n=9,c1=0,c2=0,c3=0,c4=0,nl=1,v1=0,v2=0,v3=0,v4=0;
	private ImageIcon imgCentro, imgSur, imgOeste, imgNorte, imgEste; //Imagen para los labels 
	private JLabel imagenCentro, imagenNorte, imagenSur, imagenEste, imagenOeste, imagenTabla, imagenGrafica,ganador,sur,oeste,norte,este, //Labels para imagen
				   jd1, jd2 ,jd3, jd4; //Labels para score

	public mainFrame()
	{
		super("CASINO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500,250,400,200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setIconImage(new ImageIcon("icon.png").getImage());
		initComponents();
		setBackground(Color.BLACK);
	}
	private void initComponents() 
	{
		menu();
		llena();
		cambioEstilo();
		creaTool();
		zonaCentro();
		zonaNorte();
		zonaOeste();
		creaTabla();
	}
	private void menu() 
	{
		menuApp = new JMenuBar();
		menuApp.setBackground(Color.BLACK);
		
		juego= new JMenu("Juego");
		juego.setMnemonic('J');
		
		juegoNuevo = new JMenuItem("Varios juegos");
		juegoNuevo.setMnemonic('V');
		juegoNuevo.setToolTipText("Simula varios juegos consecutivamente");
		juegoNuevo.addActionListener(this);
		juegoNuevo.setIcon(new ImageIcon("lanzarGrande.png"));
		juego.add(juegoNuevo);
		
		salir = new JMenuItem("Salir");
		salir.setMnemonic('S');
		salir.setIcon(new ImageIcon("salirGrande.png"));
		salir.addActionListener(this);
		juego.add(salir);
		
		datosDelaPartida = new JMenu("Datos de las jugadas");
		datosDelaPartida.setMnemonic('D');
		
		tablaDeJugadas = new JMenuItem("Tabla de jugadas");
		tablaDeJugadas.setMnemonic('T');
		tablaDeJugadas.addActionListener(this);
		tablaDeJugadas.setIcon(new ImageIcon("imagenTabla.png"));
		datosDelaPartida.add(tablaDeJugadas);
		
		graficaDeGanadores = new JMenuItem("Grafica de ganadores");
		graficaDeGanadores.setMnemonic('G');
		graficaDeGanadores.addActionListener(this);
		graficaDeGanadores.setIcon(new ImageIcon("imagenGrafica64.png"));
		datosDelaPartida.add(graficaDeGanadores);
				
		menuApp.add(juego);
		menuApp.add(datosDelaPartida);
		setJMenuBar(menuApp);
	}
	@SuppressWarnings("unused")
	private void llena() 
	{
		int nombre,numero;
		
		baraja = new Vector<Baraja>();
		for(int i=0; i<10; i++) 
		{
			numero=i;
			nombre=1;
			baraja.add(new Baraja(nombre,numero));
		}
		for(int i=0; i<10; i++) 
		{
			numero=i;
			nombre=2;
			baraja.add(new Baraja(nombre,numero));
		}
		for(int i=0; i<10; i++) 
		{
			numero=i;
			nombre=3;
			baraja.add(new Baraja(nombre,numero));
		}
		for(int i=0; i<10; i++) 
		{
			numero=i;
			nombre=4;
			baraja.add(new Baraja(nombre,numero));
		}
		reparto();
	}
	private void reparto() 
	{
		int jugador=1, n=40;
		for (int i=0; i<40; i++) 
		{
			if(jugador>4) 
			{
				jugador=1;
			}
			
			if(jugador==1) 
				jugador(j1,n);
			else if(jugador==2)
				jugador(j2,n);
			else if(jugador==3)
				jugador(j3,n);
			else
				jugador(j4,n);
			
			jugador++;
			n--;
		}
	}
	private void jugador(Vector <Baraja> j1, int n) 
	{
		int nCarta;
		Random r = new Random();
		nCarta=r.nextInt(n);
		j1.add(baraja.get(nCarta));
		baraja.remove(nCarta);
	}
	private void cambioEstilo() 
	{
		apariencias = UIManager.getInstalledLookAndFeels();
		try
		{
			UIManager.setLookAndFeel( apariencias[ 3 ].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );
		}
		catch ( Exception excepcion )
		{
			excepcion.printStackTrace();
		}
		
	}
	private void zonaCentro() 
	{
		zonaCentro = new JPanel();
		zonaCentro.setLayout(new BorderLayout());
		zonaCentro.setBackground(Color.BLACK);
		
		// zonaCentro zonaCentro
		imgCentro = new ImageIcon("zonaCentro.png");
		imagenCentro = new JLabel(imgCentro);
		
		//zonaCentro zonaSur
		zonaCentroSur= new JPanel();
		sur = new JLabel(new ImageIcon("leon.png"));
		sur.setVisible(false);
		zonaCentroSur.setBackground(new Color(0, 96, 128));
		imgSur = new ImageIcon("leon.png");
		imagenSur = new JLabel(imgSur);
		zonaCentroSur.add(sur);
		zonaCentroSur.add(imagenSur);
		
		//zonaCentro zonaOeste
		zonaCentroOeste= new JPanel();
		oeste = new JLabel(new ImageIcon("leon.png"));
		oeste.setVisible(false);
		zonaCentroOeste.setBackground(new Color(0, 102, 0));
		zonaCentroOeste.setLayout(new BorderLayout());
		imgOeste = new ImageIcon("leon.png");
		imagenOeste = new JLabel(imgOeste);
		zonaCentroOeste.add(oeste,BorderLayout.WEST);
		zonaCentroOeste.add(imagenOeste,BorderLayout.CENTER);
		
		//zonaCentro zonaNorte
		zonaCentroNorte = new JPanel();
		norte = new JLabel(new ImageIcon("leon.png"));
		norte.setVisible(false);
		zonaCentroNorte.setBackground(new Color(192,57,43));
		imgNorte = new ImageIcon("leon.png");
		imagenNorte = new JLabel(imgNorte);
		zonaCentroNorte.add(norte);
		zonaCentroNorte.add(imagenNorte);
		
		//zonaCentro zonaEste
		zonaCentroEste = new JPanel();
		este = new JLabel(new ImageIcon("leon.png"));
		este.setVisible(false);
		zonaCentroEste.setBackground(new Color(240,195,15));
		zonaCentroEste.setLayout(new BorderLayout());
		imgEste = new ImageIcon("leon.png");
		imagenEste = new JLabel(imgEste);
		zonaCentroEste.add(este,BorderLayout.WEST);
		zonaCentroEste.add(imagenEste,BorderLayout.CENTER);
		
		
		zonaCentro.add(imagenCentro, BorderLayout.CENTER);
		zonaCentro.add(zonaCentroSur, BorderLayout.SOUTH);
		zonaCentro.add(zonaCentroOeste, BorderLayout.WEST);
		zonaCentro.add(zonaCentroNorte, BorderLayout.NORTH);
		zonaCentro.add(zonaCentroEste, BorderLayout.EAST);
		add(zonaCentro, BorderLayout.CENTER);
	}
	private void zonaNorte() 
	{
		jToolBarNorte = new JToolBar("Barra del juego");
		jToolBarNorte.setBackground(Color.BLACK);
		
		jbLanzar = new JButton("Lanzar");
		jbLanzar.addActionListener(this);
		jbLanzar.setBorder(null);
		jbLanzar.setIcon(new ImageIcon("lanzar.png"));
		jbLanzar.setBackground(Color.BLACK);
		jbLanzar.setForeground(Color.WHITE);
		
		jbReiniciar = new JButton("Reiniciar");
		jbReiniciar.setBorder(null);
		jbReiniciar.addActionListener(this);
		jbReiniciar.setIcon(new ImageIcon("reiniciar.png"));
		jbReiniciar.setEnabled(false);
		jbReiniciar.setBackground(Color.BLACK);
		
		jd1= new JLabel("0");
		jd1.setPreferredSize(new Dimension(30,30));
		jd1.setForeground(new Color(41,128,184));
		
		jd2= new JLabel("0");
		jd2.setForeground(new Color(0, 102, 0));
		jd2.setPreferredSize(new Dimension(30,30));
		
		jd3= new JLabel("0");
		jd3.setForeground(new Color(192,57,43));
		jd3.setPreferredSize(new Dimension(30,30));
	
		jd4= new JLabel("0");
		jd4.setForeground(new Color(240,195,15));
		jd4.setPreferredSize(new Dimension(30,30));
		
		ganador = new JLabel();
		ganador.setVisible(false);
		
		jbSalir = new JButton("Salir");
		jbSalir.setToolTipText("Salir de el juego");
		jbSalir.setIcon(new ImageIcon("salir.png"));
		jbSalir.setBackground(Color.BLACK);
		jbSalir.setForeground(Color.WHITE);
		jbSalir.addActionListener(this);
		
		jToolBarNorte.add(jbLanzar);
		jToolBarNorte.add(new JToolBar.Separator());
		jToolBarNorte.add(jbReiniciar);
		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
		sep.setBackground(Color.BLACK);
		sep.setForeground(Color.BLACK);
		jbReiniciar.setForeground(Color.WHITE);
		jToolBarNorte.add(sep);
		jToolBarNorte.add(jd1);
		jToolBarNorte.add(new JToolBar.Separator());
		jToolBarNorte.add(jd2);
		jToolBarNorte.add(new JToolBar.Separator());
		jToolBarNorte.add(jd3);
		jToolBarNorte.add(new JToolBar.Separator());
		jToolBarNorte.add(jd4);
		JSeparator sep3= new JSeparator(JSeparator.HORIZONTAL);
		sep3.setBackground(Color.BLACK);
		sep3.setForeground(Color.BLACK);
		jToolBarNorte.add(sep3);
		jToolBarNorte.add(ganador);
		JSeparator sep2= new JSeparator(JSeparator.HORIZONTAL);
		sep2.setBackground(Color.BLACK);
		sep2.setForeground(Color.BLACK);
		jToolBarNorte.add(sep2);
		jToolBarNorte.add(jbSalir);
		
		add(jToolBarNorte, BorderLayout.SOUTH);	
	}
	/*private void distribucion(Vector <Baraja>  jugador) {
		double palo, carta;
		palo = Math.random();
		carta = Math.random();
		if(palo<0.25) {
			if(carta<0.10) {
				jugador.add(baraja.get(0));
				baraja.remove(0);
			}else if(carta>0.10 && carta<0.20 ) {
				jugador.add(baraja.get(1));
				baraja.remove(1);
			}else if(carta>0.20 && carta<0.30 ) {
				jugador.add(baraja.get(2));
				baraja.remove(2);
			}else if(carta>0.30 && carta<0.40 ) {
				jugador.add(baraja.get(3));
				baraja.remove(3);
			}else if(carta>0.40 && carta<0.50 ) {
				jugador.add(baraja.get(4));
				baraja.remove(4);
			}else if(carta>0.50 && carta<0.60 ) {
				jugador.add(baraja.get(5));
				baraja.remove(5);
			}else if(carta>0.60 && carta<0.70 ) {
				jugador.add(baraja.get(6));
				baraja.remove(6);
			}else if(carta>0.70 && carta<0.80 ) {
				jugador.add(baraja.get(7));
				baraja.remove(7);
			}else if(carta>0.80 && carta<0.90 ) {
				jugador.add(baraja.get(8));
				baraja.remove(8);
			}else if(carta>0.90 && carta<1 ) {
				jugador.add(baraja.get(9));
				baraja.remove(9);
			}
		}else if(palo>0.25 && palo<0.50) {
			if(carta<0.10) {
				jugador.add(baraja.get(10));
				baraja.remove(0);
			}else if(carta>0.10 && carta<0.20 ) {
				jugador.add(baraja.get(11));
				baraja.remove(1);
			}else if(carta>0.20 && carta<0.30 ) {
				jugador.add(baraja.get(12));
				baraja.remove(2);
			}else if(carta>0.30 && carta<0.40 ) {
				jugador.add(baraja.get(13));
				baraja.remove(3);
			}else if(carta>0.40 && carta<0.50 ) {
				jugador.add(baraja.get(14));
				baraja.remove(4);
			}else if(carta>0.50 && carta<0.60 ) {
				jugador.add(baraja.get(15));
				baraja.remove(5);
			}else if(carta>0.60 && carta<0.70 ) {
				jugador.add(baraja.get(16));
				baraja.remove(6);
			}else if(carta>0.70 && carta<0.80 ) {
				jugador.add(baraja.get(17));
				baraja.remove(7);
			}else if(carta>0.80 && carta<0.90 ) {
				jugador.add(baraja.get(18));
				baraja.remove(8);
			}else if(carta>0.90 && carta<1 ) {
				jugador.add(baraja.get(19));
				baraja.remove(9);
			}
		}else if(palo>0.50 && palo<0.75) {
			if(carta<0.10) {
				jugador.add(baraja.get(20));
				baraja.remove(0);
			}else if(carta>0.10 && carta<0.20 ) {
				jugador.add(baraja.get(21));
				baraja.remove(1);
			}else if(carta>0.20 && carta<0.30 ) {
				jugador.add(baraja.get(22));
				baraja.remove(2);
			}else if(carta>0.30 && carta<0.40 ) {
				jugador.add(baraja.get(23));
				baraja.remove(3);
			}else if(carta>0.40 && carta<0.50 ) {
				jugador.add(baraja.get(24));
				baraja.remove(4);
			}else if(carta>0.50 && carta<0.60 ) {
				jugador.add(baraja.get(25));
				baraja.remove(5);
			}else if(carta>0.60 && carta<0.70 ) {
				jugador.add(baraja.get(26));
				baraja.remove(6);
			}else if(carta>0.70 && carta<0.80 ) {
				jugador.add(baraja.get(27));
				baraja.remove(7);
			}else if(carta>0.80 && carta<0.90 ) {
				jugador.add(baraja.get(28));
				baraja.remove(8);
			}else if(carta>0.90 && carta<1 ) {
				jugador.add(baraja.get(29));
				baraja.remove(9);
			}
		}else {
			if(carta<0.10) {
				jugador.add(baraja.get(0));
				baraja.remove(30);
			}else if(carta>0.10 && carta<0.20 ) {
				jugador.add(baraja.get(1));
				baraja.remove(31);
			}else if(carta>0.20 && carta<0.30 ) {
				jugador.add(baraja.get(2));
				baraja.remove(32);
			}else if(carta>0.30 && carta<0.40 ) {
				jugador.add(baraja.get(3));
				baraja.remove(33);
			}else if(carta>0.40 && carta<0.50 ) {
				jugador.add(baraja.get(4));
				baraja.remove(34);
			}else if(carta>0.50 && carta<0.60 ) {
				jugador.add(baraja.get(5));
				baraja.remove(35);
			}else if(carta>0.60 && carta<0.70 ) {
				jugador.add(baraja.get(6));
				baraja.remove(36);
			}else if(carta>0.70 && carta<0.80 ) {
				jugador.add(baraja.get(7));
				baraja.remove(37);
			}else if(carta>0.80 && carta<0.90 ) {
				jugador.add(baraja.get(8));
				baraja.remove(38);
			}else if(carta>0.90 && carta<1 ) {
				jugador.add(baraja.get(9));
				baraja.remove(39);
			}
		}
	}*/
	private void zonaOeste() 
	{
		jToolBarOeste = new JToolBar();
		jToolBarOeste.setBackground(Color.BLACK);
		jToolBarOeste.setFloatable(false);
		
		jugador1 = new JButton("Jugador 1");
		jugador1.setBackground(Color.BLACK);
		jugador1.setForeground(new Color(41,128,184));
		jugador1.setBorder(null);
		jugador1.addActionListener(this);
		
		jugador2 = new JButton("Jugador 2");
		jugador2.setBackground(Color.BLACK);
		jugador2.setForeground(new Color(0, 102, 0));
		jugador2.setBorder(null);
		jugador2.addActionListener(this);
		
		jugador3 = new JButton("Jugador 3");
		jugador3.setBackground(Color.BLACK);
		jugador3.setForeground(new Color(192,57,43));
		jugador3.setBorder(null);
		jugador3.addActionListener(this);
		
		jugador4 = new JButton("Jugador 4");
		jugador4.setBackground(Color.BLACK);
		jugador4.setForeground(new Color(240,195,15));
		jugador4.setBorder(null);
		jugador4.addActionListener(this);
		
		
		jToolBarOeste.add(new JToolBar.Separator());
		jToolBarOeste.add(jugador1);
		JSeparator sep= new JSeparator(JSeparator.HORIZONTAL);
		sep.setBackground(Color.BLACK);
		sep.setForeground(Color.BLACK);
		jToolBarOeste.add(sep);
		jToolBarOeste.add(new JToolBar.Separator());
		jToolBarOeste.add(jugador2);
		JSeparator sep2= new JSeparator(JSeparator.HORIZONTAL);
		sep2.setBackground(Color.BLACK);
		sep2.setForeground(Color.BLACK);
		jToolBarOeste.add(sep2);
		jToolBarOeste.add(new JToolBar.Separator());
		jToolBarOeste.add(jugador3);
		JSeparator sep3= new JSeparator(JSeparator.HORIZONTAL);
		sep3.setBackground(Color.BLACK);
		sep3.setForeground(Color.BLACK);
		jToolBarOeste.add(sep3);
		jToolBarOeste.add(new JToolBar.Separator());
		jToolBarOeste.add(jugador4);
		add(jToolBarOeste, BorderLayout.NORTH);
	}
	private void creaTool() 
	{
		jTool = new JToolBar("Barra de opciones");
		jTool.setFloatable(false);
		
		jugar = new JButton("Jugar");
		jugar.addActionListener(this);
		jTool.add(jugar);
		
		tField = new JTextField();
		jTool.add(tField);
	}
	private void creaGrafica()
	{
		data = new DefaultPieDataset();
        data.setValue(jugador1.getText(), v1);
        data.setValue(jugador2.getText(), v2);
        data.setValue(jugador3.getText(), v3);
        data.setValue(jugador4.getText(), v4);
        
        JFreeChart chart = ChartFactory.createPieChart3D("Ganadores", data, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        
        BufferedImage graficoTorta = chart.createBufferedImage(300,300);       
        JLabel lblTorta = new JLabel();
        lblTorta.setIcon(new ImageIcon(graficoTorta));    
        imagenGrafica = new JLabel(new ImageIcon("imagenGrafica.png"));
        
        g.add(imagenGrafica);
        g.add(lblTorta);
	}
	private void creaTabla() 
	{
		String [] nombreColumnas = {"N",jugador1.getText(),jugador2.getText(),jugador3.getText(),jugador4.getText()};
		Object [][] registros = null;
		
		dtmDatos = new DefaultTableModel(registros, nombreColumnas);
		
		tInfo = new JTable(dtmDatos);
		sTabla = new JScrollPane();
		sTabla.setViewportView(tInfo);
		imagenTabla = new JLabel(new ImageIcon("imagenTabla.png"));	
	}
	private void lanzar(Baraja j, JLabel img) 
	{
		switch (j.getNombre()) 
		{
		case 1:
				switch(j.getNumero()) 
				{
				case 0:	img.setIcon(new ImageIcon("as de oros.png"));
					break;
				case 1: img.setIcon(new ImageIcon("2 de oros.png"));
					break;
				case 2: img.setIcon(new ImageIcon("3 de oros.png"));
					break;
				case 3: img.setIcon(new ImageIcon("4 de oros.png"));
					break;
				case 4: img.setIcon(new ImageIcon("5 de oros.png"));
					break;
				case 5: img.setIcon(new ImageIcon("6 de oros.png"));
					break;
				case 6: img.setIcon(new ImageIcon("7 de oros.png"));
					break;
				case 7: img.setIcon(new ImageIcon("mono de oros.png"));
					break;
				case 8: img.setIcon(new ImageIcon("caballo de oros.png"));
					break;
				case 9: img.setIcon(new ImageIcon("panson de oros.png"));
					break;
				}
			break;
		case 2:
			switch(j.getNumero()) 
			{
			case 0:	img.setIcon(new ImageIcon("as de copas.png"));
				break;
			case 1: img.setIcon(new ImageIcon("2 de copas.png"));
				break;
			case 2: img.setIcon(new ImageIcon("3 de copas.png"));
				break;
			case 3: img.setIcon(new ImageIcon("4 de copas.png"));
				break;
			case 4: img.setIcon(new ImageIcon("5 de copas.png"));
				break;
			case 5: img.setIcon(new ImageIcon("6 de copas.png"));
				break;
			case 6: img.setIcon(new ImageIcon("7 de copas.png"));
				break;
			case 7: img.setIcon(new ImageIcon("mono de copas.png"));
				break;
			case 8: img.setIcon(new ImageIcon("caballo de copas.png"));
				break;
			case 9: img.setIcon(new ImageIcon("panson de copas.png"));
				break;
			}
			break;
		case 3:
			switch(j.getNumero()) 
			{
			case 0:	img.setIcon(new ImageIcon("as de espadas.png"));
				break;
			case 1: img.setIcon(new ImageIcon("2 de espadas.png"));
				break;
			case 2: img.setIcon(new ImageIcon("3 de espadas.png"));
				break;
			case 3: img.setIcon(new ImageIcon("4 de espadas.png"));
				break;
			case 4: img.setIcon(new ImageIcon("5 de espadas.png"));
				break;
			case 5: img.setIcon(new ImageIcon("6 de espadas.png"));
				break;
			case 6: img.setIcon(new ImageIcon("7 de espadas.png"));
				break;
			case 7: img.setIcon(new ImageIcon("mono de espadas.png"));
				break;
			case 8: img.setIcon(new ImageIcon("caballo de espadas.png"));
				break;
			case 9: img.setIcon(new ImageIcon("panson de espadas.png"));
				break;
			}
			break;
		case 4:
			switch(j.getNumero()) 
			{
			case 0:	img.setIcon(new ImageIcon("as de bastos.png"));
				break;
			case 1: img.setIcon(new ImageIcon("2 de bastos.png"));
				break;
			case 2: img.setIcon(new ImageIcon("3 de bastos.png"));
				break;
			case 3: img.setIcon(new ImageIcon("4 de bastos.png"));
				break;
			case 4: img.setIcon(new ImageIcon("5 de bastos.png"));
				break;
			case 5: img.setIcon(new ImageIcon("6 de bastos.png"));
				break;
			case 6: img.setIcon(new ImageIcon("7 de bastos.png"));
				break;
			case 7: img.setIcon(new ImageIcon("mono de bastos.png"));
				break;
			case 8: img.setIcon(new ImageIcon("caballo de bastos.png"));
				break;
			case 9: img.setIcon(new ImageIcon("panson de bastos.png"));
				break;
			}
			break;
		default:
			break;
		}
	}
	private void mayor(Baraja j1, Baraja j2, Baraja j3, Baraja j4, JLabel l1, JLabel l2, JLabel l3, JLabel l4)
	{
		Baraja m = new Baraja(0,0);
		Baraja a[] = {j1,j2,j3,j4};
		for(int i=0; i<4; i++)
		{
			if(a[i].getNumero()>m.getNumero()) 
			{
				m=a[i];
			}
			else if(m.getNumero()==a[i].getNumero()) 
			{
				if(a[i].getNombre()>m.getNombre())
					m=a[i];
			}
		}
		
		String[] fila = new String[5];
		if(m==j1)
		{
			c1++;
			l1.setText(String.valueOf(c1));
			fila[0]=String.valueOf(nl);
			fila[1]="Ganador";
			dtmDatos.addRow(fila);
		}else if(m==j2) 
		{
			c2++;
			l2.setText(String.valueOf(c2));
			fila[0]=String.valueOf(nl);
			fila[2]="Ganador";
			dtmDatos.addRow(fila);
		}else if(m==j3) 
		{
			c3++;
			l3.setText(String.valueOf(c3));
			fila[0]=String.valueOf(nl);
			fila[3]="Ganador";
			dtmDatos.addRow(fila);
		}else {
			c4++;
			l4.setText(String.valueOf(c4));
			fila[0]=String.valueOf(nl);
			fila[4]="Ganador";
			dtmDatos.addRow(fila);
		}
		nl++;
	}
	private void reinicia()
	{
		c1=0; c2=0; c3=0; c4=0;
		n=9;
		jd1.setText("0"); jd2.setText("0"); jd3.setText("0"); jd4.setText("0");
		j1.removeAllElements(); j2.removeAllElements(); j3.removeAllElements(); j4.removeAllElements();
		baraja.removeAllElements();
		imagenNorte.setIcon(imgNorte); imagenSur.setIcon(imgNorte); imagenOeste.setIcon(imgNorte); imagenEste.setIcon(imgNorte);
		jbLanzar.setEnabled(true);
		jbReiniciar.setEnabled(false);
		nl=1;
		
		llena();
	}
	private void mayor(int j1,int j2,int j3,int j4) 
	{
		int [] a = {j1,j2,j3,j4};
		int m=0;
		
		for(int i=0; i<4; i++) 
		{
			if(a[i]>m) 
			{
				m=a[i];
			}
		}
		if(m==j1) 
		{
			ganador.setText("Ganador : AZUL");
			ganador.setForeground(new Color(41,128,184));
			ganador.setVisible(true);
		} else if(m == j2) {
			ganador.setText("Ganador : VERDE");
			ganador.setForeground(new Color(0, 102, 0));
			ganador.setVisible(true);
		}else if(m == j3) {
			ganador.setText("Ganador : ROJO");
			ganador.setForeground(new Color(192,57,43));
			ganador.setVisible(true);
		}else {
			ganador.setText("Ganador : AMARILLO");
			ganador.setForeground(new Color(240,195,15));
			ganador.setVisible(true);
		}
	}
	private void mayorVarios(int j1,int j2,int j3,int j4) 
	{
		int [] a = {j1,j2,j3,j4};
		int m=a[0];
		
		for(int i=0; i<4; i++) 
		{
			if(a[i]>m) 
			{
				m=a[i];
			}
		}
		if(m==j1) 
		{
			v1++;
		}if(m == j2) {
			v2++;
		}if(m == j3) {
			v3++;
		}else if(m ==j4)
		{
			v4++;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == salir) 
		{
			System.exit(NORMAL);
		}
		if(e.getSource() == jbLanzar) 
		{
			try {
				lanzar(j1.get(n), imagenSur);
				lanzar(j2.get(n), imagenOeste);
				lanzar(j3.get(n), imagenNorte);
				lanzar(j4.get(n), imagenEste);
				mayor(j1.get(n),j2.get(n),j3.get(n),j4.get(n),jd1,jd2,jd3,jd4);
				sur.setVisible(true);
				oeste.setVisible(true);
				norte.setVisible(true);
				este.setVisible(true);
				n--;
			}catch (Exception e1) {
				mayor(Integer.parseInt(jd1.getText()),Integer.parseInt(jd2.getText()),Integer.parseInt(jd3.getText()),Integer.parseInt(jd4.getText()));
				jbLanzar.setEnabled(false);
				jbReiniciar.setEnabled(true);
				sur.setVisible(false);
				oeste.setVisible(false);
				norte.setVisible(false);
				este.setVisible(false);
				v1=Integer.parseInt(jd1.getText());
				v2=Integer.parseInt(jd2.getText());
				v3=Integer.parseInt(jd3.getText());
				v4=Integer.parseInt(jd4.getText());
			}
		}
		if(e.getSource() == jbReiniciar) 
		{
			reinicia();
			ganador.setVisible(false);
		}
		if(e.getSource() == tablaDeJugadas) 
		{
			creaTabla();
			tInfo = new JTable(dtmDatos);
			t = new tablaYgrafica(this,"Tabla de jugadas");
			t.setVisible(true);
			t.setIconImage(new ImageIcon("imagenTabla.png").getImage());
			t.add(imagenTabla);
			t.add(sTabla);
		}
		if(e.getSource() == graficaDeGanadores) 
		{
			g = new tablaYgrafica(this, "Grafica de ganadores");
			g.setVisible(true);
			g.setSize(320,650);
			g.setLocation(125, 50);
			g.setIconImage(new ImageIcon("imagenGrafica.png").getImage());
			creaGrafica();
			v1=0;v2=0;v3=0;v4=0;
		}
		if(e.getSource() == juegoNuevo) 
		{
			j = new VariosJuegos(this, "Numero de juegos");
			j.add(jTool,BorderLayout.SOUTH);
			j.setIconImage(new ImageIcon("lanzar.png").getImage());
		}
		if(e.getSource() == jugar)
		{
			for(int i =0; i<Integer.parseInt(tField.getText()); i++) 
			{
				reinicia();
				for(int j=0; j<10; j++) 
				{
					lanzar(j1.get(n), imagenSur);
					lanzar(j2.get(n), imagenOeste);
					lanzar(j3.get(n), imagenNorte);
					lanzar(j4.get(n), imagenEste);
					mayor(j1.get(n),j2.get(n),j3.get(n),j4.get(n),jd1,jd2,jd3,jd4);
					n--;
				} 
				mayorVarios(Integer.parseInt(jd1.getText()),Integer.parseInt(jd2.getText()),Integer.parseInt(jd3.getText()),Integer.parseInt(jd4.getText()));
			}
			jbReiniciar.setEnabled(true);
			jbLanzar.setEnabled(false);
			jd1.setText(String.valueOf(v1));
			jd2.setText(String.valueOf(v2));
			jd3.setText(String.valueOf(v3));
			jd4.setText(String.valueOf(v4));
		}
		if(e.getSource() == jugador1)
		{
			jugador1.setText(JOptionPane.showInputDialog("Ingre el nombre del jugador azul : "));
		}
		if(e.getSource() == jugador2)
		{
			jugador2.setText(JOptionPane.showInputDialog("Ingre el nombre del jugador verde : "));
		}
		if(e.getSource() == jugador3)
		{
			jugador3.setText(JOptionPane.showInputDialog("Ingre el nombre del jugador rojo : "));
		}
		if(e.getSource() == jugador4)
		{
			jugador4.setText(JOptionPane.showInputDialog("Ingre el nombre del jugador amarillo : "));
		}
	}
}
