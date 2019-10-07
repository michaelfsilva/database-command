package br.com.databasecommand.model;

/**
 * Label Part table model without key fields.
 * 
 * @author michael_silva
 *
 */

public class LabelPart {
	private int labelType;
	private int i1;
	private String labelText1;
	private String labelText2;
	private String labelText3;
	private String labelText4;
	private String labelText5;
	
	public int getLabelType() {
		return labelType;
	}
	public void setLabelType(int labelType) {
		this.labelType = labelType;
	}
	public int getI1() {
		return i1;
	}
	public void setI1(int i1) {
		this.i1 = i1;
	}
	public String getLabelText1() {
		return labelText1;
	}
	public void setLabelText1(String labelText1) {
		this.labelText1 = labelText1;
	}
	public String getLabelText2() {
		return labelText2;
	}
	public void setLabelText2(String labelText2) {
		this.labelText2 = labelText2;
	}
	public String getLabelText3() {
		return labelText3;
	}
	public void setLabelText3(String labelText3) {
		this.labelText3 = labelText3;
	}
	public String getLabelText4() {
		return labelText4;
	}
	public void setLabelText4(String labelText4) {
		this.labelText4 = labelText4;
	}
	public String getLabelText5() {
		return labelText5;
	}
	public void setLabelText5(String labelText5) {
		this.labelText5 = labelText5;
	}
	
	/**
	 * Prepare a unique string with all information of Label part and return.
	 */	
	@Override
	public String toString() {
		String returned = this.getLabelType() + ", ";
		returned += this.getI1() + ", ";
		returned += this.getLabelText1() + ", ";
		returned += this.getLabelText2() + ", ";
		returned += this.getLabelText3() + ", ";
		returned += this.getLabelText4() + ", ";
		returned += this.getLabelText5() + ", ";
		
		return returned;
	}
}
