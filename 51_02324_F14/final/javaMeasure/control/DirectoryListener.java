package javaMeasure.control;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Iterator;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryListener {

	private String path;
	private Path dir;
	private WatchService watcher;


	public DirectoryListener() throws IOException{

		this(Paths.get("C:/Dropbox/Mín mappa/Programmering/Area51/DasyLabFiles"));
	}
	public DirectoryListener(Path dirPath) throws IOException{

		this.watcher = FileSystems.getDefault().newWatchService();
		this.dir = dirPath;
		watch();
	}


	public void watch()
	{
		boolean deletedFile, createdFile, modifiedFile;
		try
		{

			// wait for key to be signaled
			WatchService watcher = this.dir.getFileSystem().newWatchService();
			System.out.println("run 1");
			this.dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
			System.out.println("run 2");


			WatchKey watchKey = null;
			watchKey = watcher.take(); // waits until any changes occur
			System.out.println("step one");
			for(;;)
			{
				String filename = null;
				deletedFile = false;
				createdFile = false;
				modifiedFile = false;
				Thread.sleep(3000);
				
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
				if(deletedFile && createdFile && modifiedFile) // if file is pasted
				{
					System.out.println("created file");
					
				}
				else if(createdFile && modifiedFile) // if file is renamed
				{
					System.out.println("renamed file");
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

	public static void main(String[] args) throws IOException
	{
		DirectoryListener dl = new DirectoryListener();

	}
}