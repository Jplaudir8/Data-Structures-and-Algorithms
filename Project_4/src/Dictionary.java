
public interface Dictionary<K, E> {
	/** Reinitialize dictionary */
	  public void clear();

	  /** Insert a record
	      @param k The key for the record being inserted.
	      @param e The record being inserted. */
	  public void insert(K key, E elem);

	  /** Remove and return a record.
	      @param k The key of the record to be removed.
	      @return A matching record. If multiple records match
	      "k", remove an arbitrary one. Return null if no record
	      with key "k" exists. */
	  public E remove(K key);


	  /** @return A record matching "k" (null if none exists).
	      If multiple records match, return an arbitrary one.
	      @param k The key of the record to find */
	  public SinglyLinkedList<E> find(K key);

	  /** @return The number of records in the dictionary. */
	  public int size();

	  
}
