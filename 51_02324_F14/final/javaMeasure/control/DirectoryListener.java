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
	private Batch activeBatch;
	
	
	public DirectoryListener(String path, IMainController mainController, IBatchMeasureGui batchMeasureGui, Batch activeBatch)
	{
		this.path = path;
		this.mainController = mainController;
		this.batchMeasureGui = batchMeasureGui;
		this.activeBatch = activeBatch;
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

				batchMeasureGui.updateLog("DasyLab files are automatically being read from: " + path);
				WatchKey watchKey = null;
				watchKey = watcher.take(); // waits until any changes occur
				System.out.println("step one");
				while(!this.isInterrupted())
				{
					String filename = null;
					deletedFile = false;
					createdFile = false;
					modifiedFile = false;
					Thread.sleep(3000);
					System.out.println(this.isInterrupted());
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
						// creating leak measurement to be added to batch and saved in database
						long timestamp = Long.parseLong(PropertyHelper.readFromProperty("config", "leakvalue"));
						
						System.out.println("timestamp: " + timestamp);
						System.out.println(path + "/" + filename);
						
						Measurement measurement = mainController.getDasyController().getCurrentValue(timestamp, this.path + "/" + filename);
						measurement.setBatchID(activeBatch.getBatchID());
						measurement.setElementNo(activeBatch.getCurrentLeakElement());
						// adds the measurement to guis JTable before saved in database
						
						batchMeasureGui.updateLog("new leak measurement: " + measurement.getMeasureValue());
						batchMeasureGui.updateLog("read from: " + filename);

						try {
							boolean measurementAdded = activeBatch.addMeasurement(measurement);
							if(!measurementAdded)
							{
								batchMeasureGui.showInformationMessage("measurements should be taken equally", "measurements not equal");
							} else
							{
								mainController.getDatabaseController().addToDB(measurement);
								batchMeasureGui.updateTable(activeBatch);
							}
						} catch (DataBaseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
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