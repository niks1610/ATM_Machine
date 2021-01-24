import java.util.*;

public interface DenomChain {

	void setNextChain(DenomChain nextChain);
	
	boolean Withdraw(Currency cur,ArrayList<Currency> cur_rem) ;
}