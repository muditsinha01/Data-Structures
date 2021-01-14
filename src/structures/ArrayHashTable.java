package structures;

// this class demonstrates using an object's hash code to calculate the 
// index into the array

public class ArrayHashTable<K, V> implements HashTable<K, V> {

  protected static final int defaultCapacity = 10;
  protected static final double defaultLoadFactor = 0.7;
  protected static final String defaultCollisionHandler = "linear";
  protected K[] keyTable;
  protected V[] valueTable;
  protected int capacity;
  protected boolean[] flag;
  protected String collisionHandler;
  protected double loadFactorLimit;
  protected int count;

  /**
   * Default Constructor.
   */
  public ArrayHashTable() {
    this(defaultCapacity, defaultLoadFactor, defaultCollisionHandler);
  }

  /**
   * Default Constructor.
   */
  public ArrayHashTable(String collisionHandler) {
	  capacity = defaultCapacity;
	  loadFactorLimit = defaultLoadFactor;
	  flag = new boolean[capacity];
	  this.collisionHandler = collisionHandler;
	  keyTable = (K[])new Object[capacity];
	  valueTable = (V[]) new Object[capacity];
	  count = 0;
  }

  /**
   * Default Constructor.
   */
  public ArrayHashTable(int capacity, String collisionHandler){
	  this.collisionHandler = collisionHandler;
	  this.capacity = capacity;
	  flag = new boolean[capacity];
	  loadFactorLimit = defaultLoadFactor;
	  keyTable = (K[])new Object[capacity];
	  valueTable = (V[]) new Object[capacity];
	  count = 0;
  }

  
  /**
   * Constructor.
   */
  public ArrayHashTable(int capacity, double loadFactor, String collisionHandler){
    this.collisionHandler = collisionHandler;
    this.capacity = capacity;
    loadFactorLimit = loadFactor;
    flag = new boolean[capacity];
    keyTable = (K[])new Object[capacity];
	valueTable = (V[]) new Object[capacity];
	count = 0;
    
  }
 
  /** 
  * This method ensures that the inputNum maps to
  * a return value that is in the current array.
  */
  private int getBoundedHash(int inputNum) {
    return Math.abs(inputNum) % this.capacity;
  }

  private int getHash(K key) {
    int index = getBoundedHash(key.hashCode());
    return index;
  }
  
  public K[] getKeyTable() {
    return this.keyTable;
  }
  
  public V[] getValueTable() {
    return this.valueTable;
  }
  
  public boolean[] getFlag() {
    return this.flag;
  }

  /** 
   * Returns the number of elements in the hash table.
   */
  public int size() {
    return count;
  }

  /**
   * Returns the current maximal number of elements the hash table can save.
   * @return
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Returns the name of the collision handler for the current hash table. 
   * @return : name of the collision handler.
   */
  public String getCollisionHandlerName() {
    return collisionHandler;
  }

  /**
   * Enlarges the size of the array and rehash.
   */
  private void resizeArray() {
    // TODO 3: Double the size, then rehash into the new table
	  capacity = capacity *2;
	  ArrayHashTable<K,V> table = new ArrayHashTable<K,V>(capacity,loadFactorLimit,collisionHandler);
	 /* table.keyTable = keyTable;
	  table.valueTable = valueTable;
	  table.flag = flag;
	  keyTable = (K[])new Object[capacity];
	  valueTable = (V[])new Object[capacity];
	  flag = new boolean[capacity];*/
	  for(int i =0;i < keyTable.length;i++)
	  {
		  if(keyTable[i] != null && flag[i] == true)
		  {
			  table.put(keyTable[i], valueTable[i]);
			  /* if(table.keyTable[index] != null)
			  {
				index =  resolveCollision(index, keyTable[i]);
			  }
			 table.keyTable[index] = keyTable[i];
			  table.valueTable[index] = valueTable[i];
			  table.flag[index] = true;
			  table.count++;*/
		  }
		  
		  	  }
	  keyTable = (K[]) new Object[capacity];
	  valueTable = (V[]) new Object[capacity];
	  flag = new boolean[capacity];
	 keyTable = table.keyTable;
	 valueTable = table.valueTable;
	 flag = table.flag;
	 count = table.count;

  }

  /**
  * Calculates the ratio of the size of the data in the table to the capacity.
  */
  private double calcCurrentLoad() {
          double tsize = (double)count/capacity;
          return tsize;
  } 

  /**
   * This method calls upon a collision resolution scheme
   * to put this value into the table.
   */
  private int resolveCollision(int index, K key) {
    if (this.collisionHandler.equals("linear")) {
      index = doLinearProbe(index);
    } else if (this.collisionHandler.equals("quadratic")) {
      index = doQuadraticProbe(index);
    } else if (this.collisionHandler.equals("doublehash")) {
      index = doDoubleHash(index, key);
    } else { 
      return -1; 
    }
    return index;
  }

  /** 
   * Put the new value into the hash table. Before adding, 
   * resize the array if the current load factor is larger than loadFactorLimit. 
   * Please use the getHash method provided to get the hash for the array index.
   * Call the searchIndex method to see if a collision occurs. 
   * If the key exists in the hash table, replace the old value with the input value.
   * Call the resolveCollision method if a collision happened.
   */
  public void put(K key, V value) {
    if(calcCurrentLoad() >= loadFactorLimit)
    {
    	resizeArray();
    }
    int index = getHash(key);
    if(keyTable[index]!= null && flag[index] == true)
    {
    	index = resolveCollision(index,key);
    }
    keyTable[index] = key;
    valueTable[index] = value;
    flag[index] = true;
    count++;
  }

  /** T
   * This method calls upon a collision resolution scheme
   * to search for an index where the value can be inserted into the table.
   */
  private int searchIndex(int index, K value) {
    if (this.collisionHandler.equals("linear")) {
      index = doLinearSearch(index, value);
    } else if (this.collisionHandler.equals("quadratic")) {
      index = doQuadraticSearch(index, value);
    } else if (this.collisionHandler.equals("doublehash")) {
      index = doSecondHashSearch(index, value);
    }
    return index;
  }

  /**
   * Removes the target value from the hash table and return the value.
   * Throws ElementNotFoundException if the target does not exist in the table.
   * Calls searchIndex to get the index in case a collision occurred when
   * the value was put into the table.
   */
  public V remove(K target)throws ElementNotFoundException {
    //TODO 6: remove the target value from hash table and return value
	 int index = getHash(target);
	 if(keyTable[index]== null)
	 {
		 throw new ElementNotFoundException("Element not found");
	 }
	 if(index != searchIndex(index,target))
	 {
		 index = searchIndex(index,target);
	 }
	 if(index == -1)
	 {
		 throw new ElementNotFoundException("Element not found");
	 }
	 //flag[index] = false;
	// return valueTable[index];
	 if(keyTable[index].equals(target))
	 {
		 flag[index] = false;
		 count--;
		 V temp = valueTable[index];
		 return temp;
     } 
	 
	 return null;
  }

  /**
   * Returns the value if it exists in the table.
   * Returns null if the target does not exist in the table.
   * Calls searchIndex to get the index in case a collision occurred when
   * the value was put into the table.
   */
  public V get(K target) {
    int index = getHash(target);
    if(index != searchIndex(index,target))
	 {
		 index = searchIndex(index,target);
	 }
	 if(index == -1)
	 {
		return null;
	 }
	 if(keyTable[index].equals(target) && flag[index] == true)
	    {  
	    	return valueTable[index];
	    }
    return null;
  }

  /** TODO 8: Complete linearProbe, quadraticProbe, and doubleHash
  * Start at index and search linearly (with probe length = 1) until an open spot
  * is found. A spot will be found because the table 
  * is never more full than the Load Factor, assuming it's <1.0
  */
  private int doLinearProbe(int index) {
    while(keyTable[index] != null && flag[index] == true)
    {
    	index++;
    	if(index == keyTable.length)
    	{
    		index = 0;
    	}
    }
    return index;
  }
 
  /**
  * Start at index and search quadratically until an open spot is found
  * A spot will be found because the table 
  * is never more full than the Load Factor, assuming it's <1.0
  */
  private int doQuadraticProbe(int index) {
	  int i =1; 
	  int temp = index;
	  while(keyTable[index] != null && flag[index] == true) 
	   {
		   index = getBoundedHash(temp + (i*i));
		   i++;
       }
	   return index;
  }


/**
  * If the first hash resulted in a collision, use a second hash
  * as the probe length until a space is found. 
  * The second hash value is computed by length of value.hashCode()
  */
  private int doDoubleHash(int index, K key) {
		String str = "";
	    str = key.toString();
	    int p_length = getBoundedHash(str.length());
	    while(keyTable[index] != null && flag[index] == true)
	    {
	    	index = getBoundedHash(index + p_length);
	    }
	  return index;
  }  

  /**
  * TODO 9: Complete linearSearch, quadraticSearch, and doubleHashSearch.
  * Start at startIndex and search linearly until the target
  * is found. Return -1 if not found.
  */
  private int doLinearSearch(int startIndex, K target) {
      int temp = startIndex;
	  while(keyTable[temp] != null)
    		 {
    	 		if(keyTable[temp].equals(target) && flag[temp] == true )
    	 		{
    	 			return temp;
    	 		}
    	 		++temp;
    	 		if(temp == keyTable.length)
    	 		{
    	 			temp = 0;
    	 		}
    	 		if(startIndex == temp)
    	 		{
    	 			return -1;
    	 		}
    		 }
	  return -1;
  }

  /**
  * Start at startIndex and search quadratically until the target
  * is found. Return -1 if not found.
  */
  private int doQuadraticSearch(int startIndex, K target) {
	  int temp = startIndex;
	  int i =1;
	  while(keyTable[temp] != null )
	  {
		  if(keyTable[temp].equals(target)  )
		  {
			  return temp;
		  }
		  temp = (startIndex + (i*i)) % capacity ;
		  i++;
		  if(startIndex == temp)
		  {
			  return -1;
		  }
	  }
	  return -1;
  }

  private int doSecondHashSearch(int startIndex, K target) {
    int index = startIndex;
	String str = "";
    str = target.toString();
    int p_length = getBoundedHash(str.length());
    while(keyTable[index] != null && flag[index] == true)
    {
    	if(keyTable[index].equals(target))
    	{
    		return index;
    	}
    	index = getBoundedHash(index + p_length);
    	if(index == startIndex)
    	{
    		return -1;
    	}
    }
    return -1;
  }

  /**
   * Return all available keys in hash table as an array.
   */
  public K[] keys() {
    K[] nArray = (K[])new Object[count];
    int j = 0;
    for(int i =0; i<= keyTable.length;i++)
    {
    	if(keyTable[i] != null && flag[i] == true)
    	{
    		nArray[j] = keyTable[i];
    		j++;
    	}
    }
    	return nArray;
    }
    
  }

