import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class gameboard extends JPanel{

	char board[][]={{'-','-','-'},
					{'-','-','-'},
					{'-','-','-'}};
	
	int level=1;
	Image oimg;
	Image ximg;
	int gm =0;
	int dx1,dy1,dx2,dy2;
	
	gameboard(){
		try {
			oimg =ImageIO.read(new File("oimage.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			ximg =ImageIO.read(new File("ximage.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		addMouseListener(
				new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						int x = e.getX()/100;
						int y = e.getY()/100;
						
						if(board[x][y]=='-' && gm==0){
							board[x][y]='o';
							
							if(evaluatez()==-10)
								gm=1;
							
							if(gm==0 && ismovesleft())
								compfirstturn();
							
							if(evaluatez()==10)
								gm=-1;
							
						}
						
						repaint();
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					
				}
				
				
		);
	}
	
	private int evaluate(){
		
		//rows
		for(int i=0;i<3;i++){
			if(board[i][0]==board[i][1] && board[i][1]==board[i][2]){
				if(board[i][0]=='o')
					return -10;
				else if(board[i][0]=='x')
					return 10;
			}
		}
		
		//colums
		for(int i=0;i<3;i++){
			if(board[0][i]==board[1][i] && board[1][i]==board[2][i]){
				if(board[0][i]=='o')
					return -10;
				else if(board[0][i]=='x')
					return 10;
			}
		}
		
		//diagnals
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2]){
			if(board[0][0]=='o')
				return -10;
			else if(board[0][0]=='x')
				return 10;
		}
		
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0]){
			if(board[1][1]=='o')
				return -10;
			else if(board[1][1]=='x')
				return 10;
		}
		
		return 0;
	}
	
	private int minimax(int depth,boolean isMax){
		
		if(depth>=level-1)
			return 0;
			
		int score = evaluate();
		int besval,val;
		
		if(score == 10)
			return 10;
		
		if(score == -10)
			return -10;
		
		if(!ismovesleft())
			return 0;
		
		if(isMax){
			besval=-1000;
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					
					if(board[i][j]=='-'){
						
						board[i][j]='x';
						val = minimax(depth+1,!isMax);
						
						board[i][j] = '-';
						
						if(besval < val)
							besval = val;
					}
				}
			}
			return besval;
		}
		else{
			besval=1000;
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					
					if(board[i][j]=='-'){
						
						board[i][j]='o';
						val = minimax(depth+1,!isMax)-1;
						
						board[i][j] = '-';
						
						if(besval > val)
							besval = val;
					}
				}
			}
			return besval;
		}
		
	}
	
	
	private void compfirstturn() {
		int bestval = -1000, row = -1, col = -1,moveval;
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				
				if(board[i][j]=='-'){
					
					board[i][j]='x';
					moveval = minimax(0,false);
					
					board[i][j] = '-';
					
					if(moveval > bestval){
						row = i;
						col = j;
						bestval = moveval;
					}
				}
			}
		}
		board[row][col]='x';

	}
	
	private boolean ismovesleft(){
		for(int i=0; i<3;i++){
			for(int j=0;j<3;j++){
				if(board[i][j]=='-')
					return true;
			}
		}
		return false;
	}
	
	public void reset(int l){
	   for(int i=0;i<3;i++){
		   for(int j=0;j<3;j++){
			   board[i][j]='-';
		   }
	   }
	   level =l;
	   gm=0;
	   repaint();
	}
	
	private int evaluatez(){
		
		//rows
		for(int i=0;i<3;i++){
			if(board[i][0]==board[i][1] && board[i][1]==board[i][2]){
				dx1 = i; 	dy1=0;
				dx2 = i;	dy2=2;
				
				if(board[i][0]=='o')
					return -10;
				
				else if(board[i][0]=='x')
					return 10;
				
			}
		}
		
		//colums
		for(int i=0;i<3;i++){
			if(board[0][i]==board[1][i] && board[1][i]==board[2][i]){
				dx1 = 0; 	dy1=i;
				dx2 = 2;	dy2=i;
				
				if(board[0][i]=='o')
					return -10;
				else if(board[0][i]=='x')
					return 10;
			}
		}
		
		//diagnals
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2]){
			dx1 = 0; 	dy1=0;
			dx2 = 2;	dy2=2;
			
			if(board[0][0]=='o')
				return -10;
			else if(board[0][0]=='x')
				return 10;
		}
		
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0]){
			dx1 = 0; 	dy1=2;
			dx2 = 2;	dy2=0;
			
			if(board[1][1]=='o')
				return -10;
			else if(board[1][1]=='x')
				return 10;
		}
		
		return 0;
	}
	
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 300, 300);
		
		g2d.setColor(Color.BLACK);
		g2d.drawLine(0, 100, 300, 100);
		g2d.drawLine(0, 200, 300, 200);
		g2d.drawLine(100, 0, 100, 300);
		g2d.drawLine(200, 0, 200, 300);
		
		g2d.drawLine(0, 0, 0, 300);
		g2d.drawLine(0, 0, 300, 0);
		g2d.drawLine(300, 0, 300, 300);
		g2d.drawLine(0, 300, 300, 300);
		
		for(int i=0;i<3;i++){
			for(int j=0; j<3; j++){
				if(board[i][j]=='o')
					g2d.drawImage(oimg, 100*i+1, 100*j+1, 99, 99, null);
				else if(board[i][j]=='x')
					g2d.drawImage(ximg, 100*i+1, 100*j+1, 99, 99, null);
				
			
			}
		}
		
		
		
		if(gm==1){
			g2d.drawLine(dx1*100+50, dy1*100+50, dx2*100+50, dy2*100+50);
			g2d.setColor(Color.yellow);
			g2d.fillRect(100, 140, 100, 20);
			g2d.setColor(Color.red);
			g2d.drawString("Player wins", 110, 155);
		}
		else if(gm==-1){
			g2d.drawLine(dx1*100+48, dy1*100+48, dx2*100+48, dy2*100+48);
			g2d.setColor(Color.yellow);
			g2d.fillRect(100, 140, 100, 20);
			g2d.setColor(Color.red);
			g2d.drawString("Computer wins", 110, 155);
			
		}
		
		
		if(!ismovesleft()){
			g2d.setColor(Color.yellow);
			g2d.fillRect(100, 140, 100, 20);
			g2d.setColor(Color.red);
			g2d.drawString("Draw :(", 130, 155);

		}
	}
		
	
			
}
