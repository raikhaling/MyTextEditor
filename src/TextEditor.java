import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextEditor extends JFrame implements ActionListener{
	
	JTextArea textArea;
	JScrollPane scrollPane;
	JLabel fontLabel;
	JSpinner fontSizeSpinner;
	JButton fontColorButton;
	JComboBox fontBox;
	
	TextEditor(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Text Editor");
		this.setSize(600, 600);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null); //makes window appear in the middle of screen
		
		textArea = new JTextArea();		
		textArea.setLineWrap(true); //if lines to long break into another line .ie reached rightmost 
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(550, 550));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		fontLabel = new JLabel("Font:");
		
		fontSizeSpinner = new JSpinner(); 
		fontSizeSpinner.setPreferredSize(new Dimension(60, 35));
		fontSizeSpinner.setValue(30);
		fontSizeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				 textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN,(int)fontSizeSpinner.getValue()));
				
			}
			
		});
		
		fontColorButton = new JButton("Color");
		fontColorButton.addActionListener(this);
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();//get all fonts available local fonts 
//		for(String s:fonts) {
//			System.out.println(s);
//		}
		fontBox = new JComboBox(fonts);
		fontBox.addActionListener(this);
		fontBox.setSelectedItem("Arial");
		
		this.add(fontLabel);
		this.add(fontSizeSpinner); 
		this.add(fontColorButton);
		this.add(fontBox);
		this.add(scrollPane);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fontColorButton) {
			JColorChooser colorChooser = new JColorChooser();
			
			Color color = colorChooser.showDialog(null, "Choose a color", Color.BLACK);
			
			textArea.setForeground(color);
			
		}		
		if(e.getSource() == fontBox) {
			textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
		}
	}

}
