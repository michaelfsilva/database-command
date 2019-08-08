package br.com.databasecommand.model;

/**
 * LE Schema Entity File table model without key fields.
 * 
 * @author michael_silva
 *
 */

public class LeEntityFile {
	private String comment;
	private String image;
	private String imageName;
	private long length;
	private String mime;
	private String name;
	private String type;
	private String uploadDate;
	private String uploadDateTz;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadDateTz() {
		return uploadDateTz;
	}

	public void setUploadDateTz(String uploadDateTz) {
		this.uploadDateTz = uploadDateTz;
	}

	/**
	 * Prepare a unique string with all information of LE Entity File and return.
	 */	
	@Override
	public String toString() {
		String returned = this.getName() + ", ";
		returned += this.getComment() + ", ";
		returned += this.getImage() + ", ";
		returned += this.getImageName() + ", ";
		returned += this.getLength() + ", ";
		returned += this.getMime() + ", ";
		returned += this.getName() + ", ";
		returned += this.getType() + ", ";
		returned += this.getUploadDate() + ", ";
		returned += this.getUploadDateTz() + ", ";

		return returned;
	}
}
