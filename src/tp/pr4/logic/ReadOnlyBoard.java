package tp.pr4.logic;

public interface ReadOnlyBoard {

	public int getHeight();
	
	public Counter getPosition(int x, int y);
	
	public int getWidth();
	
}