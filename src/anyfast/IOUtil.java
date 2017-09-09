package anyfast;

import java.io.IOException;
import java.io.InputStream;

import com.sun.xml.internal.ws.Closeable;

public class IOUtil {
	    /*
	     * 关闭一个或多个流对象
	     * 
	     * @param closeables
	     *            可关闭的流对象列表
	     * @throws IOException
	     */
	    public static void close(Closeable... closeables) throws IOException {
	        if (closeables != null) {
	            for (Closeable closeable : closeables) {
	                if (closeable != null) {
	                    closeable.close();
	                }
	            }
	        }
	    }
	 
	    /*
	     * 关闭一个或多个流对象
	     * 
	     * @param closeables
	     *            可关闭的流对象列表
	     */
	    public static void closeQuietly(Closeable... closeables) {
	        try {
	            close(closeables);
	        } catch (IOException e) {
	            // do nothing
	        }
	    }
	    /*
	     * 等待InputStream数据
	     */
	    public static boolean waitForData(InputStream is){
	    	try{
	    		for(;is.available()==-1;){}
	    	} catch(IOException e){
	    		return false;
	    	}
	    	return true;
	    }
	    /*
	     * 等待InputStream数据,超时返回false
	     */
	    public static boolean waitForData(InputStream is,long time)
	    {
	    	long start = System.currentTimeMillis();
	    	try{
	    		for(;is.available()==-1;){
	    			if(System.currentTimeMillis()-start >= time){
	    				return false;
	    			}
	    		}
	    	} catch(IOException e){
	    		return false;
	    	}
	    	return true;
	    }
}
