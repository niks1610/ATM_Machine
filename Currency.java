public class Currency {

	private int amount;
	private int limit;
	
	public Currency(int amt,int limit){
		this.amount=amt;
		this.limit=limit;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public int getLimit(){
	    return this.limit;
	}
	
	public void changeLimit(int num)
	{
	    this.limit = limit - num;
	}
	
	public void inc_Limit(int num)
	{
	    this.limit = limit + num;
	}
	
	
}