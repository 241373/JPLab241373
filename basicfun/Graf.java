package basicfun;
import java.util.Arrays;


public class Graf {
	int ID=-1;
	String name="-";
	

}

class Node{
	int index=-1;
	int[] Edges;
	
	public Node(int ind, int[] half_edge) {
		index=ind;
		Edges =	Arrays.copyOf(half_edge, half_edge.length);
	}
}
