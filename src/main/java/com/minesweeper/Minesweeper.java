package com.minesweeper;

import java.util.Map;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

public class Minesweeper {

	private static String bombPositionnKey = "bombsPosition";
	
	Integer fieldSide;
	Integer fieldLength;
	String bombsPosition;
	
	public Minesweeper(Integer fieldSide, String bombsPositionJson) {
		super();
		this.fieldSide = fieldSide;
		this.fieldLength = (fieldSide * fieldSide) -1 ;
		
		JsonParser jsonParser = new BasicJsonParser();
		Map<String, Object> jsonMap = jsonParser.parseMap(bombsPositionJson);
		this.bombsPosition = (String) jsonMap.get(Minesweeper.bombPositionnKey);
		
	}
		
	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	/*
	 * 	parmas: x position and y position of the game field
	 * 
	 *	return values:
	 *	null : position is not valid
	 *	-1 : there is a bomb in this point
	 *	0: there is not bombs close to this point
	 *	1-8: there are bombs close to this point 
	 */
	public Integer checkPosition(int x, int y){
		
		int position = y * fieldSide + x;
		
		if( x<0 || x>fieldSide-1 || y<0 || y>fieldSide-1){
			return null;
		}else if(getBombNumber(position) == 1){
			return -1;
		}else{
			int bombNumerRowBeforeLeft = (y > 0 && x > 0)? getBombNumber(position - fieldSide - 1):0;
			int bombNumerRowBeforeCenter = (y > 0)? getBombNumber(position - fieldSide):0;
			int bombNumerRowBeforeRight = (y > 0 && x < fieldSide - 1)? getBombNumber(position - fieldSide + 1):0;
			int bombNumerRowActualLeft = (x > 0)? getBombNumber(position -1):0;
			int bombNumerRowActualRight = (x < fieldSide -1)? getBombNumber(position +1):0;
			int bombNumerRowAfterLeft = (y < fieldSide - 1 && x > 0)? getBombNumber(position + fieldSide - 1):0;
			int bombNumerRowAfterCenter = (y < fieldSide - 1)? getBombNumber(position + fieldSide):0;
			int bombNumerRowAfterRight = (y < fieldSide - 1 && x < fieldSide -1)? getBombNumber(position + fieldSide + 1):0;
			
			return bombNumerRowBeforeLeft + bombNumerRowBeforeCenter + bombNumerRowBeforeRight + 
					bombNumerRowActualLeft + bombNumerRowActualRight + bombNumerRowAfterLeft + bombNumerRowAfterCenter
					+ bombNumerRowAfterRight;
		}

	}
	
	public Integer getBombNumber(int position){
		if(position >= 0 && position <= fieldLength){
			return Integer.valueOf(bombsPosition.substring(position, position+1));
		}else{
			return 0;
		}
	}

}
