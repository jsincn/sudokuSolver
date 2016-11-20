import java.util.Scanner;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * 
 */

/**
 * @author David Sudoku solving algorithm
 *
 */

public class sudoku {

	private static Scanner sc = new Scanner(System.in);
	static public int set_size = 9;
	static int[][] smatrix = new int[set_size][set_size];
	static private int[][][] boxes = new int[3][3][9];
	static public boolean verbose = true;
	static public boolean detailed_verbose = false;
	static public boolean slow_mode = true;
	static public int time_delay = 1;
	static public boolean advanced = false;
	static public boolean graphic_input = true;
	static public String version = "1.0.5 Build 38";
	static public String logo = "     _______. __    __   _______   ______    __  ___  __    __  \r\n    /       ||  |  |  | |       \\ /  __  \\  |  |/  / |  |  |  | \r\n   |   (----`|  |  |  | |  .--.  |  |  |  | |  '  /  |  |  |  | \r\n    \\   \\    |  |  |  | |  |  |  |  |  |  | |    <   |  |  |  | \r\n.----)   |   |  `--'  | |  '--'  |  `--'  | |  .  \\  |  `--'  | \r\n|_______/     \\______/  |_______/ \\______/  |__|\\__\\  \\______/  \r\n                                                                ";
	static public String sub_text = "     _______.  ______    __      ____    ____  _______ \r\n    /       | /  __  \\  |  |     \\   \\  /   / |   ____|\r\n   |   (----`|  |  |  | |  |      \\   \\/   /  |  |__   \r\n    \\   \\    |  |  |  | |  |       \\      /   |   __|  \r\n.----)   |   |  `--'  | |  `----.   \\    /    |  |____ \r\n|_______/     \\______/  |_______|    \\__/     |_______|\r\n                                                       \r\n";
	
	public static void setup(){
		boolean achoice = true;
		while(achoice){
			System.out.print("Enable advanced mode [y/n]: ");
			String choice = sc.next();
			if(choice.equals("y")){
				advanced = true;
				achoice = false;
			} else if(choice.equals("n")){
				advanced = false;
				achoice = false;
			} else {
				// Do nothing
			}
		}
		if(advanced){
			boolean vchoice = true;
			while(vchoice){
				System.out.print("Enable verbose mode [y/n]: ");
				String choice = sc.next();
				if(choice.equals("y")){
					verbose = true;
					vchoice = false;
				} else if(choice.equals("n")){
					verbose = false;
					vchoice = false;
				} else {
					// Do nothing
				}
			}
			
			boolean gchoice = true;
			while(gchoice){
				System.out.print("Enable  Graphical Input mode [y/n]: ");
				String choice = sc.next();
				if(choice.equals("y")){
					graphic_input = true;
					gchoice = false;
				} else if(choice.equals("n")){
					graphic_input = false;
					gchoice = false;
				} else {
					// Do nothing
				}
			}
			if(verbose){
				boolean vdchoice = true;
				while(vdchoice){
					System.out.print("Enable detailed verbose mode [y/n]: ");
					String choice = sc.next();
					if(choice.equals("y")){
						detailed_verbose = true;
						vdchoice = false;
					} else if(choice.equals("n")){
						detailed_verbose = false;
						vdchoice = false;
					} else {
						// Do nothing
					}
				}
				
				


			}
			boolean schoice = true;
			while(schoice){
				System.out.print("Enable step-by-step mode [y/n]: ");
				String choice = sc.next();
				if(choice.equals("y")){
					slow_mode = true;
					schoice = false;
				} else if(choice.equals("n")){
					slow_mode = false;
					schoice = false;
				} else {
					// Do nothing
				}
			}
			if(slow_mode){
				boolean tchoice = true;
				while(tchoice){
					System.out.print("Enable set time delay (1-10 seconds) [1-10]: ");
					String choice = sc.next();
					try {
						int time_delay_value = Integer.parseInt(choice);
						if (time_delay_value <= 10 && time_delay_value >= 1) {
							time_delay = time_delay_value;
							tchoice = false;
						} else {
							System.err.println("Invalid Time Entered!");
						}
					} catch (NumberFormatException e) {
						System.err.println("Invalid Input Exception, please reenter!\n");
					}
					
				}
			}

		}
		AnsiConsole.systemInstall();
		System.out.print(ansi().fg(YELLOW).a("[").reset());
		for(int i = 0;i<50;i++){
			System.out.print(ansi().fg(YELLOW).a("=").reset());
			try {
			    Thread.sleep(30);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		
		System.out.print(ansi().fg(YELLOW).a("]").reset());
		System.out.println();
		System.out.print(ansi().fg(YELLOW).bold().a("Starting SudokuSolver v. " + version + " with the following setting:\n").reset());
		System.out.print(ansi().fg(GREEN).bold().a("Advanced Mode: " + advanced + "\n").reset());
		System.out.print(ansi().fg(GREEN).bold().a("Step-by-Step Mode: " + slow_mode + "\n").reset());
		System.out.print(ansi().fg(GREEN).bold().a("Time delay: " + time_delay + "\n").reset());
		System.out.print(ansi().fg(GREEN).bold().a("Verbose Mode: " + verbose + "\n").reset());
		System.out.print(ansi().fg(GREEN).bold().a("Detailed Verbose Mode: " + detailed_verbose + "\n").reset());
		
		System.out.print("\n\nPlease input the sudoku puzzle by entering each cell as indicated. Please type 0 for a blank space:\n");
		AnsiConsole.systemUninstall();
	}
	
	public static void main(String[] args) {

		AnsiConsole.systemInstall();
		
		System.out.println(ansi().fg(YELLOW).bold().a(logo));
		System.out.println(ansi().a(sub_text).bold().reset());
		// TODO Auto-generated method stub
		System.out.print(ansi().fg(YELLOW).bold().a("SudokuSolve " + version).fgBright(CYAN).boldOff().a("\nPlease follow any on-screen instructions.\n\n\n").reset());
		System.out.print(ansi().bold().a("Based on code by David.\n9x9 Sudoku implementation by Jakob.\n").reset());
		AnsiConsole.systemUninstall();
		setup();
		
		System.out.println();
		
		input();
		
		if(set_size == 9){
			for (int box_x = 0; box_x < 9; box_x+=3){
				for (int box_y = 0; box_y < 9; box_y+=3){
					boxes[box_y/3][box_x/3][0] = smatrix[box_y+0][box_x+0];
					boxes[box_y/3][box_x/3][1] = smatrix[box_y+0][box_x+1];
					boxes[box_y/3][box_x/3][2] = smatrix[box_y+0][box_x+2];
					boxes[box_y/3][box_x/3][3] = smatrix[box_y+1][box_x+0];
					boxes[box_y/3][box_x/3][4] = smatrix[box_y+1][box_x+1];
					boxes[box_y/3][box_x/3][5] = smatrix[box_y+1][box_x+2];
					boxes[box_y/3][box_x/3][6] = smatrix[box_y+2][box_x+0];
					boxes[box_y/3][box_x/3][7] = smatrix[box_y+2][box_x+1];
					boxes[box_y/3][box_x/3][8] = smatrix[box_y+2][box_x+2];
				}
			}
		}
		AnsiConsole.systemInstall();
		System.out.println(ansi().fgBrightYellow().a("Inputted Sudoku Puzzle:").reset());
		AnsiConsole.systemUninstall();
		printSudoku(true);
		
		// check whether set_sizexset_size Sudoku is valid
		boolean validity = true;
		int sum = 0;
		for (int number = 1; number <= set_size; number++) {
			// check rows
			for (int row = 0; row < set_size; row++) {
				for (int checkcolumn = 0; checkcolumn < set_size; checkcolumn++) {
					if (smatrix[row][checkcolumn] == number) {
						sum++;
					}
				}
				if (sum > 1) {
					validity = validity && false;
				}
				sum = 0;
			}
			// check columns
			for (int column = 0; column < set_size; column++) {
				for (int checkrow = 0; checkrow < set_size; checkrow++) {
					if (smatrix[checkrow][column] == number) {
						sum++;
					}
				}
				if (sum > 1) {
					validity = validity && false;
				}
				sum = 0;
				}
		}
		if (validity == true) {
			System.out.println("Sudoku accepted!");
			System.out.println("Starting to solve...");
		
			// Solve Sudoku
			validity = true;
			boolean ready = false;
			int hit = 0;
			while (ready == false && validity == true) {
				for (int row = 0; row < set_size; row++) {
					for (int column = 0; column < set_size; column++) {
						if (smatrix[row][column] == 0) {
							// Try to fill the gap
							for (int number = 1; number <= set_size; number++) {
								// check row
								int hitr = 1;
								for (int checkrow = 0; checkrow < set_size; checkrow++) {
									if (smatrix[checkrow][column] != number) {
										hitr = hitr * 1;
									}
									else
									{
										hitr = hitr * 0;
									}
								}
								// check column
								for (int checkcolumn = 0; checkcolumn < set_size; checkcolumn++) {
									if (smatrix[row][checkcolumn] != number) {
										hitr = hitr * 1;
									}
									else
									{
										hitr = hitr * 0;
									}
								}
								//check box
								if(set_size == 9){
									for (int checkbox = 0; checkbox < set_size; checkbox++) {
										if (boxes[row/3][column/3][checkbox] != number) {
											hitr = hitr * 1;
										}
										else
										{
											hitr = hitr * 0;
										}
									}
								}
								if (hitr == 1 && hit == 0)
								{
									hit = number;
								}
								else if (hitr == 1 && hit != 0)
								{
									hit = set_size+1;
								}
							}
							// handle hits
							if (hit == 0) {
								validity = false;
								smatrix[row][column] = set_size;
								System.out.println("Failed to solve");
		
							} else if (hit != set_size+1) {
								smatrix[row][column] = hit;
								if(set_size == 9){
									for (int box_x = 0; box_x < 9; box_x+=3){
										for (int box_y = 0; box_y < 9; box_y+=3){
											boxes[box_y/3][box_x/3][0] = smatrix[box_y+0][box_x+0];
											boxes[box_y/3][box_x/3][1] = smatrix[box_y+0][box_x+1];
											boxes[box_y/3][box_x/3][2] = smatrix[box_y+0][box_x+2];
											boxes[box_y/3][box_x/3][3] = smatrix[box_y+1][box_x+0];
											boxes[box_y/3][box_x/3][4] = smatrix[box_y+1][box_x+1];
											boxes[box_y/3][box_x/3][5] = smatrix[box_y+1][box_x+2];
											boxes[box_y/3][box_x/3][6] = smatrix[box_y+2][box_x+0];
											boxes[box_y/3][box_x/3][7] = smatrix[box_y+2][box_x+1];
											boxes[box_y/3][box_x/3][8] = smatrix[box_y+2][box_x+2];
										}
									}
								}
								printSudoku(false);
								if(slow_mode == true){
									try {
									    Thread.sleep(time_delay*1000);
									} catch(InterruptedException ex) {
									    Thread.currentThread().interrupt();
									}
								}
							} else {
								if(detailed_verbose == true){
									System.out.println("Multiple Possibilities");
								}
							}
							hit = 0;
						} else {
							// Skip
						}
		
					}
				}
				// Check whether the puzzle has been solved
				ready = true;
				for (int r = 0; r < set_size; r++) {
					for (int c = 0; c < set_size; c++) {
						if (smatrix[r][c] == 0) {
							ready &= false;
						}
		
					}
				}
			}
			if (validity == false) {
				System.err.println("The inputed sudoku can't be solved.");
			} else {
				System.out.println("Done:");
				printSudoku(true);
			}
			} else {
				System.err.println("The entered sudoku is not a standard 9x9 Sudoku and can't be solved.");
			}
		
	}
	
	static void input(){
		String input;
		int parity = 0;
		int value;
		for (int zeilen = 0; zeilen < set_size; zeilen++) {
			for (int spalten = 0; spalten < set_size; spalten++) {
				while (parity != 1) {
					try {
						smatrix[zeilen][spalten] = -1;
						if(graphic_input){
							printSudoku(true);
						}
						input = sc.next();
						try {
							value = Integer.parseInt(input);
							if (value <= set_size && value >= 0) {
								smatrix[zeilen][spalten] = value;
								
								
								parity = 1;
								
							} else {
								System.err.println("Invalid Input Exception, please reenter!\n");
							}
						} catch (NumberFormatException e) {
							System.err.println("Invalid Input Exception, please reenter!\n");
						}
					} catch (java.util.InputMismatchException e) {
						e.printStackTrace();
					}
				}
				parity = 0;
			}
		}
	}
	
	static void printSudoku(boolean necessary) {
		if(verbose == true || necessary == true){
			for(int i=0;i<set_size*2;i++){
				System.out.print("--");
			}
			System.out.println("-");
			for (int zeilen = 0; zeilen < set_size; zeilen++) {
				System.out.print("| ");
				for (int spalten = 0; spalten < set_size; spalten++) {
					if (smatrix[zeilen][spalten] == 0) {
						System.out.print(" ");
					} else if (smatrix[zeilen][spalten] == set_size+1) {
						System.err.print("X");
					} else if (smatrix[zeilen][spalten] == -1) {
						System.out.print("█");
					} else {
						System.out.print(smatrix[zeilen][spalten]);
					}
					System.out.print(" | ");
				}
				System.out.print("\n");
				for(int i=0;i<set_size*2;i++){
					System.out.print("--");
				}
				System.out.print("-\n");
			}
		System.out.print("\n");
		}
	}

}
