package Game;
import java.awt.*;

public class Block extends Rectangle{
	// class như 1 cái khuôn

	public Rectangle towerSquare;
	public int towerSquareSize =130;
	public int groundID;
	public int airID;
	
	public int loseTime = 100 ,loseFrame = 0;
	public int shotMob = -1;
	public boolean isShooting = false;
	public int towerDamage = 1;

	public Block(int x, int y, int width, int height, int groundID,int airID) {
		setBounds(x , y, width, height);
		towerSquare = new Rectangle(x - (towerSquareSize/2), y- (towerSquareSize/2), width + (towerSquareSize), height + (towerSquareSize));
		this.groundID = groundID;
		this.airID = airID;
	}

	public void draw(Graphics g){
		//g.drawRect(x, y, width, height);
		g.drawImage(ActionGame.tileset_groundGrass[groundID],x, y, width, height,null);
		//Vẽ tháp và nhà

		if(airID == 0 )
			g.drawImage(ActionGame.tileset_item[airID],x -25, y - 25, width + 30, height + 25,null);

		if( airID == 2 || airID == 3)
			g.drawImage(ActionGame.tileset_item[airID],x, y, width, height,null);
	}
	
	public void physic(){
		//phương thức intersects trả về true nếu 2 hình chữ nhật giao nhau và false cho trường hợp còn lại.
		if(shotMob != -1 && towerSquare.intersects(ActionGame.enemys[shotMob]))
			isShooting = true;
		else isShooting = false;


		if(!isShooting){
			if (airID == Value.airTowerLase || airID ==  Value.airTowerFrost){
				for(int i=0;i<ActionGame.enemys.length;i++){
					if(ActionGame.enemys[i].inGame){
						if(towerSquare.intersects(ActionGame.enemys[i])){
							isShooting = true;
							shotMob = i;
						}
					}
				}
			}
		}

		if(isShooting){
			if(loseFrame >= loseTime){
				if(airID ==  Value.airTowerFrost){
					ActionGame.enemys[shotMob].slow();
					ActionGame.enemys[shotMob].gettingShot = true;
				}else{
					ActionGame.enemys[shotMob].loseHealth();
				}
				loseFrame = 0;
			}else{
				loseFrame += 1;
			}
			
			if(ActionGame.enemys[shotMob].isDead() && !ActionGame.enemys[shotMob].inGame){
				isShooting = false;
				shotMob = -1;
			}
		}
	}

	// Vẽ tia đạn laze
	public void fight(Graphics g){
		if(ActionGame.store.holdsItem){
			if(airID == Value.airTowerLase || airID == Value.airTowerFrost){
				g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
			}
		}
		if(isShooting){
			//Nếu như là đạn laze:
			if(airID == Value.airTowerLase){
			g.setColor(new Color(240,20,20));
			g.drawLine(x + (width/2), y + (height/2), ActionGame.enemys[shotMob].x + (ActionGame.enemys[shotMob].width/2), ActionGame.enemys[shotMob].y + (ActionGame.enemys[shotMob].height/2));
			}
			//Còn nếu là đạn băng
			else if(airID == Value.airTowerFrost){
				g.setColor(new Color(150, 255, 255));
				g.drawLine(x + (width/2), y + (height/2), ActionGame.enemys[shotMob].x + (ActionGame.enemys[shotMob].width/2), ActionGame.enemys[shotMob].y + (ActionGame.enemys[shotMob].height/2));
			}
		}
	}
}
