package converter;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<String> texts = new ArrayList<String>();
		texts.add("Japan is great");
		texts.add("Japan has sushi");
		texts.add("Japan is near water");
		texts.add("Japan has a while flag with a red dot in the middle");
		texts.add("Sunrise land");
		
		String paragraph = "Japan is an island nation in the Pacific Ocean with dense cities, imperial palaces, mountainous national parks and thousands of shrines and temples. Shinkansen bullet trains connect the main islands of Kyushu (with Okinawa's subtropical beaches), Honshu (home to Tokyo and Hiroshima’s atomic-bomb memorial) and Hokkaido (famous for skiing). Tokyo, the capital, is known for skyscrapers, shopping and pop culture.";
		String p = "During the Yayoi Period (300 BC to 250 AD), the rice culture was imported into Japan around 100 BC. With the introduction of agriculture, social classes started to evolve, and parts of the country began to unite under powerful land owners. Chinese travellers during the Han and Wei dynasties reported that a queen called Himiko (or Pimiku) reigned over Japan at that time. The Yayoi period brought also the introduction of iron and other modern ideas from Korea into Japan. Again, its pottery gave the period its name.";
		Element title = new Title("Japan");
		Element heading = new Heading("Introduction");
		Element body = new Body(paragraph);
		Element bulletedlist = new BulletedList(texts);
		Element body1 = new Body(p);
		Element heading1 = new Heading("History");
		title.formatAndAppend(title.getText());
		heading.formatAndAppend(heading.getText());
		body.formatAndAppend(body.getText());
		bulletedlist.formatAndAppend(bulletedlist.getPoints());
		body1.formatAndAppend(body1.getText());
		heading1.formatAndAppend(heading1.getText());
		body1.formatAndAppend(body1.getText());
		body1.formatAndAppend(body1.getText());
		
		Document.writeDoc();
		//System.out.println("•");
	}
}
