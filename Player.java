import java.util.ArrayList;

public class Player extends Person {
	private String name; //���a�m�W
	private int chips; //���a�����w�X
	private int bet; //���a�����U�`���w�X 
	
	public Player(String name, int chips) { //�s�WPlayer����ɡA�ݭn�m�W�B�֦����w�X����ӰѼ�
		this.name = name;
		this.chips = chips;
	}
	public String getName() { //name��getter
		return name;	
	}
	public int makeBet() {
		if(chips != 0) { //�ˬd�O�_�٦����A�S���F�N����A�~��U�`
			bet=1; //�U�`�G�@��1��
		}
        return bet; //�^�ǹw�p�U�`���w�X�򥻤U�`
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
		 return TotalValue;  //�^�Ǧ��P���ұo���d�I�ƥ[�`
	}
    public int getCurrentChips() { //�^�Ǫ��a�{���w�X
    	return chips;
    }
    public void increaseChips (int diff) { //���a�w�X�ܰʡAsetter
    	chips += diff;
    }

	public void sayHello() { //���aSay Hello
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	@Override
	public boolean hit_me(Table table) {
		int TotalValue = getTotalValue();
		if (TotalValue >= 17) { //16�I�H�U�n�P�A17�I�H�W���n�P => �O�_�n�P�A�O�^��true�A���A�n�P�h�^��false
			return false;
		} else {
			return true;
		}
	}
} 

