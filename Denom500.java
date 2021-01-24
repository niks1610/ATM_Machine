import java.util.*;

public class Denom500 implements DenomChain {

	private DenomChain chain;
	
	@Override
	public void setNextChain(DenomChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public boolean Withdraw(Currency cur,ArrayList<Currency> cur_rem)  {
	    
	    boolean result = false;
		if(cur.getAmount() >= 500){
			int num = cur.getAmount()/500;
			
			if(cur_rem.get(1).getLimit()>=num){
			    cur_rem.get(1).changeLimit(num);
			    int remainder = cur.getAmount() % 500;
    			System.out.println("Withdrawing "+num+"*Rs.500 note");
    		
    			if(remainder !=0) 
    			{result = this.chain.Withdraw(new Currency(remainder,cur_rem.get(2).getLimit()),cur_rem);}
    			else 
    			{
    			    result = true;
    			}
    			
    			if(!result)
    			{
    			    cur_rem.get(1).inc_Limit(num);
    			}
			}
			
			
			else if(cur_rem.get(1).getLimit()>0 )
			{
			
			int remainder = cur.getAmount()-cur_rem.get(1).getLimit()*500;
			System.out.println("Withdrawing "+(cur_rem.get(1).getLimit())+"*Rs.500 note");
			cur_rem.get(1).changeLimit(cur_rem.get(1).getLimit());
			if(remainder !=0) 
			{result = this.chain.Withdraw(new Currency(remainder,cur_rem.get(2).getLimit()),cur_rem);}
			else return true;
			}
			else{
			result =  this.chain.Withdraw(cur,cur_rem);
	    	}
			
			if(!result)
			{
			    cur_rem.get(1).inc_Limit(num);
			}
		}else{
		    result =  this.chain.Withdraw(cur,cur_rem);
		}
		
		return result;
	}

}