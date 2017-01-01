import java.util.ArrayList;

public class List {
    private Node first;
    private java.util.List<List> backets;
    
    public List() {
        this.first = null;
    }
    
    public void insert(int data){
        Node node = new Node();
        node.setData(data);
        node.setNext(first);
        this.first = node;
    }
    
    public Node find(int data){
        return find(this.first, data);
    }
    
    public Node find(Node node, int data){
        if(node == null) return null;
        else if(node.getData() == data) return node;
        return find(node.getNext(), data);
    }
    
    public Node findPredecessor(int data){
        return findPredecessor(this.first, data);
    }
    
    public Node findPredecessor(Node node, int data){
        if(node == null || node.getNext() == null) return null;
        else if(node.getNext().getData() == data) return node;
        return findPredecessor(node.getNext(), data);
    }
    
    public void delete(int data){
        Node node = find(data);
        if (node != null) {
            Node predecessor_node = findPredecessor(data);
            if (predecessor_node != null) {
                predecessor_node.setNext(node.getNext());
            }
            else{
                this.first = node.getNext();
            }
            node = null;
        }
    }
    
    public int length(){
        int count = 0;
        Node node = this.first;
        
        while(node != null){
            count++;
            node = node.getNext();
        }
        return count;
    }
    
    public Node[] findAll(){
        Node[] nodes = new Node[this.length()];
        Node node = this.first;
        int i=0;
        
        while(node != null){
            nodes[i] = node;
            node = node.getNext();
            i++;
        }
        
        return nodes;
    }
    
    private void merge(int index, int jndex){
        Node node1 = backets.get(index).first;
        Node node2 = backets.get(jndex).first;
        List list_of_nodes = new List();
        java.util.List<Integer> list = new ArrayList<Integer>();
        
        for(int i=0;i<backets.get(index).length();i++){
            for(int j=0;j<backets.get(jndex).length();j++){
                if(j==-1)j++;
                if(node1.getData() <= node2.getData()){
                    list.add(node1.getData());
                    i++;
                    j--;
                    node1 = node1.getNext();
                }
                else{
                    list.add(node2.getData());
                    node2 = node2.getNext();
                }
                
                if(i==backets.get(index).length()){
                    for(;j<backets.get(jndex).length();j++) {
                        if(node2 == null)break;
                        list.add(node2.getData());
                        node2 = node2.getNext();
                    }
                }
                else if(j==backets.get(jndex).length()-1){
                    for(;i<backets.get(index).length();i++) {
                        if(node1 == null)break;
                        list.add(node1.getData());
                        node1 = node1.getNext();
                    }
                }
            }
        }
        
        for(int i=list.size()-1;i>=0;i--)
            list_of_nodes.insert(list.get(i));
        
        backets.remove(index);
        backets.add(index, list_of_nodes);
        backets.remove(jndex);
    }
    
    private void addToSortArray(int data1, Object data2){
        List list = new List();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node = null;
        
        node1.setData(data1);
        if (data2 != null) {
            node2.setData((Integer)data2);
    
            // Если первая нода больше второй, то меняем их местами
            if (node1.getData() > node2.getData()) {
                node = node1;
                node1 = node2;
                node2 = node;
            }
    
            // Первая нода становится потомком второй
                node1.setNext(node2);
        }
        
        // Добавляем первую ноду в новый лист
        list.first = node1;
    
        // Сразу добавляем в массив листов наш новый лист
        backets.add(list);
    
        for(int i=0;i<backets.size()-1;i++){
            if(backets.get(i).length() == backets.get(i+1).length() || backets.get(i+1).length() == 1){
                merge(i, i+1);
            }
        }
        return;
    }
    
    public void sort(){
        Node[] nodes = findAll();
        backets = new ArrayList<List>(32);
        
        for(int i=0;i<nodes.length;i+=2){
            if(nodes[i].getNext() != null)
                addToSortArray(nodes[i].getData(), nodes[i].getNext().getData());
            else
                addToSortArray(nodes[i].getData(), null);
        }
        
        while(backets.size() != 1){{
            for(int i=0;i<backets.size()-1;i++){
                merge(i, i+1);
            }
        }}
    }
    
    public void primitive_sort(){
        Node[] nodes = findAll();
        Node node = new Node();

        for(int i=0;i<length();i++){
            for(int j=i+1;j<length();j++){
                if(nodes[i].getData() > nodes[j].getData()) {
                    node = nodes[i];
                    nodes[i] = nodes[j];
                    nodes[j] = node;
                }
            }
        }
    }
}
