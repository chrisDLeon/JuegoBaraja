package componentes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class tablaYgrafica extends JDialog implements ActionListener 
{ 
	private static final long serialVersionUID = 1L;
	public tablaYgrafica(JFrame frame, String nombre) 
	{
		super(frame,nombre);
		setLayout(new GridLayout(2,1));
		setLocation(150,150);
		setSize(400,400);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}

}
