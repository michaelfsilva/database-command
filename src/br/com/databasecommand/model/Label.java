package br.com.databasecommand.model;

/**
 * Label table model without key fields.
 * 
 * @author michael_silva
 *
 */

public class Label {
	private boolean releasedLabel;
	private String modificationTS;
	private int labelType;
	private String altInitString;
	private String procedureName;
	private int partKey;
	private String formatName;
	private int printQuantity;
	private int templateKey;
	private boolean useTemplate;
	private boolean templateFlag;
	private String a1;
	private int i1;
	private boolean l1;
	private String outputDestination;
	private boolean isGeneric;

	public boolean isReleasedLabel() {
		return releasedLabel;
	}

	public void setReleasedLabel(boolean releasedLabel) {
		this.releasedLabel = releasedLabel;
	}

	public String getModificationTS() {
		return modificationTS;
	}

	public void setModificationTS(String modificationTS) {
		this.modificationTS = modificationTS;
	}

	public int getLabelType() {
		return labelType;
	}

	public void setLabelType(int labelType) {
		this.labelType = labelType;
	}

	public String getAltInitString() {
		return altInitString;
	}

	public void setAltInitString(String altInitString) {
		this.altInitString = altInitString;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public int getPartKey() {
		return partKey;
	}

	public void setPartKey(int partKey) {
		this.partKey = partKey;
	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public int getPrintQuantity() {
		return printQuantity;
	}

	public void setPrintQuantity(int printQuantity) {
		this.printQuantity = printQuantity;
	}

	public int getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(int templateKey) {
		this.templateKey = templateKey;
	}

	public boolean isUseTemplate() {
		return useTemplate;
	}

	public void setUseTemplate(boolean useTemplate) {
		this.useTemplate = useTemplate;
	}

	public boolean isTemplateFlag() {
		return templateFlag;
	}

	public void setTemplateFlag(boolean templateFlag) {
		this.templateFlag = templateFlag;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public int getI1() {
		return i1;
	}

	public void setI1(int i1) {
		this.i1 = i1;
	}

	public boolean isL1() {
		return l1;
	}

	public void setL1(boolean l1) {
		this.l1 = l1;
	}

	public String getOutputDestination() {
		return outputDestination;
	}

	public void setOutputDestination(String outputDestination) {
		this.outputDestination = outputDestination;
	}

	public boolean isGeneric() {
		return isGeneric;
	}

	public void setGeneric(boolean isGeneric) {
		this.isGeneric = isGeneric;
	}

	/**
	 * Prepare a unique string with all information of Label and return.
	 */	
	@Override
	public String toString() {
		String returned = this.getLabelType() + ", ";
		returned += this.isReleasedLabel() + ", ";
		returned += this.getModificationTS() + ", ";
		returned += this.getAltInitString() + ", ";
		returned += this.getProcedureName() + ", ";
		returned += this.getPartKey() + ", ";
		returned += this.getFormatName() + ", ";
		returned += this.getPrintQuantity() + ", ";
		returned += this.getTemplateKey() + ", ";
		returned += this.isUseTemplate() + ", ";
		returned += this.isTemplateFlag() + ", ";
		returned += this.getA1() + ", ";
		returned += this.getI1() + ", ";
		returned += this.isL1() + ", ";
		returned += this.getOutputDestination() + ", ";
		returned += this.isGeneric();

		return returned;
	}
}
