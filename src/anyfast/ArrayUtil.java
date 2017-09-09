package anyfast;

public class ArrayUtil {
	public static final String getString(String[] str){
		StringBuilder sb = new StringBuilder();
		for(String s : str) {
			sb.append(s);
		}
		return sb.toString();
	}
	
}
