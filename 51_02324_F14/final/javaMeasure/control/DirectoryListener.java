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
	public void run()
	{
		synchronized(this){
			boolean deletedFile, createdFile, modifiedFile;
			try
			{
				// wait for key to be signaled
				this.watcher = this.dir.getFileSystem().newWatchService();
				this.dir.register(this.watcher, StandardWatchEventKinds.ENTRY_CREATE,
						StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
				
				WatchKey watchKey = null;
				watchKey = this.watcher.take(); // waits until any changes occur
				System.out.println(path + " is being watched");
				while(!this.isInterrupted()){	
					String filename = null;
					deletedFile = false;
					createdFile = false;
					modifiedFile = false;
					Thread.sleep(1000);

					List<WatchEvent<?>> events = watchKey.pollEvents();
					// one change can trigger up to 3 events
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
					
					if(deletedFile && createdFile && modifiedFile) // if file is renamed
					{
						System.out.println("renamed file");	
					}
					else if(createdFile && modifiedFile) // if file is pasted these events are triggered
					{			
						batchMeasureController.addLeakMeasurement(path, filename);
					}
					else if(deletedFile) // if file is deleted
					{
						System.out.println("deleted file");
					}
					else if(modifiedFile) // changes made in file
					{
						System.out.println("changes made in file");
					}
					else if(createdFile) // new file created
					{
						System.out.println("new file created");
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
	
// TODO these are all different form of changes in folder. if more than the current wants to be used replace current one with this	

	
//	public void run()
//	{
//		synchronized(this){
//			boolean deletedFile, createdFile, modifiedFile;
//			try
//			{
//				// wait for key to be signaled
//				this.watcher = this.dir.getFileSystem().newWatchService();
//				this.dir.register(this.watcher, StandardWatchEventKinds.ENTRY_CREATE,
//						StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
//				
//				WatchKey watchKey = null;
//				watchKey = this.watcher.take(); // waits until any changes occur
//				System.out.println(path + " is being watched");
//				while(!this.isInterrupted()){	
//					String filename = null;
//					createdFile = false;
//					modifiedFile = false;
//					Thread.sleep(1000);
//
//					List<WatchEvent<?>> events = watchKey.pollEvents();
//					// one change can trigger up to 3 events but only 2 of them are needed at the moment
//					for (WatchEvent<?> event : events)
//					{
//						filename = event.context().toString(); // sets filename of file to be read by dasyFileReader
//						
//						if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
//							createdFile = true;
//						
//						if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY)
//							modifiedFile = true;
//					}	
//					
//					if(createdFile && modifiedFile) // if file is pasted these events are triggered		
//						batchMeasureController.addLeakMeasurement(path, filename);
//				}
//				
//			} catch (Exception e){
//				System.out.println("Error: " + e.toString());
//			}
//		}
//	}
	
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