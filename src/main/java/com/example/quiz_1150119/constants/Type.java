package com.example.quiz_1150119.constants;

public enum Type {

	//type題型
	//前端寫甚麼後端就寫甚麼
	SINGLE("single"),//單選
	MULTI("multi"),//多選
	TEXT("text"),//簡答
	SELECT("select");//下拉
	
	private String type;

	private Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	//是否為type裡的題型
	public static boolean click(String input) {
		/* 寫法一 */
		/* values(): 會取出 enum 中所有的列舉 */
		for(Type item : values()) {
			if(input.equalsIgnoreCase(item.getType())) {
				return true;
			}
		}
//		/* 寫法二 */
//		if(input.equalsIgnoreCase(Type.SINGLE.getType())
//				|| input.equalsIgnoreCase(Type.MULTI.getType())
//				|| input.equalsIgnoreCase(Type.TEXT.getType())
//				|| input.equalsIgnoreCase(Type.SELECT.getType())) {
//			return true;
//		}
		return false;
	}
	
	//是否為選擇題
	public static boolean isChoice(String input) {
		if(input.equalsIgnoreCase(Type.SINGLE.getType())
				||input.equalsIgnoreCase(Type.MULTI.getType())
				||input.equalsIgnoreCase(Type.SELECT.getType())) {
			return true;
		}
		return false;
	}
	
	//檢查是否為單選
	public static boolean isSingleType(String input) {
		return input.equalsIgnoreCase(Type.SINGLE.getType())||
				input.equalsIgnoreCase(Type.SELECT.getType());
	}
	
}
