package linkedlist;

public class MainLL {

	public static void main(String[] args) {
		
		LinkedList myLinkedList = new LinkedList(10);
        myLinkedList.append(40);
        myLinkedList.append(65);
        myLinkedList.append(300);
        myLinkedList.append(8);

        int k = 3;
        int result = myLinkedList.findKthFromEnd(k).value;

        System.out.println(result); // Output: 4
		
	}
	
	//Calcolo fattoriale
	public static int ricorsione(int x) {
		int fattoriale;
		// Se argomento x = 0 allora fattoriale = 1
		if (x == 0) 
			fattoriale = 1;
		else 
			fattoriale = x * (ricorsione(x-1));
		return fattoriale;
	}
}


