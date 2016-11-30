package umlClasses;

public class Artist extends RegisteredUser{

	public ArtistType type;
	private int id;

	public ArtistType getType() {
		return type;
	}

	public void setType(ArtistType type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
		
}
