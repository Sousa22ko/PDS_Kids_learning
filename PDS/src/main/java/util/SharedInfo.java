package util;

public class SharedInfo {

	private static Long id = null;
	private static boolean direction;
	private static int instance;

	public static boolean getDirection() {
		return direction;
	}

	public static void setDirection(boolean direction) {
		SharedInfo.direction = direction;
	}

	public static long getId() {
		return id;
	}

	public static void setId(Long id) {
		SharedInfo.id = id;
	}

	public static void setInstance(int i) {
		instance = i;
	}
	
	public static int getInstance(){
		return instance;
	}
}
