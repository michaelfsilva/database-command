package br.com.databasecommand.model;

/**
 * LE Schema Label table model without key fields.
 * 
 * @author michael_silva
 * 
 */

public class LeLabel {
	private String assemblyMustBeMapped;
	private String comment;
	private int concurrentFlag;
	private String name;
	private boolean previewFileExists;
	private String type;
	private String size;
	private String status;
	private int versionNumber;
	private boolean hasInDevVersion;

	public String getAssemblyMustBeMapped() {
		return assemblyMustBeMapped;
	}

	public void setAssemblyMustBeMapped(String assemblyMustBeMapped) {
		this.assemblyMustBeMapped = assemblyMustBeMapped;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getConcurrentFlag() {
		return concurrentFlag;
	}

	public void setConcurrentFlag(int concurrentFlag) {
		this.concurrentFlag = concurrentFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPreviewFileExists() {
		return previewFileExists;
	}

	public void setPreviewFileExists(boolean previewFileExists) {
		this.previewFileExists = previewFileExists;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	public boolean hasInDevVersion() {
		return hasInDevVersion;
	}

	public void setHasInDevVersion(boolean hasInDevVersion) {
		this.hasInDevVersion = hasInDevVersion;
	}

	/**
	 * Prepare a unique string with all information of LE Label and return.
	 */
	@Override
	public String toString() {
		String returned = this.getName() + ", ";
		returned += this.getComment() + ", ";
		returned += this.getConcurrentFlag() + ", ";
		returned += this.getAssemblyMustBeMapped() + ", ";
		returned += this.isPreviewFileExists() + ", ";
		returned += this.getType() + ", ";
		returned += this.getSize() + ", ";
		returned += this.getStatus() + ", ";
		returned += this.getVersionNumber() + ", ";
		returned += this.hasInDevVersion() + ", ";

		return returned;
	}

	/**
	 * Create a hashcode for each label attributes to compare.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((assemblyMustBeMapped == null) ? 0 : assemblyMustBeMapped.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + concurrentFlag;
		result = prime * result + (hasInDevVersion ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (previewFileExists ? 1231 : 1237);
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + versionNumber;
		
		return result;
	}

	/**
	 * Compare two lelabel objects. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		LeLabel other = (LeLabel) obj;
		
		if (assemblyMustBeMapped == null) {
			if (other.assemblyMustBeMapped != null)
				return false;
			
		} else if (!assemblyMustBeMapped.equals(other.assemblyMustBeMapped))
			return false;
		
		if (comment == null) {
			if (other.comment != null)
				return false;
			
		} else if (!comment.equals(other.comment))
			return false;
		
		if (concurrentFlag != other.concurrentFlag)
			return false;
		
		if (hasInDevVersion != other.hasInDevVersion)
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
			
		} else if (!name.equals(other.name))
			return false;
		
		if (previewFileExists != other.previewFileExists)
			return false;
		
		if (size == null) {
			if (other.size != null)
				return false;
			
		} else if (!size.equals(other.size))
			return false;
		
		if (status == null) {
			if (other.status != null)
				return false;
			
		} else if (!status.equals(other.status))
			return false;
		
		if (type == null) {
			if (other.type != null)
				return false;
			
		} else if (!type.equals(other.type))
			return false;
		
		if (versionNumber != other.versionNumber)
			return false;
		
		return true;
	}

}
