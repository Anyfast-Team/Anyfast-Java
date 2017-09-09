package anyfast.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import anyfast.RegUtil;

/*
 * �ļ�����API
 */

public class FileAPI {
	/*
	 * ɾ���ļ�
	 */
	public static final boolean deleteFile(String path){
		return new File(path).delete();
	}
	/*
	 * �������ļ�
	 */
	public static final boolean createFile(String path){
		try {
			return new File(path).createNewFile();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			return false;
		}
	}
	/*
	 * �ļ���С
	 */
	public static final long length(String path){
		return new File(path).length();
	}
	/*
	 * ����Ŀ¼
	 */
	public static final boolean mkdir(String path){
		return new File(path).mkdir();
	}
	/*
	 * ȡ�ļ�Type
	 */
	public static final String contentType(String filename){
		String type = null;
		Path path = Paths.get(filename);
		try {
			type = Files.probeContentType(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return type;
	}
	/*
	 * ���ı��ļ�
	 */
	public static final TextFile openTextFile(String path){
		return new TextFile(path);
	}
	/*
	 * ��ָ��������ı��ļ�
	 */
	public static final TextFile openTextFile(String path,String encode){
		return new TextFile(path,encode);
	}
	/*
	 * �����ļ�
	 */
	public static final File[] searchFile(String start,String name){
		return searchFile(new File(start),name);
	}
	private static final File[] searchFile(File f,String name){
		List<File> fs = new ArrayList<File>();
		for(File fi : f.listFiles()) {
			if(fi.isFile()){
				if(RegUtil.Regular(fi.getName(),(name))){
					fs.add(fi);
				}
			} else if(fi.isDirectory()) {
				for(File add : searchFile(fi,name)){
					fs.add(add);
				}
			}
		}
		File[] ret = new File[fs.size()];
		int point = 0;
		for(File fi : fs) {
			ret[point] = fi;
			point++;
		}
		return ret;
	}

}
