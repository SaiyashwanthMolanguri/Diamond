import java.util.*;

class Cards implements Comparable<Cards>{

	int pip;
	String suit;
	static String[] names  = new String[14];
	
	static{
		for(int i=0;i<=13;++i)
			names[i]= Integer.toString(i);
		
		names[1]  = "ACE";
		names[11] = "JACK";
		names[12] = "QUEEN";
		names[13] = "KING";
	
	}
	
	
	Cards(int pip , String suit){
		this.pip = pip;
		this.suit = suit;
	}
	
	int getCardNumber(){
		return pip;
	}
	
	String getCardSuit(){
		return suit;
	}
	
	public int compareTo(Cards c2){
			return (c2.getCardNumber() - this.getCardNumber() ); 	
	}
	
	public String toString(){
		 return "( "+names[this.getCardNumber()]+","+ this.getCardSuit()+" ) ";
	}

}

class Deck{

	ArrayList<Cards> deck = new ArrayList<Cards>();
	boolean[] thrown=  new boolean[14];
	Scanner input = new Scanner(System.in);
	
	Deck(String suit){
		for(int i=1;i<=13;++i)
			deck.add(new Cards(i,suit));	
	}
	
	void throwCard(int c){
		
		if( thrown[c] == false)
			thrown[c] = true;
		else{
			
			do{
				System.out.println("!!!!! WARNING : YOU CHOOSE TO THROW A CARD WHICH YOU HAVE ALREDAY THROWN !!!!!");
				c=input.nextInt();
			
			}while(thrown[c] == true);
			
			thrown[c] =true;
			
		}
		
	}
	
	
	public String toString(){
		
		String printDeck="";
		
		for(Cards c: deck)
			if(thrown[c.getCardNumber()] == false)
					printDeck += c;
		
		return printDeck;
	}

}

class Diamond{
	
	int playerScore,computerScore;
	Deck playerDeck ,computerDeck , diamondDeck;
	String winner;
	Scanner input = new Scanner(System.in);
	
	Diamond(){
		
		playerDeck= new Deck("HEARTS");
		computerDeck = new Deck("SPADES");
		diamondDeck =new Deck("DIAMOND");
		Collections.shuffle(diamondDeck.deck, new Random(System.nanoTime()));
		Collections.sort(computerDeck.deck);
	}
	
	int decideComputerMove(int c){
		if(1<=c && c<=7)
			return c+2;
		else if(c == 12 )
			return 2;
		else if(8<=c && c<=11)
			return c+2;
		else
			return 1;
		
	}
	
	
	void beginGame(){
		
		System.out.println("\n\n_________________________ DIAMOND GAME_____________________");
		
		for(int moves=1;moves<=13;++moves)
		{
				System.out.println("\n\nOkay , Now throw your cards when the topmost diamond card is  : " + diamondDeck.deck.get(moves-1)+"\n\n");
				System.out.println("You have these cards left . Among them, choose one and enter an integer correspondingly: ");
				System.out.println(playerDeck);
				System.out.println("\n\n_________________ YOUR MOVE "+ moves +"_________________");
				
				int c = input.nextInt();
				int d = decideComputerMove(diamondDeck.deck.get(moves-1).getCardNumber());
				
				playerDeck.throwCard(c);
				computerDeck.throwCard(d);
				
				System.out.println("Computer moves "+ new Cards(d,"SPADES"));
				
				if(c > d)
					playerScore += diamondDeck.deck.get(moves-1).getCardNumber();
				else if (c < d)
					computerScore += diamondDeck.deck.get(moves-1).getCardNumber();
				else{
					playerScore += (diamondDeck.deck.get(moves-1).getCardNumber())/2;
					computerScore += (diamondDeck.deck.get(moves-1).getCardNumber())/2;
				}
				
				System.out.println("_________________ RESULT OF MOVE "+moves+"_____________________");
				System.out.println("Computer Wins. Current Score is \nPlayer = "+playerScore+" and Computer = " + computerScore);
		}
		
		System.out.println("\n\n_________________ FINAL RESULT_________________");
		System.out.println("\nTotal Player Score = " + playerScore + " Total Computer Score = " + computerScore);
		if(playerScore > computerScore)
			System.out.println("Player wins.Computer Loses");
		else if(playerScore < computerScore)
			System.out.println("Computer wins. Player Loses");
		else
			System.out.println("Its a draw");
		
		System.out.println("Game ends !!! .");
						
	}

}

public class Main{

	public static void main(String[] args){
	
		Diamond game = new Diamond();
		game.beginGame();
			
	}


}



