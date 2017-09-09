package anyfast;

import java.io.IOException;
import java.io.InputStream;

import com.sun.xml.internal.ws.Closeable;

public class IOUtil {
	    /*
	     * �ر�һ������������
	     * 
	     * @param closeables
	     *            �ɹرյ��������б�
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
	     * �ر�һ������������
	     * 
	     * @param closeables
	     *            �ɹرյ��������б�
	     */
	    public static void closeQuietly(Closeable... closeables) {
	        try {
	            close(closeables);
	        } catch (IOException e) {
	            // do nothing
	        }
	    }
	    /*
	     * �ȴ�InputStream����
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
	     * �ȴ�InputStream����,��ʱ����false
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
