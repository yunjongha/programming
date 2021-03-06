package chapter0_general;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLockCondition {
	
	private static Collection<Suit> suits = Arrays.asList(Suit.values());
	private static Collection<Rank> ranks = Arrays.asList(Rank.values());
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//addCard();
		addAllFaces();
	}
	
	public static void face() {
		
	}

	public static void addCard() throws InterruptedException {
		Card card = new Card();
		

		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(() -> card.add(Suit.CLUB, Rank.ACE));
		
		Thread.sleep(3 * 1000);

		service = Executors.newSingleThreadExecutor();
		service.execute(() -> card.release());
	}

	public static void addAllCards() {
		Card card = new Card();
		for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
			Suit suit = i.next();
			for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
				card.add(suit, j.next());
		}
		
		card.showCard();
	}

	public static void addAllFaces() {
	
		Face[] faces = Face.values();
		for(Face face1 : faces)
			for(Face face2 : faces)
				System.out.println("pair: " + face1 + ", " + face2);
	}
}

enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }

enum Suit { CLUB, DIAMON, HEART, SPADE }

enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }

/**
 * 
 * @author yunjo
 *
 */
final class Card {

	private final Map<Suit, Set<Rank>> cards = new HashMap<>();
	
	private final Lock lock = new ReentrantLock();
	private final Condition cond = lock.newCondition();
	
	public void add(Suit suit, Rank rank) {
		
		System.out.println(Thread.currentThread().getName() + ": start lock!");
		lock.lock();
		System.out.println(Thread.currentThread().getName() + ": locked!");
		try {
			Set<Rank> ranks = cards.get(suit);
			if(ranks == null) {
				ranks = new HashSet<>();
			}
			System.out.println(Thread.currentThread().getName() + ": start wait!");
			cond.await();
			System.out.println(Thread.currentThread().getName() + ": end wait!");
			ranks.add(rank);
			cards.put(suit, ranks);
		} catch (InterruptedException e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	public void release() {

		System.out.println(Thread.currentThread().getName() + ": start lock!");
		lock.lock();
		System.out.println(Thread.currentThread().getName() + ": locked!");
		try {
			System.out.println(Thread.currentThread().getName() + ": start signal!");
			cond.signalAll();
			System.out.println(Thread.currentThread().getName() + ": end signal!");
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public void showCard() {
		for(Iterator<Suit> it = cards.keySet().iterator(); it.hasNext(); ) {
			Suit suit = it.next();
			Set<Rank> ranks = cards.get(suit);
			System.out.print("\nsuit: " + suit + ", rank: ");
			ranks.forEach( x -> System.out.print(x + ", "));
		}
	}
}
