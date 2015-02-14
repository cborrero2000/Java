import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Mov extends JFrame {
	private JList rightList;
	private JList leftList;
	private JButton movingButton;
	private static String[] foods = {"bacon", "potato", "chicken", "ham","cheese"};
	
	
	Mov(){
		super("Moving List");
		setLayout(new FlowLayout());
		
		leftList = new JList(foods);
		leftList.setVisibleRowCount(3);
		leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(leftList));
		
		movingButton = new JButton("Move -->");
		
		movingButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						rightList.setListData(leftList.getSelectedValuesList().toArray());
					}
			
				}
		);
		
		add(movingButton);
		rightList = new JList();
		rightList.setFixedCellHeight(15);
		rightList.setFixedCellWidth(100);
		rightList.setVisibleRowCount(3);
		rightList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(rightList));
		
	}
}
