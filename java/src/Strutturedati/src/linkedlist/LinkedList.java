package linkedlist;

public class LinkedList {
	
	private Node head;
	private Node tail;
	private int length;
	
	
	public LinkedList(int value) {
		// Alla nuova istanza della lista
		// si realizza un nuovo nodo con un valore.
		Node newNode = new Node(value);
		// Quest'unico nodo diventa testa e coda
		head = newNode;
		tail = newNode;
		length++;	// Aumentiamo di un unità la lunghezza
	}
	
	public void append(int value) {
		// Realizziamo un nuovo nodo a priori
		Node newNode = new Node(value);
		/* Se la lista non esiste, allora
			il nuovo nodo è testa e coda */
		if (length == 0) {
			head = newNode;
			tail = newNode;
		} else {
			/* Se la lista esiste
			   accodiamo come nuova coda */
			tail.next = newNode;	// accodiamo dopo la coda attuale ...
			tail = newNode;			// diventa la coda attuale
		}
		length++; // a priori aumentiamo la lista
	}
	
	// Rimuovi l'ultimo elemento
	public Node removeLast() {
		// Se la lista è nulla, restuiamo nodo nullo
		if (length==0) return null;
		/* Se esiste la lista
			cerchiamo il penultimo nodo
			cioè quello immediatamente
			prima della coda
			tramite 2 nodi buffer
			pre e temp 
			inizializzandoli all'head */
		
		Node temp = head;
		Node pre = head;
		/* Avviamo il ciclo while per cercare
			il penultimo nodo */
		while (temp.next != null) {
			pre = temp; 		// Sincronizziamo il buffer pre a temp
			temp = temp.next; 	// Avanziamo temp
		}
		/* 	Alla fine di questo ciclo,
			avremmo raggiunto il penultimo nodo
			Questo nodo è la nuova coda */
		tail = pre;
		// Eliminiamo l'ultimo nodo
		tail.next = null;
		// Riduciamo la lunghezza di un unità
		length--;
		// Se avessimo raggiunto lunghezza 0
		// annulliamo testa e coda
		if (length == 0) {
			head = null;
			tail = null;
		}
		// A priori restituiamo temp
		return temp;
	}
	
	
	// Visualizziamo la lista
	public void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}
	
	// Prepend
	public void prepend(int value) {
		// A priori realizziamo il nodo
		Node newNode = new Node(value);
		// Se la lista è nulla, 
		// questo nodo diventa testa e coda
		if (length==0) {
			head = newNode;
			tail = newNode;
		} else {
			// Proseguiamo col caso che la lista non sia vuota
			// esiste allora una testa
			// Allora la testa attuale diventerà il next del
			// nuovo nodo
			newNode.next = head;   // Accodiamo prima della testa attuale
			head = newNode; // Il nuovo nodo diventa la testa
		};
		length++;	// Aggiungiamo un nodo, quindi la lunghezza aumenta
	}
	
	// Rimuoviamo il primo elemento
	public Node removeFirst() {
		// Se la lista è vuota, restituire nullo
		if (length == 0) return null;
		// Vediamo il caso la lista non sia vuota
		// Esisterà quindi una testa
		// Dovremo rimuovere quella testa
		// Assegnando il ruolo di testa all' eventuale
		// nodo successivo
		Node temp = head;	// Salviamo in un buffer l'attuale head
		// La nuova testa è il successivo della testa attuale
		head = head.next;
		// il nodo buffer pre fa riferimento al vecchio nodo
		// eliminando il riferimento al suo nodo next,
		// eliminiamo il nodo stesso
		temp.next = null;
		length--;	// Abbiamo tolto un nodo
		// Se la lunghezza dovesse diventare 0
		// allora la coda andrebbe annullata
		if (length == 0) {
			tail = null;
		}
		return temp;
	}
	
	// Get
	public Node get(int index) {
		// Poniamo il caso non ci sia la lista
		// allora restituiamo null
		if (length == 0) return null;
		// Di seguito se l'indice è minore di zero,
		// oppure è maggiore della lunghezza,
		// restituiamo anche lì null
		if (index <0 || index > length) return null;
		
		// Facciamo un nodo buffer
		// che parte dalla testa
		Node temp = head;
		// Se esiste la lista, allora percorriamola
		// con un ciclo for, fino a quando l'indice
		// non diventa uguale al valore cercato
		for (int i= 0; i < index; i++) {
			{
				// A ogni passo del for
				// avanziamo nella lista
				temp = temp.next;
			}
		}
		return temp;
	}
	
	// Set
	public boolean set(int index, int value) {
		// se l'indice è minore di zero,
		// oppure è maggiore della lunghezza,
		// restituiamo anche lì null
		if (index <0 || index > length) return false;
		
		if (length == 0) return false;
		
		Node temp = get(index);
		temp.value = value;
		return true;
	}
	
	
	// Insert
	public boolean insert(int index, int value) {
		// Se l'indice è minore di zero 
		// o maggiore della lunghezza
		if (index < 0 || index > length) return false;
		
		// Se dobbiamo inserire prima della testa
		// usiamo prepend
		if (index == 0) {
			prepend(value);
			return true;
		}
		
		// Se dobbiamo inserire dopo la coda
		// usiamo append
		if (index == length) {
			append(value);
			return true;
		}
		
		// Affrontiamo i casi in mezzo
		
		// Realizziamo il nodo da inserire
		Node newNode = new Node(value);
		
		// Si cerca il nodo immediatamente
		// precedente al nodo dell'indice
		Node temp = get(index - 1);
		
		
		
		// Allochiamo il nuovo nodo
		// immediatamente dopo temp
		newNode.next = temp.next;
		temp.next = newNode;
		
		// Aggiorniamo lunghezza
		length++;
		
		// Inserimento effettuato
		// restituiamo true
		return true;
	}
	
	
	// Rimuovi
	public Node remove(int index) {
		// Se l'indice è minore di zero 
		// o maggiore della lunghezza
		if (index < 0 || index > length) return null;
		
		// Se dobbiamo rimuovere l'attuale testa
		// usiamo removeFirst
		if (index == 0) {
			return removeFirst();
		}
		
		// Se dobbiamo rimuovere l'attuale coda
		// usiamo removeLast
		if (index == length) {
			return removeLast();
		}
		
		// Se dobbiamo rimuovere in mezzo ...
		// Prendiamo il nodo prima dell'indice ..
		Node pre = get(index - 1);
		// ... e quello dell'indice
		Node temp = get(index);
		
		// Il successivo di pre è l'attule successivo di temp
		pre.next = temp.next;
		// ... l'attuale temp non ha più un successivo
		temp.next = null;
		
		// aggiorniamo la lunghezza
		length--;
		
		// Restituiamo il nodo rimosso
		return temp;
		
	}
	
	
	// Reverse
	public void reverse() {
		// Per prima cosa invertiamo
		// coda e testa
		Node temp = head;
		head = tail;
		tail = temp;
		
		// Realizziamo altri 2 nodi buffer
		// che, visto che siamo all'inizio
		// assumeranno questi 2 valori
		Node after = temp.next;
		Node before = null;
		
		// Avviamo un ciclo ed invertiamo
		// i riferimenti ai nodi
		for (int i = 0; i < length; i++) {
			after = temp.next;
			temp.next = before;
			// Ora ci spostiamo al nodo successivo
			before = temp;
			temp = after;
		}
	}
	
	
	// metodi accessori
	public Node getHead() {
        return head;
		//System.out.println("Head: " + head.value);
    }

    public Node getTail() {
        return tail;
    	// System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }
    
    // Trova il nodo medio
    
    /* This algorithm uses the slow and fast pointer technique, 
     * also known as Floyd's Tortoise and Hare algorithm, 
     * to find the middle element of the linked list. */
    public Node findMiddleNode() {
    	// Initialize slow pointer to the head of the linked list
        Node slow = head;
     
        // Initialize fast pointer to the head of the linked list
        Node fast = head;
     
        // Traverse the linked list with two pointers: slow and fast
        // slow moves one node at a time, while fast moves two nodes at a time
        while (fast != null && fast.next != null) {
            // Move slow pointer to the next node
            slow = slow.next;
     
            // Move fast pointer to the next two nodes
            fast = fast.next.next;
        }
     
        // Return the Node object representing the middle node of the linked list
        return slow;
    	
    }
	
    // Cerca loop
    public boolean hasLoop() {
    	
    	Node slow = head;
        Node fast = head;
 
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
 
            if (slow == fast) {
                return true;
            }
        }
 
        return false;
    }
    
    
    // Cerca nodo Kth
    public Node findKthFromEnd(int k) {
    	
    	Node slow = head;
        Node fast = head;
 
        // Giriamo tutta la lista per
        // vedere se ci sono numeri 
        for (int i= 0; i < k; i++) {
			{
				fast = fast.next;
				
				if (fast == null) return null;

			}
		}
        
        // Se siamo arrivati alla fine
        // è il momento di muovere slow
        while (fast != null) {
        	slow = slow.next;
        	fast = fast.next;
        }
    	return slow;
    	
    }
    
}
