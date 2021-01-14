package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.UnboundedQueueInterface;
import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
   // Queue<File> diver = new Queue<File>(); 
	UnboundedQueueInterface<File> diver;
	File ret;
	
    /**
   * Instantiate a new LevelOrderIterator, rooted at the rootNode.
   * @param rootNode : node of the root.
   * @throws FileNotFoundException if the rootNode does not exist.
   */
  public LevelOrderIterator(File rootNode) throws FileNotFoundException {
    if(!(rootNode.exists()))
    {
    	throw new FileNotFoundException("Doesn't exist");
    }
    diver = new Queue<File>();
    diver.enqueue(rootNode);
    }

  @Override
  public boolean hasNext() {
    if(diver.isEmpty())
    {
    	return false;
    }
    return true;
  }

  @Override
  public File next() throws NoSuchElementException {
    if(diver.isEmpty())
    {
    	throw new NoSuchElementException("Not Found");
    }
    ret = diver.dequeue();
    if(ret.isDirectory())
	{
		File[] tsort = ret.listFiles();
		Arrays.sort(tsort);
		for(File sorted : tsort)
		{
			diver.enqueue(sorted);
		}
	}
    return ret;

  }

  @Override
  public void remove() {
    // Leave this one alone.
    throw new UnsupportedOperationException();
  }

}
