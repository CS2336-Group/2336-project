package compression;

public class TestBWT{
	public static void main(String[] args){
		BWTEncoding str1 = new BWTEncoding("THE LEGEND OF ST. NICHOLAS\nThe legend of Santa Claus can be traced back hundreds of years to a monk named St. Nicholas. It is believed that Nicholas was born sometime around 280 A.D. in Patara, near Myra in modern-day Turkey. Much admired for his piety and kindness, St. Nicholas became the subject of many legends. It is said that he gave away all of his inherited wealth and traveled the countryside helping the poor and sick. One of the best known of the St. Nicholas stories is that he saved three poor sisters from being sold into slavery or prostitution by their father by providing them with a dowry so that they could be married. Over the course of many years, Nicholas’s popularity spread and he became known as the protector of children and sailors. His feast day is celebrated on the anniversary of his death, December 6. This was traditionally considered a lucky day to make large purchases or to get married. By the Renaissance, St. Nicholas was the most popular saint in Europe. Even after the Protestant Reformation, when the veneration of saints began to be discouraged, St. Nicholas maintained a positive reputation, especially in Holland.\n\nSINTER KLAAS COMES TO NEW YORK\nSt. Nicholas made his first inroads into American popular culture towards the end of the 18th century. In December 1773, and again in 1774, a New York newspaper reported that groups of Dutch families had gathered to honor the anniversary of his death.\n\nThe name Santa Claus evolved from Nick’s Dutch nickname, Sinter Klaas, a shortened form of Sint Nikolaas (Dutch for Saint Nicholas). In 1804, John Pintard, a member of the New York Historical Society, distributed woodcuts of St. Nicholas at the society’s annual meeting. The background of the engraving contains now-familiar Santa images including stockings filled with toys and fruit hung over a fireplace. In 1809, Washington Irving helped to popularize the Sinter Klaas stories when he referred to St. Nicholas as the patron saint of New York in his book, The History of New York. As his prominence grew, Sinter Klaas was described as everything from a \"rascal\" with a blue three-cornered hat, red waistcoat, and yellow stockings to a man wearing a broad-brimmed hat and a \"huge pair of Flemish trunk hose.\"");
		
		System.out.println(str1.rotatingString);
		System.out.println(str1.stringArraySize);
		
		String encoded = "";
		encoded = str1.encode();
		
		System.out.println(encoded);
		
		BWTDecoding comStr1 = new BWTDecoding(encoded);
		String decoded = "";
		decoded = comStr1.decode();
		
		System.out.println(decoded);
	}
}
