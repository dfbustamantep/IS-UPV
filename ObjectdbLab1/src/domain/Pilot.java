package domain;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity

public class Pilot {
	 @Id
	String name;
	String nationality;
	int points;
	
	public Pilot(String name, String naz, int x){
		this.name=name;
		this.nationality =naz;
		this.points=x;
	}
	
	public String toString(){
		return name+" "+Integer.toString(points);
	}
	
	public void addPoints(int x){
		this.points= this.points+x;
	}
	
	public String getName(){
		return this.name;
	}
}