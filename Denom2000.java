import java.util.*;

public class Denom2000 implements DenomChain {

	private DenomChain chain;
	
	@Override
	public void setNextChain(DenomChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public boolean Withdraw(Currency cur,ArrayList<Currency> cur_rem)  {
	    boolean result=false;
		if(cur.getAmount() >= 2000 ){
			int num = cur.getAmount()/2000;
			
			if(cur_rem.get(0).getLimit()>=num)
			{
			cur_rem.get(0).changeLimit(num);
			int remainder = cur.getAmount() % 2000;
			System.out.println("Withdrawing "+num+"*Rs.2000 note");
			if(remainder !=0) 
			{result = this.chain.Withdraw(new Currency(remainder,cur_rem.get(1).getLimit()),cur_rem);}
			else result =  true;
			
			
			if(!result)
			{
			    cur_rem.get(0).inc_Limit(num);
			}
			}
			
			else if(cur_rem.get(0).getLimit()>0 )
			{
			
			int remainder = cur.getAmount()-cur_rem.get(0).getLimit()*2000;
			System.out.println("Withdrawing "+(cur_rem.get(0).getLimit())+"*Rs.2000 note");
			cur_rem.get(0).changeLimit(cur_rem.get(0).getLimit());
			if(remainder !=0) 
			{result = this.chain.Withdraw(new Currency(remainder,cur_rem.get(1).getLimit()),cur_rem);}
			else result =  true;
			
			if(!result)
			{
			    cur_rem.get(0).inc_Limit(num);
			}
			
			
			}
			
			
			else{
			    result = this.chain.Withdraw(cur,cur_rem);
			}
			
		}
		    else{
                result = this.chain.Withdraw(cur,cur_rem);
		}
		
		
		return result;
	}
	    

}