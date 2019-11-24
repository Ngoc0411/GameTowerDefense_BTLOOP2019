package Game;
import java.awt.*;

public class Store {
	public static int shopWidth = 4; // số lượng button chọn mua trong shop
	public static int buttonSize = 62; //52
	public static int cellSpace = 2;
	public static int shopSpacing = 25;
	public static int iconSize = 25;
	public static int iconSpace = 3;
	public static int iconTextY = 7;
	public static int itemIn = 4;
	public static int heldID = -1;
	public static int realID = -1;
	
	public static int[] buttonID = {Value.airTowerLase,Value.airTowerFrost,Value.airAir,Value.airTrash};
 										//tháp đỏ, tháp băng, ô button trống, Thùng rác
	public static int[] buttonPrice = {10,15,0,0};

	public Rectangle[] button = new Rectangle[shopWidth];

	public Rectangle buttonHealth;
	public Rectangle buttonCoins;
	
	public boolean holdsItem = false;
	
	public Store(){
		define();
	}
	
	public void click(int mouseButton){
		if(mouseButton == 1){
			for(int i = 0; i < button.length; i++){
				if (button[i].contains(ActionGame.mse)){
					if(buttonID[i] != Value.airAir){
						if(buttonID[i] == Value.airTrash){// Xóa các mục trong kho
							// Chức năng lỡ nhấn chọn mua nhưng ko mua nữa
							holdsItem = false;
							heldID = Value.airAir;
						}
						else{
							heldID = buttonID[i];
							realID = i;
							holdsItem = true;
						}
					}
				}
			}
			// Vẽ button tháp lên màn hình
			if(holdsItem){
				if(ActionGame.coinage >= buttonPrice[realID]){
					for(int y =0;y<ActionGame.room.block.length;y++){
						for(int x =0;x<ActionGame.room.block[0].length;x++){
						if(ActionGame.room.block[y][x].contains(ActionGame.mse)){
							// Điều kiện để vẽ được tháp : Ko vẽ lên đường :))
							if(ActionGame.room.block[y][x].groundID != Value.groundRoad && ActionGame.room.block[y][x].airID == Value .airAir){
								ActionGame.room.block[y][x].airID = heldID; // Cái room bây giờ sẽ chứa tháp
								ActionGame.coinage -= buttonPrice[realID]; // trừ tiền
								holdsItem = false;                         // vẽ rồi ko vẽ nữa
							}
						}
					 }
					}
				}
				
			}
		}
	
	
	}
	
	public void define(){
		for(int i=0;i < button.length;i++){
			button[i] = new Rectangle((ActionGame.myWidth/2 )- ((shopWidth * (buttonSize + cellSpace))/2) + ((buttonSize + cellSpace)*i), (ActionGame.room.block[ActionGame.room.worldHeight-1][0].y) + (ActionGame.room.blockSize + shopSpacing),buttonSize,buttonSize);
		}
		
		buttonHealth = new Rectangle(ActionGame.room.block[0][0].x -1, button[0].y,iconSize,iconSize);
		buttonCoins = new Rectangle(ActionGame.room.block[0][0].x -1, button[0].y + button[0].height - iconSize,iconSize,iconSize);
	}
	
	public void draw(Graphics g){
		
		for(int i=0;i < button.length;i++){

			//Vẽ tiền
			g.drawImage(ActionGame.tileset_symbol[0],button[i].x, button[i].y, button[i].width, button[i].height,null);

			//Vẽ các tháp lên cửa hàng
			if(buttonID[i] != Value.airAir){
				g.drawImage(ActionGame.tileset_item[buttonID[i]],button[i].x + itemIn - 5, button[i].y + itemIn - 5 , button[i].width - (itemIn*2) + 5, button[i].height - (itemIn*2) + 5,null);
			}

			//Vẽ giá tiền của tháp
			if(buttonPrice[i] > 0){
				g.setFont(new Font("Arial",Font.BOLD,14));
				g.setColor(new Color(255,255,255));
				g.drawString(" $" + buttonPrice[i] + "", button[i].x + itemIn + 10, button[i].y + itemIn + button[i].height - 12);
			}

		}

		g.setFont(new Font("Arial",Font.BOLD,14));
		g.setColor(new Color(255,255,255));

		//Vẽ trái tim và lượng sức khoẻ còn lại
		g.drawImage(ActionGame.tileset_symbol[2],buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height,null);
		g.drawString("" + ActionGame.health, buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + buttonHealth.height + iconSpace -iconTextY);// Draw Health
		//Vẽ tiền vàng còn lại
		g.drawImage(ActionGame.tileset_symbol[1],buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height,null);
		g.drawString("" + ActionGame.coinage, buttonCoins.x + buttonCoins.width + iconSpace, buttonCoins.y + buttonCoins.height + iconSpace -iconTextY);//Draw Coins

		// set font chữ và màu chữ
		g.setFont(new Font("Arial",Font.BOLD,14));
		g.setColor(new Color(255,255,255));
		// set chữ level
		g.drawString("Level: " + ActionGame.level , button[button.length -1].x + 100, buttonHealth.y + buttonHealth.height + iconSpace -iconTextY);//Draw Level

		if(holdsItem){
			g.drawImage(ActionGame.tileset_item[heldID],ActionGame.mse.x - ((button[0].width -(itemIn*2))/2)+ itemIn, ActionGame.mse.y - ((button[0].width -(itemIn*2))/2) + itemIn, button[heldID].width - (itemIn*2), button[heldID].height - (itemIn*2),null);
		}

	}
}
