package test.air.service;

import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {
	private Stack<Result> stack = new Stack<Result>();

	public static void main(String args[]) {
		RPNCalculator calc = new RPNCalculator();
		calc.launch();
	}

	public void launch() {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		while (input != null && !input.isEmpty()) {
			this.processInput(input);
			input = in.nextLine();
		}
		in.close();
	}

	private void processInput(String input) {
		String[] strs = input.split("\\s+");
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i].toLowerCase();
			if (str.matches("(?i)\\+|-|\\*|\\/|sqrt")) {
				if (stack.isEmpty() || stack.size() < 2 && !"sqrt".equals(str)) {
					System.out.println("operator " + str + " (position: " + (i * 2 - 1) + "): insufficient parameters");
					break;
				}
				Result res = new Result();
				res.setOperator(str);
				res.setRightOprnd(stack.pop());
				if (!"sqrt".equals(str)) {
					res.setLeftOprnd(stack.pop());
				}
				// push the operand back to stack
				compute(res);
				stack.push(res);
			} else if ("undo".equals(str)) {
				if (!stack.isEmpty()) {
					Result top = stack.pop();
					if (top.getLeftOprnd() != null) {
						stack.push(top.getLeftOprnd());
					}
					if (top.getRightOprnd() != null) {
						stack.push(top.getRightOprnd());
					}
				}
			} else if ("clear".equals(str)) {
				stack.clear();
			} else { // when input is operand
				stack.push(new Result(Double.parseDouble(str)));
			}
			// ignore the random input case
		}
		printStack();
	}

	private void compute(Result res) {
		Double left = res.getLeftOprnd() == null ? null : res.getLeftOprnd().getValue();
		double right = res.getRightOprnd().getValue();
		double output = 0;
		switch (res.getOperator()) {
		case "+":
			output = left + right;
			break;
		case "-":
			output = left - right;
			break;
		case "*":
			output = left * right;
			break;
		case "/":
			output = left / right;
			break;
		case "sqrt":
			output = Math.sqrt(right);
			break;
		default:
			break;
		}
		res.setValue(output);
	}

	private void printStack() {
		System.out.println(String.format("stack: %s", getStackElements()));
	}

	private String getStackElements() {
		StringBuffer sb = new StringBuffer();
		for (Result res : stack) {
			Double value = res.getValue();
			sb.append(String.format("%.10f", value).replaceFirst("\\.?0+$", "") + " ");
		}
		return sb.toString().trim();
	}
}