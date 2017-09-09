package anyfast.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 控制台操作
 */

public class Console {
	private static final BufferedReader in;
	static{
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	public static final void print(String str){
		System.out.print(str);
	}
	public static final void println(String str){
		System.out.println(str);
	}
	public static final void printf(String str,Object[] args){
		System.out.println(String.format(str, args));
	}
	public static final char read(){
		try {
			return (char)System.in.read();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return 0;
		}
	}
	public static final String read(int i1){
		String ret = "";
		for(int i=0;i<i1;i++) {
			ret += read();
		}
		return ret;
	}
	public static final String readLine(){
		try {
			return in.readLine();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return null;
		}
	}
	public static final void error(String str){
		System.err.print(str);
	}
}
