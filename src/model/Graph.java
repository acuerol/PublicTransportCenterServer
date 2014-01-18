package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Alexis Cuero Losada
 * This class is the abstraction of graph for sort the nodes and edges.
 */
public class Graph implements Serializable {
	
	/**
	 * Count of nodes in the graph.
	 */
	public static int countNodes;
	/**
	 * The Graph serialVersionUID.
	 */
	private static final long serialVersionUID = 367984052472116448L;
	private ArrayList<Object> nodes = new ArrayList<Object>();
	
	/**
	 * Creates a edge in the direction father to child.
	 * @param father the father node that constains to the child.
	 * @param child  the child node content in the father.
	 * @return returns true if the child was added succefully, else returns false.
	 */
	public boolean addEdge(Object father, Object child)
	{
		if(father != null || child != null) 
		{
			int index;
			if(!nodes.contains(father))
			{	
				nodes.add(father);
			}
			
			if(!nodes.contains(child))
			{
				nodes.add(child);
			}
			
			index = nodes.indexOf(father);
			
			if (nodes.get(index) instanceof Station) {
				((Station)(nodes.get(index))).addChild(child);
			}
			else
			{
				((Semaphore)(nodes.get(index))).addChild(child);
			}
			
			return true;
		}else
		{
			System.err.println("Error creating the edge, null reference.");
			return false;
		}
	}
	
	/**
	 * Creates all edges between nodes.
	 * @param roads teh roads that join the nodes.
	 */
	public void addListBidirectionalEdge(ArrayList<Road> roads)
	{
		ArrayList<Object> attached;
		for (Road road : roads) {
			attached = road.getNodesAttached();
			
			if(attached.size() == 2)
			{
				addEdge(attached.get(0), attached.get(1));
				addEdge(attached.get(1), attached.get(0));
			}
			
		}
	}
	
	/**
	 * Creates all edges between nodes.
	 * @param roads teh roads that join the nodes.
	 */
	public void addListEdge(ArrayList<Road> roads)
	{
		ArrayList<Object> attached;
		for (Road road : roads) {
			attached = road.getNodesAttached();
			
			if(attached.size() == 2)
			{
				addEdge(attached.get(0), attached.get(1));
			}
			
		}
	}
	
	/**
	 * Add all elements of ArrayList<E> to the graph. 
	 * @param <E> the type of ArrayList
	 * @param objects the ArrayList<E> added to the graph.
	 */
	public <E> void addListNode(ArrayList<E> objects)
	{
		for (Object object : objects) {
			addNode(object);
		}
	}
	
	/**
	 * Add a node to the nodes list in the graph. 
	 * @param node the node to be added.
	 */
	public void addNode(Object node)
	{
		if(!nodes.contains(node))
		{
			nodes.add(node);
			countNodes++;
		}
	}
	
	/**
	 * Creates a bidirectional edge to the node represent by dataNodeA to the node represent by dataNodeB. 
	 * @param nodeA the data that represent the node A.
	 * @param nodeB the data that represent the node B.
	 * @return if the edge was created succefully. 
	 */
    public boolean addTwoWayEdge(Object nodeA, Object nodeB)
    {
        if(addEdge(nodeA, nodeB) && addEdge(nodeB, nodeA))
        {
        	return true;
        }
        
        return false;
    }
	
	//Si no se puede crear que no se cree?
	
	/**
     * Returns the node childNodes. 
     * @param node the node where will obtained the childNodes  
     * @return if the node is content in the graph nodes then return the node childNodes, else return null.
     */
    public ArrayList<Object> getChildNodes(Object node)
    {
    	if(nodes.contains(node))
    	{
    		int index = nodes.indexOf(node);
    		ArrayList<Object> childNodes;
    		
    		if (nodes.get(index) instanceof Station) {
    			childNodes = ((Station)(nodes.get(index))).getChildNodes();
			}
			else
			{
				childNodes = ((Semaphore)(nodes.get(index))).getChildNodes();
			}
    		
    		if(childNodes == null)
    		{
    			return new ArrayList<Object>();
    		}
    		
    		return childNodes;
    	}
    	
    	System.err.println("This node isn't content in graph nodes.");
    	return null;
    }
    
    
    /**
	 * Returns all nodes in the Graph instance.
	 * @return the graph nodes.
	 */
	public ArrayList<Object> getNodes()
	{
		return nodes;
	}
    
    /**
     * Returns true if father childNodes constains to child.
     * @param father the node that probably contains to child.
     * @param child the node that probably is content in father childNodes.
     * @return true if father childNodes constains to child.
     */
    public boolean isChild(Object father, Object child)
    {
    	if(nodes.contains(father) && nodes.contains(child))
    	{
    		int index = nodes.indexOf(father);
    		
    		if (nodes.get(index) instanceof Station) {
				return ((Station)(nodes.get(index))).isChild(child);
			}
			else
			{
				return ((Semaphore)(nodes.get(index))).isChild(child);
			}
    		
    	}
    	else
    	{
    		System.err.println("Any node isn't in the graph nodes.");
    	}
    	
    	return false;
    }
}
