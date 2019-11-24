package Game;
import java.io.*;
import java.util.*;

public class Save {
	//Đây là phương thức giúp địch đi đúng hướng đã định sẵn ở Mission
	public void loadSave(InputStream loadPath){
		try{
		Scanner loadScanner = new Scanner(loadPath);
		while(loadScanner.hasNext()){
			//địch tìm đường để đi
			for(int y = 0; y < ActionGame.room.block.length; y++){
				for(int x = 0; x < ActionGame.room.block[0].length; x++){
					ActionGame.room.block[y][x].groundID = loadScanner.nextInt();
				}
			}

			for(int y=0; y < ActionGame.room.block.length; y++){
				for(int x=0; x < ActionGame.room.block[0].length;x++){
					ActionGame.room.block[y][x].airID = loadScanner.nextInt();
				}
			}
		}	
		loadScanner.close();
		} catch(Exception e){System.out.println(e.getMessage());}
	}
}
