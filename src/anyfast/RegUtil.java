package anyfast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 正则表达式
 */

public class RegUtil {
	/*
	 * 正则表达式
	 */
	public static final boolean Regular(String content,String pattern){
		return Pattern.matches(pattern, content);
	}
	/*
	 * 正则表达式分数组
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
