package anyfast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtil {
	/*
	 * 取本机IP
	 */
	public static final String getIPAddres(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			return null;
		}
	}
	/*
	 * 取指定HOST的IP地址
	 */
	public static final String getIPAddres(String host){
		try {
			return InetAddress.getByName(host).getHostAddress();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			return null;
		}
	}
}
