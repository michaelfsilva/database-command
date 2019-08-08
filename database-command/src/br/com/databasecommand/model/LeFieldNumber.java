package br.com.databasecommand.model;

/**
 * LE Schema Field Number table model without key fields.
 * 
 * @author michael_silva
 *
 */

public class LeFieldNumber {
	private String labelName;
	private int containerPosition;
	private String inputData;
	private String Name;
	private boolean execFunction;
	
	public String getLabelName() {
		return labelName;
	} 

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	} 

	public int getContainerPosition() {
		return containerPosition;
	} 

	public void setContainerPosition(int containerPosition) {
		this.containerPosition = containerPosition;
	} 

	public String getInputData() {
		return inputData;
	} 

	public void setInputData(String inputData) {
		this.inputData = inputData;
	} 

	public String getName() {
		return Name;
	} 

	public void setName(String name) {
		Name = name;
	} 

	public boolean isExecFunction() {
		return execFunction;
	} 

	public void setExecFunction(boolean execFunction) {
		this.execFunction = execFunction;
	}

	/**
	 * Prepare a unique string with all information of LE Field Number and return.
	 */	
	@Override
	public String toString() {
		String returned = this.getLabelName() + ", ";
		returned += this.getContainerPosition() + ", ";
		returned += this.getInputData() + ", ";
		returned += this.getName() + ", ";
		returned += this.isExecFunction() + ", ";

		return returned;
	}
}
