import java.util.*;

public class Main {

	private DenomChain c1;
	
	//
    private	Currency cur1;
    private	Currency cur2;
    private	Currency cur3;
    private	Currency cur4;
    protected int avail_balance;
    public static ArrayList<Currency> cur_rem;
    
    
    
    	

	public Main() {
	    
	    //initialise currency for ATM with 4-Rs2000, 3-Rs.500, 2-Rs.200 and 1-Rs.100
	    cur1= new Currency(2000,4);
        cur2= new Currency(500,3);
        cur3= new Currency(200,2);
        cur4= new Currency(100,1);
        
        
        //cur remained in ATM
		cur_rem = new ArrayList();
	    cur_rem.add(cur1);
        cur_rem.add(cur2);
        cur_rem.add(cur3);
        cur_rem.add(cur4);
        
        //initialise avail_balance
        for(int i =0;i<cur_rem.size();i++)
        {
            avail_balance += cur_rem.get(i).getLimit()*cur_rem.get(i).getAmount();
        }
	
	    //chain of responsibility
		this.c1 = new Denom2000();
		DenomChain c2 = new Denom500();
		DenomChain c3 = new Denom200();
		DenomChain c4 = new Denom100();

		
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		c3.setNextChain(c4);
	}


    public int avail_bal()
    {
        return avail_balance;
    }
	public static void main(String[] args) {
		Main ATM = new Main();
		while (true) {
			int amount = 0;
			System.out.println("Enter amount to dispense");
			Scanner input = new Scanner(System.in);
			amount = input.nextInt();
			
			if(ATM.avail_bal()<amount)
			{
			    System.out.println("Sorry, Insufficient Balance.");
			    return;
			}
			if (amount % 100 != 0) {
				System.out.println("Amount should be in multiple of 100s.");
				return;
			}
			
			
			boolean result = ATM.c1.Withdraw(new Currency(amount,cur_rem.get(0).getLimit()),cur_rem);
			
			System.out.println("Payment Successful: "+ result);
			
			if(result)
			ATM.avail_balance -= amount;
			
			
			//if want to see available balance in ATM
		//	System.out.println("Available balance = "+ATM.avail_balance);
		}

	}

}