import java.awt.Color;

import javax.swing.JButton;

public class Cell extends JButton{
	public boolean isAlive;
	public Cell(boolean isAlive) {
		this.isAlive = isAlive;
		if(isAlive) {
			setBackground(Color.black);
		}
		else {
			setBackground(Color.white);
		}
	}
	public void update(boolean isAlive) {
		this.isAlive = isAlive;
		if(isAlive) {
			setBackground(Color.black);
		}
		else {
			setBackground(Color.white);
		}
	}
	public Cell copy() {
		return new Cell(this.isAlive);
	}
}
