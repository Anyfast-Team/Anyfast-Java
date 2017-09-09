package anyfast.thread;

/*
 * 线程操作
 */

public class ThreadUtil {
	public static final boolean sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			return false;
		}
		return true;
	}
	public static final void safeSleep(long time){
		long start = System.currentTimeMillis();
		for(;;){
			if(System.currentTimeMillis() - start >= time){
				break;
			}
			if(System.currentTimeMillis() - start>=100){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
				}
			}else{
				try {
					Thread.sleep(System.currentTimeMillis() - start);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
				}
				return;
			}
		}
	}
}
