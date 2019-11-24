package Game;

import Game.ActionGame;
import Game.GameStage;

import java.awt.event.*;
import java.awt.*;

public class KeyHandel implements MouseMotionListener, MouseListener {
	@Override
	public void mouseDragged(MouseEvent e) {
		ActionGame.mse = new Point(((e.getX()) - ((GameStage.size.width) - ActionGame.myWidth))/2,(e.getY()) - ((GameStage.size.height - (ActionGame.myHeight))/2));
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		ActionGame.mse = new Point( ((e.getX()) - ((GameStage.size.width - ActionGame.myWidth))/2),(e.getY()) - ((GameStage.size.height - (ActionGame.myHeight))/2));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		ActionGame.mse = new Point(((e.getX()) - ((GameStage.size.width - ActionGame.myWidth))/2),(e.getY()) - ((GameStage.size.height - (ActionGame.myHeight))/2));
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		ActionGame.mse = new Point(((e.getX()) - ((GameStage.size.width - ActionGame.myWidth))/2),(e.getY()) - ((GameStage.size.height - (ActionGame.myHeight))/2));
		ActionGame.store.click(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ActionGame.mse = new Point(((e.getX()) - ((GameStage.size.width - ActionGame.myWidth))/2),(e.getY()) - ((GameStage.size.height - (ActionGame.myHeight))/2));
		
	}

	
}
