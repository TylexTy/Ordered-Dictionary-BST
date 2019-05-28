/**
 * 
 * @author Tyler Bulley
 *ID: 250498520
 *
 * Class storing a Record(Pair,data)
 */
public class Record {

	private Pair key;
	private String data;
	
	public Record(Pair key, String data) {
		
	this.key = key;
	this.data = data;
	}
	
	public Pair getKey() {
		return key;
	}
	
	public String getData() {
		return data;
	}
}
