package test.air.service;

public class Result {

	private Result leftOprnd;
	private Result rightOprnd;
	private double value;
	private String operator;
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Result(){
	}
	
	public Result(double value){
		this.value=value;
		
	}
	
	public Result getLeftOprnd() {
		return leftOprnd;
	}
	public void setLeftOprnd(Result leftOprnd) {
		this.leftOprnd = leftOprnd;
	}
	public Result getRightOprnd() {
		return rightOprnd;
	}
	public void setRightOprnd(Result rightOprnd) {
		this.rightOprnd = rightOprnd;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	

	}
