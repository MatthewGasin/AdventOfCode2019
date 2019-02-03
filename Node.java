public class Node {

	private Node[] children;
	private int[] metadata;
	private int nChildren;
	private int nMetadata;

	
	public Node() {
		super();
		nMetadata = -1;
		nChildren = -1;
		
	}
	
	public int getnChildren() {
		return nChildren;
	}

	public int getnMetadata() {
		return nMetadata;
	}

	public void readNChildren(int n) {
		this.nChildren = n;
		this.children = new Node[nChildren];
	}
	
	public void readNMetadata(int n) {
		this.nMetadata = n;
		this.metadata = new int[nMetadata];
		for(int i = 0; i < metadata.length; i++) {
			metadata[i] = -1;
		}
	}
	
	public Node[] getChildren() {
		return children;
	}

	public int[] getMetadata() {
		return metadata;
	}
	
	public boolean needsChildren() {
		for(int i = 0; i < children.length; i++) {
			if(children[i] != null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean needsMetadata() {
		for(int i = 0; i < metadata.length; i++) {
			if(metadata[i] != -1) {
				return true;
			}
		}
		return false;
	}
	

	public void addChild(Node n) {
		for(int i = 0; i < children.length; i++) {
			if(children[i] == null) {
				children[i] = n;
				return;
			}
		}
	}
	
	public void addMetadata(int a) {
		for(int i = 0; i < metadata.length; i++) {
			if(metadata[i] == -1) {
				metadata[i] = a;
				return;
			}
		}
	}
	
	

}
