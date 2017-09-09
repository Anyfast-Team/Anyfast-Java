package anyfast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	public static final void zip(String zipFileName, File inputFile) throws Exception {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                zipFileName));  
        BufferedOutputStream bo = new BufferedOutputStream(out);  
        zip(out, inputFile, inputFile.getName(), bo);  
        bo.close();  
        out.close(); // ������ر�  
    }  
  
    private static final void zip(ZipOutputStream out, File f, String base,  
            BufferedOutputStream bo) throws Exception { // ��������  
        if (f.isDirectory()) {  
            File[] fl = f.listFiles();  
            if (fl.length == 0) {  
                out.putNextEntry(new ZipEntry(base + "/")); // ����zipѹ�������base   
            }  
            for (int i = 0; i < fl.length; i++) {  
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // �ݹ�������ļ���  
            }
        } else {  
            out.putNextEntry(new ZipEntry(base)); // ����zipѹ�������base
            FileInputStream in = new FileInputStream(f);  
            BufferedInputStream bi = new BufferedInputStream(in);  
            /*int b;  
            while ((b = bi.read()) != -1) {  
                bo.write(b); // ���ֽ���д�뵱ǰzipĿ¼  
            }  */
            byte[] data = new byte[128];
            int b;
            while ((b = bi.read(data)) != -1) {  
                bo.write(data,0,b); // ���ֽ���д�뵱ǰzipĿ¼  
            }
            bi.close();  
            in.close(); // �������ر�  
        }  
    }
    /**
     * ��ѹ�ļ�
     * 
     * @param filePath
     *            ѹ���ļ�·��
     */
    public static void unzip(String filePath) {
        File source = new File(filePath);
        if (source.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null
                        && !entry.isDirectory()) {
                    File target = new File(source.getParent(), entry.getName());
                    if (!target.getParentFile().exists()) {
                        // �����ļ���Ŀ¼
                        target.getParentFile().mkdirs();
                    }
                    // д���ļ�
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
               try {
            	   zis.close();
            	   bos.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
               
            }
        }
    }
}
