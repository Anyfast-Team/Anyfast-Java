package anyfast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * ������ʽ
 */

public class RegUtil {
	/*
	 * ������ʽ
	 */
	public static final boolean Regular(String content,String pattern){
		return Pattern.matches(pattern, content);
	}
	/*
	 * ������ʽ������
	 */
	public static final String[] RegularArray(String content,String pattern){
		if(!Regular(content,pattern)){
			return null;
		}
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(content);
		String[] ret = new String[m.groupCount()];
		for(int point = 0;point<m.groupCount();point++){
			ret[point] = m.group(point);
		}
		return ret;
	}
}
