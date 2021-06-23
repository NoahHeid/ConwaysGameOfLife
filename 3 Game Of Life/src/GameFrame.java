import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame {
	static int feldLaenge = 100;
	static int startendLebend = 6000;
	static int fps = 25;
	static Cell[] cells;
	public static void main(String[] args) {
		JFrame f = new JFrame("Game of Life");
		f.setSize(1000, 1000);
		f.setLayout(new GridLayout(feldLaenge, feldLaenge));
		
		cells = new Cell[feldLaenge*feldLaenge];
		initialisiereCells(f);
		
		f.setVisible(true);
		
		for(int zyklus = 0; zyklus<10000; zyklus++) {
			Cell[] copyCells = cells.clone();
			for(int i = 0; i<cells.length; i++) {
				copyCells[i] = cells[i].copy();
			}
			for(int i = 0; i<copyCells.length; i++) {
				if(copyCells[i].isAlive) {
					if(getLebendNachbarAnzahl(i, copyCells) < 2) {
						cells[i].update(false);
					}
					else if(getLebendNachbarAnzahl(i, copyCells) < 4) {
						cells[i].update(true);
					}
					else if(getLebendNachbarAnzahl(i, copyCells) > 3) {
						cells[i].update(false);
					}
				}
				else {
					if(getLebendNachbarAnzahl(i, copyCells) == 3) {
						cells[i].update(true);
					}
				}
					
			}
			
			
			
			try {
				Thread.sleep((int)(1000/fps));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	private static void initialisiereCells(JFrame f) {
		int[] randomAlives = new int[startendLebend];
		randomAlives = getRandomAlive(startendLebend);
		
		for(int i = 0; i<feldLaenge*feldLaenge; i++) {
			
			
			if(valueIsInArray(randomAlives, i)) {
				cells[i] = new Cell(true);
			}
			else {
				cells[i] = new Cell(false);
			}
			
			f.add(cells[i]);
		}
	}

	private static boolean valueIsInArray(int[] randomAlives, int i) {
		for(int x = 0; x<randomAlives.length; x++) {
			if(randomAlives[x]==(i)) {
				return true;
			}
		}
		return false;
	}

	private static int[] getRandomAlive(int i) {
		int[] solution = new int[i];
		for(int r = 0; r<i; r++) {
			int randomX = (int) (Math.random()*feldLaenge*feldLaenge);
			boolean rIsInY = false;
			for(int y = 0; y<r; y++) {
				if(randomX == solution[y]) {
					rIsInY = true;
					
				}
			}
			if(!rIsInY) {
				solution[r] = randomX;
				
			}
			else {
				r--;
			}
			
		}

		
		System.out.println(Arrays.toString(solution));
		
		return solution;
	}
	private static int getLebendNachbarAnzahl(int i, Cell[] copyCells) {
		int summe = 0;
		//Summe der oberen Reihe:
		if(i<feldLaenge) {
			if(i%feldLaenge == 0) {
				
				if(copyCells[i+1].isAlive)
					summe++;
				if(copyCells[i+1+feldLaenge].isAlive)
					summe++;
				if(copyCells[i+feldLaenge].isAlive) 
					summe++;
				
			}
			
			else if(i%feldLaenge==feldLaenge-1) {
				if(copyCells[i-1].isAlive)
					summe++;
				if(copyCells[i+feldLaenge].isAlive)
					summe++;
				if(copyCells[i-1+feldLaenge].isAlive)
					summe++;
			}
			
			else {
				if(copyCells[i-1].isAlive)
					summe++;
				if(copyCells[i+1].isAlive)
					summe++;
				if(copyCells[i-1+feldLaenge].isAlive)
					summe++;
				if(copyCells[i+feldLaenge].isAlive)
					summe++;
				if(copyCells[i+1+feldLaenge].isAlive)
					summe++;
			}
		}
		//Summe der untersten Reihe
		else if(i>=feldLaenge*(feldLaenge-1)) {
			//Ganz unten links
			if(i%feldLaenge == 0) {
				if(copyCells[i+1].isAlive)
					summe++;
				if(copyCells[i+1-feldLaenge].isAlive)
					summe++;
				if(copyCells[i-feldLaenge].isAlive) 
					summe++;	
			}
			//Ganz unten rechts
			else if(i%feldLaenge==feldLaenge-1) {
				if(copyCells[i-feldLaenge].isAlive)
					summe++;
				if(copyCells[i-feldLaenge-1].isAlive)
					summe++;
				if(copyCells[i-1].isAlive)
					summe++;
			}
			//Der Rest unten
			else {
				if(copyCells[i-1].isAlive)
					summe++;
				if(copyCells[i+1].isAlive)
					summe++;
				if(copyCells[i-1-feldLaenge].isAlive)
					summe++;
				if(copyCells[i-feldLaenge].isAlive)
					summe++;
				if(copyCells[i+1-feldLaenge].isAlive)
					summe++;
			}
		}
		//alle Reihen dazwischen!
		else {
			//Links
			if(i%feldLaenge== 0) {
				if(copyCells[i-feldLaenge].isAlive)
					summe++;
				if(copyCells[i+1-feldLaenge].isAlive)
					summe++;
				if(copyCells[i+1].isAlive)
					summe++;
				if(copyCells[i+1+feldLaenge].isAlive)
					summe++;
				if(copyCells[i+feldLaenge].isAlive) 
					summe++;
			}
			//Rechts
			else if(i%feldLaenge== feldLaenge-1) {
				if(copyCells[i-feldLaenge].isAlive)
					summe++;
				if(copyCells[i-1-feldLaenge].isAlive)
					summe++;
				if(copyCells[i-1].isAlive)
					summe++;
				if(copyCells[i-1+feldLaenge].isAlive)
					summe++;
				if(copyCells[i+feldLaenge].isAlive) 
					summe++;
			}
			else {
				if(copyCells[i-1].isAlive)
					summe++;
				if(copyCells[i+1].isAlive)
					summe++;
				if(copyCells[i-1-feldLaenge].isAlive)
					summe++;
				if(copyCells[i-feldLaenge].isAlive)
					summe++;
				if(copyCells[i+1-feldLaenge].isAlive)
					summe++;
				if(copyCells[i-1+feldLaenge].isAlive)
					summe++;
				if(copyCells[i+feldLaenge].isAlive)
					summe++;
				if(copyCells[i+1+feldLaenge].isAlive)
					summe++;
			}
		}
			
		return summe;
	}
}
