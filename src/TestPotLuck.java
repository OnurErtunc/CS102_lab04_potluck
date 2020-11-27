import java.util.Scanner;
import javax.swing.JFrame;
/**
 * test class of the PotLuck Game
 * @author Onur Ertunc
 * @version 26.11.2020
 *
 */
public class TestPotLuck {

	public static void main(String[] args) {
		Scanner scan = new Scanner( System.in );
		
		// variables 
		int row;
		int column;
		JFrame onur;
		
		// program code
		System.out.print( "Please insert the dimension of the pot luck game: " );
		row = scan.nextInt();
		column = row;
		
		onur = new PotLuck( row, column );
		onur.setVisible( true );
		scan.close();
	}

}
