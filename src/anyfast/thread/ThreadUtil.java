package anyfast.thread;

/*
 * �̲߳���
 */

public class ThreadUtil {
	public static final boolean sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
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
					// TODO �Զ����ɵ� catch ��
				}
			}else{
				try {
					Thread.sleep(System.currentTimeMillis() - start);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
				}
				return;
			}
		}
	}
}
