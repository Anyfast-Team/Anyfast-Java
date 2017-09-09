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
 * 文件操作API
 */

public class FileAPI {
	/*
	 * 删除文件
	 */
	public static final boolean deleteFile(String path){
		return new File(path).delete();
	}
	/*
	 * 创建新文件
	 */
	public static final boolean createFile(String path){
		try {
			return new File(path).createNewFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			return false;
		}
	}
	/*
	 * 文件大小
	 */
	public static final long length(String path){
		return new File(path).length();
	}
	/*
	 * 创建目录
	 */
	public static final boolean mkdir(String path){
		return new File(path).mkdir();
	}
	/*
	 * 取文件Type
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
	 * 打开文本文件
	 */
	public static final TextFile openTextFile(String path){
		return new TextFile(path);
	}
	/*
	 * 以指定编码打开文本文件
	 */
	public static final TextFile openTextFile(String path,String encode){
		return new TextFile(path,encode);
	}
	/*
	 * 搜索文件
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
