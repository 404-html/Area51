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

import javaMeasure.Batch;
import javaMeasure.Measurement;
import javaMeasure.PropertyHelper;
import javaMeasure.control.interfaces.IBatchMeasureController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.IMainController;
import javaMeasure.view.interfaces.IBatchMeasureGui;

public class DirectoryListener extends Thread
{
	private Thread thread;	
	private String path;
	private Path dir;
	private WatchService watcher;
	private IMainController mainController;
	private IBatchMeasureGui batchMeasureGui;
	private IBatchMeasureController batchMeasureController;
	private Batch activeBatch;
	
	
//	public DirectoryListener(String path, IMainController mainController, IBatchMeasureGui batchMeasureGui, Batch activeBatch)
//	{
	public DirectoryListener(String path, IBatchMeasureController batchMeasureController)
	{
		System.out.println("initialize: " + System.nanoTime());
		this.path = path;
		this.batchMeasureController = batchMeasureController;
//		this.mainController = mainController;
//		this.batchMeasureGui = batchMeasureGui;
//		this.activeBatch = activeBatch;
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.batchMeasureGui.updateLog("something went wrong when newWatchService() was called"); 
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
				WatchService watcher = this.dir.getFileSystem().newWatchService();
				this.dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
						StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

				batchMeasureController.updateLog("DasyLab files are automatically being read from: ");
				batchMeasureController.updateLog(path);
				
//				batchMeasureGui.updateLog("DasyLab files are automatically being read from: ");
//				batchMeasureGui.updateLog(path);
				WatchKey watchKey = null;
				System.out.println("before .take(): " + System.nanoTime());
				watchKey = watcher.take(); // waits until any changes occur
				System.out.println(path + " is being watched");
				while(!this.isInterrupted())
				{
					
					String filename = null;
					deletedFile = false;
					createdFile = false;
					modifiedFile = false;
					Thread.sleep(1000);

					System.out.println("start check: " + System.nanoTime());
					System.out.println("checking: " + path);
					List<WatchEvent<?>> events = watchKey.pollEvents();
					// one change can trigger up to 3 events
					for (WatchEvent event : events)
					{
						filename = event.context().toString();
						
						if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
						{
							System.out.println("Created: " + event.context().toString());
							createdFile = true;
						}
						if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE)
						{
							System.out.println("Delete: " + event.context().toString());
							deletedFile = true;
						}
						if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY)
						{
							System.out.println("Modify: " + event.context().toString());
							modifiedFile = true;
						}
					}
					if(deletedFile && createdFile && modifiedFile) // if file is renamed
					{
						System.out.println("renamed file");	
					}
					else if(createdFile && modifiedFile) // if file is pasted
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
					System.out.println("after check: " + System.nanoTime());
				}
				
			} catch (Exception e)
			{
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