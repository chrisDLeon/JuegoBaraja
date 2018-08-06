package componentes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VariosJuegos extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton jugar;
	private JTextField tField;
	public JButton getJugar() {
		return jugar;
	}
	public void setJugar(JButton jugar) {
		this.jugar = jugar;
	}
	public JTextField gettField() {
		return tField;
	}
	public void settField(JTextField tField) {
		this.tField = tField;
	}
	private JLabel lInfo;
	public VariosJuegos(JFrame frame, String nombre)
	{
		super(frame, nombre);
		setLocation(100,50);
		setSize(200,200);
		setVisible(true);
		setLayout(new BorderLayout());
		
		initComponents();
	}
	private void initComponents() 
	{
		zonaCentro();
		zonaSur();
	}
	private void zonaSur() 
	{
		
	}
	private void zonaCentro() 
	{
		lInfo = new JLabel(new ImageIcon("lanzarGrande.png"));
		add(lInfo, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	}

}
