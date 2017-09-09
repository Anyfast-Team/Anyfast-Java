package anyfast.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TextFile {
	private File f;
	private String encode = "ASCII";
	private BufferedReader br;
	private FileWriter fw;
	private FileOutputStream os;
	private int point = 0;
	public TextFile(String path){
		try {
			init(path,"ASCII");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
		}
	}
	public TextFile(String path,String encode){
		try {
			init(path,encode);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
		}
	}
	private void init(String path,String code) throws Exception{
		f = new File(path);
		encode = code;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(f),encode));
		fw = new FileWriter(f);
		os = new FileOutputStream(f,false);
	}
	public static final int T_Start = 0;
	public static final int T_End = 1;
	public boolean mark(int t,int offset){
		try {
			if(t==0) {
				br.mark(offset);
			} else if(t==1) {
				br.mark((int) (f.length() + offset));
			}
			return true;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return false;
		}
	}
	public String read(){
		try {
			String ret = "";
			char r;
			r = (char) br.read();
			if(r == -1){
				return null;
			}
			ret += r;
			return ret;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return null;
		}
	}
	public String read(int num){
		try{
			StringBuilder sb = new StringBuilder();
			for(int i=1;i<=num;i++) {
				int buf = br.read();
				if(buf==-1) {
					return sb.toString();
				}
				char add = (char) buf;
				sb.append(add);
			}
			return sb.toString();
		} catch(Exception e) {
			return null;
		}
	}
	public String readLine(){
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return null;
		}
	}
	public boolean write(String str,int offset){
		try {
			byte[] data = str.getBytes(encode);
			os.write(data,offset,data.length);
			os.flush();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return false;
		}
		return true;
	}
	public boolean append(String str){
		try {
			fw.append(str);
			fw.flush();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return false;
		}
		return true;
	}
	public void finalize(){
		try {
			br.close();
			fw.close();
		} catch (IOException e) {
		}
	}
}
