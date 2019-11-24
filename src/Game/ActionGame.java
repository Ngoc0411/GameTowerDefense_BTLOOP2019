package Game;

import Enemy.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class ActionGame extends JPanel implements Runnable{
    public Thread thread = new Thread(this);

    public static Image[] tileset_groundGrass = new Image[2];
    public static Image[] tileset_item = new Image[4];
    public static Image[] tileset_symbol = new Image[3];    // Biểu tượng : vd heart, coin, cell : ô vuông chứa tháp bán
    public static Image[] tileset_enemy = new Image[100];

    public static int myWidth, myHeight;
    public static boolean isFirst = true;

    public static int killCount = 0;

    public static GameField room;
    public static Save save;
    public static Point mse = new Point(0,0);
    public static Store store;

    public static Enemy[] enemys = new Enemy[4];
   // public static BossEnemy be = new BossEnemy();




    public static int coinage = 25, health = 100;

    public static int level =1;

    public ActionGame(Frame frame){
        frame.addMouseListener(new KeyHandel());
        frame.addMouseMotionListener(new KeyHandel());
        thread.start();
    }
    public void define(){
        room = new GameField();
        save = new Save();
        store = new Store();

        tileset_enemy[0] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\Enemy1.png").getImage();
        tileset_enemy[1] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\Enemy3.png").getImage();

        tileset_groundGrass[Value.groundGrass] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\towerDefense_tile1.png").getImage();
        tileset_groundGrass[Value.groundRoad] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\towerDefense_tile060.png").getImage();

        tileset_item[Value.airHome] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\Home.png").getImage();
        tileset_item[Value.airTrash] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\Trash.png").getImage();
        tileset_item[Value.airTowerLase] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\towerDefense_tile206.png").getImage();
        tileset_item[Value.airTowerFrost] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\towerDefense_tile249.png").getImage();

        tileset_symbol[0] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\Cell.png").getImage();
        tileset_symbol[1] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\coin.png").getImage();
        tileset_symbol[2] = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Resource\\Textures\\heart.png").getImage();

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream("C:\\Users\\phamv\\IdeaProjects\\TowerDefense\\Save\\Mission");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        save.loadSave(fileStream);
        for(int i =0; i< enemys.length;i++){
            enemys[i] = new Enemy();
        }

    }

    public void paint(Graphics g){
        if(isFirst){
            myWidth = getWidth();
            myHeight = getHeight();
            define();
            isFirst = false;
        }
        g.setColor(new Color(60,60,60));
        g.fillRect(0, 0, getWidth(), getHeight());

        room.draw(g); // vẽ map : đường và cây
        for(int i =0; i< enemys.length;i++){
            if(enemys[i].inGame){
                enemys[i].draw(g);
              //  mobs[i].draw(g, mobs[i].enemyType);
            }
            store.draw(g);// vẽ cửa hàng mua bán

            // kiểm tra kết thúc game
            if(health <= 0){
                Image image = new ImageIcon("C:\\Users\\phamv\\IdeaProjects\\Tower_Defense\\Resource\\Textures\\GameOver.png").getImage();
                g.drawImage(image,0, 0, getWidth(), getHeight(),null);
            }
        }
    }

    //spawnTime Khoảng Thời gian giữa 2 con enemy đi ra
    //spawnFrame biến đếm thời gian
    public int spawnTime = 2000, spawnFrame = 0;
    public int mobSpawned = 0;
    public int mobNumber = ActionGame.level * 3;

    public void mobSpawner(){
        if(mobSpawned <= mobNumber){
            if(spawnFrame == spawnTime){
                for(int i = 0; i < enemys.length; i++){
                    if(!enemys[i].inGame){
                        if(mobSpawned == mobNumber){
                            enemys[mobNumber].towerDamage = 5;// lượng sát thương mà boss địch bị khi bị bắn
                            enemys[mobNumber].spawnMob(Value.mobBoss,1000 * level, 22* level, 10, (25 / ActionGame.level) - 8);

                        }else{
                            enemys[i].towerDamage = 15;// Lượng sát thương mà loại thường bị khi bị bắn
                            enemys[i].spawnMob(Value.mobAngry, 1000 * level,22 * level, 5, (25 / ActionGame.level) );
                        }
                        mobSpawned += 1;
                        break;
                    }
                }
                spawnFrame = 0;
            }else{
                spawnFrame += 1;
            }
        }
    }

    public void run(){
        while(true){
            if(!isFirst && health > 0){
                room.physic();
                mobSpawner();
                for(int i =0; i< enemys.length;i++){
                    if(enemys[i].inGame)
                        enemys[i].physic();
                }
                if(killCount == mobNumber +1){
                    level += 1;
                    killCount =0;
                    mobSpawned =0;
                }
            }
            repaint();
            try{
                Thread.sleep(1);
            }catch(Exception e){}
        }

    }

}
