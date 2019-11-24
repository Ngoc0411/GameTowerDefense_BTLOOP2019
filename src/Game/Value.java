package Game;
public class Value {
	public static int groundGrass =0;    // cỏ cây
	public static int groundRoad = 1;   // đường
	public static int airAir = -1;      // thứ khác ko phải nhà
	public static int airHome = 0;     // ngôi nhà

	public static int airTrash = 1;    // Thùng rác
	public static int airTowerLase = 2; // Tháp laze
	public static int airTowerFrost = 3; // Tháp băng

	public static int mobAir = -1;
	public static int mobAngry = 0;
	public static int mobBoss = 1;

	public static int[] deathReward = {2,5};
}
