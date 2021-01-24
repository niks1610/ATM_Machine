import java.util.*;

public class Denom200 implements DenomChain {

	private DenomChain chain;
	
	@Override
	public void setNextChain(DenomChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public boolean Withdraw(Currency cur,ArrayList<Currency> cur_rem) {
	    boolean result = false;
	    
		if(cur.getAmount() >= 200){
			int num = cur.getAmount()/200;
			
			if(cur_rem.get(2).getLimit()>=num){
			    cur_rem.get(2).changeLimit(num);
			    int remainder = cur.getAmount() % 200;
    			System.out.println("Withdrawing "+num+"*Rs.200 note");
    		
    			if(remainder !=0) 
    			{
    			    result = this.chain.Withdraw(new Currency(remainder,cur_rem.get(3).getLimit()),cur_rem);
    			}
    			else 
    			{
    			    result = true;
    			}
    			
    			if(!result)
    			{
    			    cur_rem.get(2).inc_Limit(num);
    			}
			}
			
			else if(cur_rem.get(2).getLimit()>0 )
			{
			
			int remainder = cur.getAmount()-cur_rem.get(2).getLimit()*200;
			System.out.println("Withdrawing "+(cur_rem.get(2).getLimit())+"*Rs.200 note");
			cur_rem.get(2).changeLimit(cur_rem.get(2).getLimit());
			if(remainder !=0) 
			{result = this.chain.Withdraw(new Currency(remainder,cur_rem.get(3).getLimit()),cur_rem);}
			else return true;
			}
			
			
			else{
			result = this.chain.Withdraw(cur,cur_rem);
		    }
		    
		    
		    if(!result)
			{
			    cur_rem.get(2).inc_Limit(num);
			}
			
		}else{
			result = this.chain.Withdraw(cur,cur_rem);
		}
		return result;
	}

}