package br.com.databasecommand.model;

/**
 * Label Component table model without key fields.
 * 
 * @author michael_silva
 *
 */

public class LabelComp {
	private String printerType;
	private String printCmdTitle;
	private String printCmdString;
	private int printCmdType;
	private int printRowPosition;
	private int printColumnPosition;
	private String fontCmdString;
	private String rotation;
	private int labelType;
	private String dataId;
	private boolean downloadGraphic;
	private boolean mandatoryFlag;
	private String mandatoryFailMsg;
	private String a1;
	private int i1;
	private boolean l1;

	public String getPrinterType() {
		return printerType;
	}

	public void setPrinterType(String printerType) {
		this.printerType = printerType;
	}

	public String getPrintCmdTitle() {
		return printCmdTitle;
	}

	public void setPrintCmdTitle(String printCmdTitle) {
		this.printCmdTitle = printCmdTitle;
	}

	public String getPrintCmdString() {
		return printCmdString;
	}

	public void setPrintCmdString(String printCmdString) {
		this.printCmdString = printCmdString;
	}

	public int getPrintCmdType() {
		return printCmdType;
	}

	public void setPrintCmdType(int printCmdType) {
		this.printCmdType = printCmdType;
	}

	public int getPrintRowPosition() {
		return printRowPosition;
	}

	public void setPrintRowPosition(int printRowPosition) {
		this.printRowPosition = printRowPosition;
	}

	public int getPrintColumnPosition() {
		return printColumnPosition;
	}

	public void setPrintColumnPosition(int printColumnPosition) {
		this.printColumnPosition = printColumnPosition;
	}

	public String getFontCmdString() {
		return fontCmdString;
	}

	public void setFontCmdString(String fontCmdString) {
		this.fontCmdString = fontCmdString;
	}

	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation = rotation;
	}

	public int getLabelType() {
		return labelType;
	}

	public void setLabelType(int labelType) {
		this.labelType = labelType;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public boolean isDownloadGraphic() {
		return downloadGraphic;
	}

	public void setDownloadGraphic(boolean downloadGraphic) {
		this.downloadGraphic = downloadGraphic;
	}

	public boolean isMandatoryFlag() {
		return mandatoryFlag;
	}

	public void setMandatoryFlag(boolean mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}

	public String getMandatoryFailMsg() {
		return mandatoryFailMsg;
	}

	public void setMandatoryFailMsg(String mandatoryFailMsg) {
		this.mandatoryFailMsg = mandatoryFailMsg;
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

	/**
	 * Prepare a unique string with all information of Label component and return.
	 */	
	@Override
	public String toString() {
		String returned = this.getLabelType() + ", ";
		returned += this.getPrinterType() + ", ";
		returned += this.getPrintCmdTitle() + ", ";
		returned += this.getPrintCmdString() + ", ";
		returned += this.getPrintCmdType() + ", ";
		returned += this.getPrintRowPosition() + ", ";
		returned += this.getPrintColumnPosition() + ", ";
		returned += this.getFontCmdString() + ", ";
		returned += this.getRotation() + ", ";
		returned += this.getDataId() + ", ";
		returned += this.isDownloadGraphic() + ", ";
		returned += this.isMandatoryFlag() + ", ";
		returned += this.getMandatoryFailMsg() + ", ";
		returned += this.getA1() + ", ";
		returned += this.getI1() + ", ";
		returned += this.isL1() + ", ";
				
		return returned;
	}
}
