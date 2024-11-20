package main;

public class Ship implements Runnable{
	private int boxes;
	private String name;
	 private static final double UNLOAD_TIME = 0.5;
	 private static int availableDocks = 2;
	 private static final Object dockLock = new Object();
	 
	 public Ship(String name, int boxes) {
	        this.name = name;
	        this.boxes = boxes;
	    }
	 @Override
	    public void run() {
	        try {
	            synchronized (dockLock) {
	                while (availableDocks == 0) {
	                    dockLock.wait();
	                }
	                availableDocks--;
	                System.out.println(name + " gained access to the docks and began unloading.");
	            }
	            for (int i = 1; i <= boxes; i++) {
	                System.out.println(name + " unloads the box " + i);
	                Thread.sleep((long) (UNLOAD_TIME * 1000));
	            }
	            System.out.println(name + " completed unloading.");
	            synchronized (dockLock) {
	            	availableDocks++;
	            	dockLock.notifyAll();
	            }
	            } catch (InterruptedException e) {
	                System.out.println(name + " was interrupted.");
	            }
	        }
	    }
	
	
	
	


