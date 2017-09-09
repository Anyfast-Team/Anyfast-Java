package anyfast.core;

public class RuntimeSystem {
	/*
	 *	���л���
	 */
	public static final String Name =  System.getProperty("os.name");
	public static final String Version = System.getProperty("os.version");
	public static final String Arch = System.getProperty("os.arch");
	public static final boolean isAndroid(){
		try {
			Class.forName("android.app.Application");
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
}
