package converter;

public class Test {
	public static void main(String[] args) {
		Document doc = new Document("Japan.docx");
		String paragraph = "Japan is an island nation in the Pacific Ocean with dense cities, imperial palaces, mountainous national parks and thousands of shrines and temples. Shinkansen bullet trains connect the main islands of Kyushu (with Okinawa's subtropical beaches), Honshu (home to Tokyo and Hiroshima’s atomic-bomb memorial) and Hokkaido (famous for skiing). Tokyo, the capital, is known for skyscrapers, shopping and pop culture.";
		Element title = new Title(doc, "Japan");
		Element heading = new Heading(doc, "Introduction");
		Element body = new Body(doc, paragraph);
		title.formatAndAppend(title.getText());
		heading.formatAndAppend(heading.getText());
		body.formatAndAppend(body.getText());
		
		doc.writeDoc();
	}
}
