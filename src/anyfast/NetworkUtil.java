package anyfast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtil {
	/*
	 * ȡ����IP
	 */
	public static final String getIPAddres(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			return null;
		}
	}
	/*
	 * ȡָ��HOST��IP��ַ
	 */
	public static final String getIPAddres(String host){
		try {
			return InetAddress.getByName(host).getHostAddress();
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			return null;
		}
	}
}
