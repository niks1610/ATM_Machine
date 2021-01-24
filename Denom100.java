import java.util.*;

public class Denom100 implements DenomChain {

	private DenomChain chain;
	
	@Override
	public void setNextChain(DenomChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public boolean Withdraw(Currency cur,ArrayList<Currency> cur_rem) {
	    
	    boolean result = false;
		if(cur.getAmount() >= 100){
			int num = cur.getAmount()/100;
			
    			    if(cur_rem.get(3).getLimit()>=num){
        			    cur_rem.get(3).changeLimit(num);
        			    int remainder = cur.getAmount() % 100;
            			System.out.println("Withdrawing "+num+"*Rs.100 note");
            			
        			    if(remainder !=0) 
        			    {
        			        result = this.chain.Withdraw(new Currency(remainder,cur_rem.get(1).getLimit()),cur_rem);
        			    }
        			    else 
                		{
                		
                			result = true;
                		}
                		
                		if(!result)
            			{
            			    cur_rem.get(3).inc_Limit(num);
            			}
			        }
			        
			        
        			else if(cur_rem.get(2).getLimit()>0) {
        			System.out.println("Try in multiple of 200,500 and 2000.");
        			return false;
        		    }
        		    
        		    else if(cur_rem.get(1).getLimit()>0) {
        			System.out.println("Try in multiple of 500 and 2000.");
        			return false;
        		    }
        		    
        		    else if(cur_rem.get(0).getLimit()>0) {
        			System.out.println("Try in multiple of 2000.");
        			return false;
        		    }
        		    
        		    else
        		    {
        		     System.out.println("Sorry, ATM is Out of cash");
        		     return false;
        		    }
		  
		
    		
		}
		else{
		 result =	this.chain.Withdraw(cur,cur_rem);
		}
		
		return result;
		
		
	}

}