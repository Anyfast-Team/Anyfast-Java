package anyfast;

public class RandomUtil {
	public static final int randomInt(int min,int max){
		return (int)(min - (System.currentTimeMillis() % (max - min)));
	}
	public static final String randomString(int num){
		String ret = "";
		for(int i=0;i<num;i++) {
			int i1 = randomInt(0,82);
			if(i1<9) {
				ret += i1;
			} else {
				if(i1<46){
					ret += ((char)'a'+i1);
				} else {
					ret += ((char)'A'+i1);
				}
			}
		}
		return ret;
	}
	public static final String randomString(int num,char[] c){
		String ret = "";
		for(int i=0;i<num;i++) {
			int i1 = randomInt(0,c.length-1);
			ret += c[i1];
		}
		return ret;
	}
	public static final String randomNumberString(int num){
		String ret = "";
		for(int i=0;i<num;i++) {
			int i1 = randomInt(0,9);
			ret += i1;
		}
		return ret;
	}
}
