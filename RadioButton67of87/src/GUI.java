import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{
	private Font pf;
	private Font bf;
	private Font itf;
	private Font bif;	
	private JTextField tf;
	private JRadioButton pb;
	private JRadioButton bb;
	private JRadioButton ib;
	private JRadioButton bib;
	private ButtonGroup group;
	
	
	GUI(){
		super("Radio Button");
		setLayout(new FlowLayout());
		
		tf = new JTextField("P90X Rocks... Bring It!!!!", 25);
		add(tf);
		
		pb = new JRadioButton("plain", true);
		bb = new JRadioButton("bold", false);
		ib = new JRadioButton("italic", false);
		bib = new JRadioButton("bold and italic", false);
		add(pb);
		add(bb);
		add(ib);
		add(bib);
		
		group = new ButtonGroup();
		group.add(pb);
		group.add(bb);
		group.add(ib);
		group.add(bib);
		
		pf = new Font("Serif", Font.PLAIN , 14);
		bf = new Font("Serif", Font.BOLD , 14); 
		itf = new Font("Serif", Font.ITALIC , 14);
		bif = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
		tf.setFont(pf);
		
		//wait for event to happen, pass in font object for constructor
		pb.addItemListener(new HandlerClass(pf));
		bb.addItemListener(new HandlerClass(bf));
		ib.addItemListener(new HandlerClass(itf));
		bib.addItemListener(new HandlerClass(bif));
	}
	
	private class HandlerClass implements ItemListener{
		private Font font;
		
		HandlerClass(Font f){
			font = f;
		}
		
		public void itemStateChanged(ItemEvent ie){
			tf.setFont(font);
		}
		
	}
	
}
