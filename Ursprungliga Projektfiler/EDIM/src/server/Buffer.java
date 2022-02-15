package server;

import java.util.LinkedList;
/**
 * Buffer class
 *
 * @author Rolf Axelsson
 */
public class Buffer<T> {
	private LinkedList<T> buffer=new LinkedList<T>();


	public synchronized Object put(T obj) {
		buffer.addLast(obj);
		notifyAll();
		return buffer.getFirst();
	}
	
	public synchronized T get() throws InterruptedException {
		while(buffer.isEmpty()) {
			wait();
		}
		return buffer.removeFirst();
	}
	
	public int size() {
		return buffer.size();
	}
}
