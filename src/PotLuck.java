import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
/**
 * Lab04_potluck class
 * @author Onur Ertunc
 * @version 25.11.2020
 *
 */
public class PotLuck extends JFrame {
	
	// Instances of the PotLuck class
	
	// Panels
	private JPanel textPanel;
	private JPanel potPanel;
	
	// data, row and counter
	private int rows;
	private int columns;
	private int counter;
	
	// to keep the place of prize, bomb1 and bomb2
	private int prize;
	private int bomb1;
	private int bomb2;
	
	// status field
	private JTextField statusField;
	
	// buttons
	private JButton bomb1Button;
	private JButton bomb2Button;
	private JButton prizeButton;
	
	/**
	 * constructor of the potLuck class
	 * @param rows
	 * @param columns
	 */
	public PotLuck( int rows, int columns ) {
		
		// set rows and columns
		this.rows = rows;
		this.columns = columns;
		
		// call the setFrame method to instantiate the frame
		setFrame();
		
		// call the status method
		status();
		
		// call the button builder method
		buttonBuilder();
		
		// placing the panels onto their places based on the sample screenshots
		add( textPanel, BorderLayout.NORTH );
		add( potPanel, BorderLayout.CENTER );
		
		
	}
	
	/**
	 * setFrame method keeps the implementation for the initialization of the frame
	 */
	public void setFrame() {
		
		// Set title
		setTitle( "Button Matrix Game");
		setBackground(Color.green);
		// layout decision.  status field will be in NORTH and buttonpanel will be in the center 
		setLayout( new BorderLayout(rows,columns) );
		
		// set size of the frame and default close operation
		setSize( 1000, 1000 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	
	/**
	 * status method arranges the text field and adds it onto the related panel textPanel
	 */
	public void status() {
		
		// the panel to keep the textfield 
		textPanel = new JPanel();
		textPanel.setBackground(Color.yellow);
		
		// Text field that shows the status
		statusField = new JTextField("", 20);
		
		// adjustments in it
		if ( this.counter == 0 ) {
			statusField.setText( "0" );
		}
		else {
			statusField.setText( "You clicked " + this.counter + " times.\n" );
		}
		
		// statusField added to the textpanel to be ready to placed in onto NORTH in the frame
		textPanel.add( statusField );
		statusField.setEditable(false);
		
	}
	
	/**
	 * button builder method of the PotLuck class.
	 */
	public void buttonBuilder() {
		
		// pot panel
		potPanel = new JPanel();
		potPanel.setLayout( new GridLayout(this.rows, this.columns, rows, columns) );
		
		// Action listener 
		ActionListener handler = new TheHandler();
		
		// max and min for the places
		int max = this.rows * this.columns;
		int min = 1;
		
		// instantiate counter
		counter = 0;
		
		// instantiate bomb1, bomb2 and prize
		bomb1 = 0;
		bomb2 = 0;
		prize = 0;
		
		// button building
		while ( prize == bomb1 || prize == bomb2 || bomb1 == bomb2 ) {
			prize = (int) ( Math.random() * (max - min + 1) + min );
			bomb1 = (int) ( Math.random() * (max - min + 1) + min );
			bomb2 = (int) ( Math.random() * (max - min + 1) + min );
		}
		
		for ( int i = 1; i <= max; i++ ) {
			if ( prize == i ) {
				prizeButton = new JButton();
				prizeButton.setSize(30,30);
				prizeButton.addActionListener( handler );
				potPanel.add( prizeButton );
			}
			else if ( bomb1 == i ) {
				bomb1Button = new JButton();
				bomb1Button.setSize(30,30);
				bomb1Button.addActionListener( handler );
				potPanel.add( bomb1Button );
			}
			else if ( bomb2 == i ) {
				bomb2Button = new JButton();
				bomb2Button.setSize(30,30);
				bomb2Button.addActionListener( handler );
				potPanel.add( bomb2Button );
			}
			else {
				JButton tempButton = new JButton();
				tempButton.setSize(30,30);
				tempButton.addActionListener( handler );
				potPanel.add( tempButton );
			}
		}
	}
	
	/**
	 * TheHandler class that implements the ActionListener for the game
	 * @author Onur Ertunc
	 *
	 */
	private class TheHandler implements ActionListener {
		
		/**
		 * @override actionPerformed
		 */
		public void actionPerformed( ActionEvent event ) {
			
			if ( event.getSource() != prizeButton && event.getSource() != bomb1Button && event.getSource() != bomb2Button ) {
				counter++;
				
				// to disable the pressed button
				( (JButton) event.getSource() ).setEnabled( false );
				statusField.setText( "You clicked " + counter + " times.\n" );
			}
			else if ( event.getSource() == prizeButton ) {
				statusField.setText( "Hurray!! Prize found in " + (counter + 1) + " times.\n" );
				for ( int i = 0; i < (rows*columns); i++ ) {
					if ( potPanel.getComponent(i) != prizeButton ) {
						( (JButton) potPanel.getComponent(i) ).setEnabled(false);
					}
				}
			}
			else {
				statusField.setText( "Sorry! You are blown at attempt " + (counter + 1) );
				for ( int i = 0; i < (rows*columns); i++ ) {
					if ( potPanel.getComponent(i) != prizeButton || potPanel.getComponent(i) != bomb1Button || potPanel.getComponent(i) != bomb2Button ) {
						( (JButton) potPanel.getComponent(i) ).setEnabled(false);
					}
				}
			}
		}
	}
}
