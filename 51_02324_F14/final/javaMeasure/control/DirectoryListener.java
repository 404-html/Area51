package javaMeasure.control;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import javaMeasure.control.interfaces.IBatchMeasureController;

public class DirectoryListener extends Thread
{
	private Thread thread;	
	private String path;
	private Path dir;
	private WatchService watcher;
	private IBatchMeasureController batchMeasureController;

	public DirectoryListener(String path, IBatchMeasureController batchMeasureController)
	{
		System.out.println("initialize: " + System.nanoTime());
		this.path = path;
		this.batchMeasureController = batchMeasureController;

		try {
			this.watcher = FileSystems.getDefault().newWatchService();
		} catch (IOException e1) {
			System.err.println("something went wrong when newWatchService() was called");
			System.err.println(e1.getMessage());
		}
		this.dir = Paths.get(path);

	}
	public void run(){
		synchronized(this){
			boolean deletedFile, createdFile, modifiedFile;
			try	{	// wait for key to be signaled
				this.watcher = this.dir.getFileSystem().newWatchService();
				this.dir.register(this.watcher, StandardWatchEventKinds.ENTRY_CREATE,
						StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
				WatchKey watchKey = this.watcher.take(); // waits until any changes occur
				System.out.println(path + " is being watched");
				while(!this.isInterrupted()){	
					String filename = null;
					deletedFile = false; createdFile = false; modifiedFile = false;

					Thread.sleep(1000); //1 sec update interval
					List<WatchEvent<?>> events = watchKey.pollEvents();
					// one change can trigger up to 3 events - Finding the filename
					for (WatchEvent<?> event : events)
					{
						filename = event.context().toString();
						if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
							createdFile = true;
						if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE)
							deletedFile = true;
						if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY)
							modifiedFile = true;
					}	

					if(deletedFile && createdFile && modifiedFile) { 
						System.out.println("renamed file");	// file is renamed
					}
					else if(createdFile && modifiedFile) {			
						batchMeasureController.addLeakMeasurement(path, filename); // file is pasted
					}
					else if(deletedFile) {
						System.out.println("deleted file"); // if file is deleted
					}
					else if(modifiedFile) {
						System.out.println("changes made in file"); // changes made in file
					}
					else if(createdFile) {
						System.out.println("new file created"); // new file created
					}
					//					System.out.println("after check: " + System.nanoTime());
				}
			} catch (Exception e){
				System.out.println("Error: " + e.toString());
			}
		}
	}
	public void start()
	{
		if(this.thread == null)
		{
			this.thread = new Thread(this);
			this.thread.start();
		}
	}
	public void setPath(String path){
		this.path = path;
		this.dir = Paths.get(path);
	}

	public void interrupt(){
		this.thread.interrupt();
	}

	public static void main(String[] args) throws IOException
	{
		//		MainController m1 = new MainController();
		//		BatchMeasureController bc1 = new BatchMeasureController(m1);
		//		m1.activeUser = new User("test", 1);
		//		IDasyFileReader df = new DasyFileReader();
		//		System.out.println("file reader created");
		//		DirectoryListener dl = new DirectoryListener("C:/Dropbox/Mín mappa/Programmering/Area51/DasyLabFiles", bc1.getBatchMeasureGui(), df);
		//		dl.run();
		//		System.out.println("directoryListener running");

	}
}