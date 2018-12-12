import java.util.ArrayList;

public class Player extends Person {
	private String name; //玩家姓名
	private int chips; //玩家有的籌碼
	private int bet; //玩家此局下注的籌碼 
	
	public Player(String name, int chips) { //新增Player物件時，需要姓名、擁有的籌碼等兩個參數
		this.name = name;
		this.chips = chips;
	}
	public String getName() { //name的getter
		return name;	
	}
	public int makeBet() {
		if(chips != 0) { //檢查是否還有錢，沒錢了就不能再繼續下注
			bet=1; //下注：一次1元
		}
        return bet; //回傳預計下注的籌碼基本下注
	}
	public int getTotalValue() {
		 int TotalValue = 0;
		 for(int n=0; n<this.getOneRoundCard().size(); n++) {
			 if(this.getOneRoundCard().get(n).getRank() == 11 || this.getOneRoundCard().get(n).getRank() == 12 || this.getOneRoundCard().get(n).getRank() == 13)
		    {
				 TotalValue += 10;
		    }
			 TotalValue += this.getOneRoundCard().get(n).getRank();
	     }
		 return TotalValue;  //回傳此牌局所得的卡點數加總
	}
    public int getCurrentChips() { //回傳玩家現有籌碼
    	return chips;
    }
    public void increaseChips (int diff) { //玩家籌碼變動，setter
    	chips += diff;
    }

	public void sayHello() { //玩家Say Hello
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	@Override
	public boolean hit_me(Table table) {
		int TotalValue = getTotalValue();
		if (TotalValue >= 17) { //16點以下要牌，17點以上不要牌 => 是否要牌，是回傳true，不再要牌則回傳false
			return false;
		} else {
			return true;
		}
	}
} 

