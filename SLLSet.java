
/*
	Utpal Patel
	2SH4- Lab 5
*/


public class SLLSet {

    int size;
    SLLNode head;

    public SLLSet() {
        head = new SLLNode (0,null);
        size = 0;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    
    
    
    public SLLSet(int[] sortedArray) {
        size = sortedArray.length;                 
        head = new SLLNode(sortedArray[0], null);  
        SLLNode current = head;

        for (int i = 1; i < size; ++i) 
        {          
            current.next = new SLLNode(sortedArray[i], null);
            current = current.next;
        }
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public int getSize() {                          
        return size;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public SLLSet copy() {                          
        int[] u = new int[this.size];
        int i = 0;
        
        for (SLLNode p = this.head; p != null; p = p.next, i++) {
            u[i] = p.value;
        }
        SLLSet s = new SLLSet(u);
        return s;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public boolean isIn(int v) 
    {
        for (SLLNode p = head; p != null; p = p.next) {  
            if (p.value == v) {                          
                return true;
            }
        }
        return false;
    }

    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public void add(int v) {
        if (head.value > v) {
            head = new SLLNode(v, head);                              
            size = size + 1;
        } else if (head.value < v) {                                 
            SLLNode a = head, b = null;
            while (a != null && !(a.value >= v)) {
                b = a;
                a = a.next;

            }
            if (a != null && a.value != v) {                         
                b.next = new SLLNode(v, a);                
                size = size + 1;
            } else if (a == null) {                                     
                b.next = new SLLNode(v, a);
                size = size + 1;
            }
        }
    }

    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public void remove(int v) {
        if (head == null) {                  
            return;
        }
        if (head.value == v) {                
            head = head.next;
            size = size - 1;
            return;
        }
        SLLNode a = head, b = null;               
        while (a != null && a.value != v) {
            b = a;
            a = a.next;
        }
        
        if (!(a == null)) {
            b.next = a.next;
            size = size - 1;
        }
    }
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    public SLLSet union(SLLSet s) { 
        if (s.head == null) {                            
            return this;
            
        } else if (this.head == null) {
            return s;
        }

        SLLSet set = new SLLSet();                              
        SLLNode a = s.head, b = this.head;

        if (a.value < b.value) 
        {
            set.head = new SLLNode(a.value, null);
            set.size = size + 1;
            a = a.next;
        } else if (a.value > b.value) {
            set.head = new SLLNode(b.value, null);
            b = b.next;
            set.size = size + 1;
        } else if (a.value == b.value) {
            set.head = new SLLNode(b.value, null);
            a = a.next;
            b = b.next;
            set.size = size + 1;
        }
        SLLNode current = set.head;

        while (a != null & b != null) {                                     
            if (a.value < b.value) {
                current.next = new SLLNode(a.value, null);
                current = current.next;
                set.size = size + 1;
                a = a.next;
            } else if (a.value == b.value) {
                current.next = new SLLNode(a.value, null);
                current = current.next;
                b = b.next;
                a = a.next;
               set.size = size + 1;
            } else if (a.value > b.value) {
                current.next = new SLLNode(b.value, null);
                current = current.next;
                b = b.next;
                set.size = size + 1;
            }

        }

        if (a == null) {                                              
            while (b != null) {
                current.next = new SLLNode(b.value, null);
                current = current.next;
                b = b.next;
                set.size = size + 1;
            }
        } else if (b == null) {
            while (a != null) {
                current.next = new SLLNode(a.value, null);
                current = current.next;
                a = a.next;
                set.size = size + 1;
            }
        }
        return set;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public SLLSet intersection(SLLSet s) {

        SLLSet set = new SLLSet();                                  
        if (s.head == null) {
            return set;
        } else if (this.head == null) {
            return set;
        }
        SLLNode a = this.head, b = s.head;

        while (a != null & b != null) {                                        
            if (a.value < b.value) 
            {
                a = a.next;
            } else if (b.value < a.value)
            {
                b = b.next;
            } else if (a.value == b.value) 
            {
                set.head = new SLLNode(a.value, null);
                a = a.next;
                b = b.next;
                set.size = size + 1;
                break;
            }

        }

        
        SLLNode curr = set.head; 
        while (a != null & b != null) {                                        
            if (a.value < b.value) {
                a = a.next;
            } else if (b.value < a.value) 
            {
                b = b.next;
            } else if (a.value == b.value) 
            {
                curr.next = new SLLNode(a.value, null);
                a = a.next;
                b = b.next;
                curr = curr.next;
                set.size = size + 1;
            }

        }
        return set;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public SLLSet difference(SLLSet s) {
        SLLNode a;
        SLLSet difference = new SLLSet();    
        for (a=head;a!=null;a=a.next)
        {    //SLLSet and //adds it to difference
            difference.add(a.value);          
        }
        
        for (a=s.head.next;a!=null;a=a.next){ 
            difference.remove(a.value);       
        }
        return difference;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public static SLLSet union(SLLSet[] sArray) 
    { 
        SLLSet hold = sArray[0];

        for (int i = 1; i < (sArray.length - 1); i++) {                     
            hold = hold.union(sArray[i]);
        }
        return hold;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public String toString() {
        String hold = "";                                                   
        
        
        for (SLLNode a = head; a != null; a = a.next)
        {
            hold = hold + a.value + ",";
        }
        return hold;
    }
}
