package com.minesweeper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

public class Minesweeper {

	private static String BOMB_POSITION_KEY = "bombsPosition";
	
	Integer fieldSide;
	Integer fieldLength;
	String bombsPosition;
	
	public Minesweeper(Integer fieldSide, String bombsPositionJson) {
		super();
		this.fieldSide = fieldSide;
		this.fieldLength = (fieldSide * fieldSide) -1 ;
		
		JsonParser jsonParser = new BasicJsonParser();
		jsonParser.parseMap(bombsPositionJson);
		
		Map<String, Object> jsonMap = null;
		try {
	         jsonMap = jsonParser.parseMap(bombsPositionJson);
	         
	         this.bombsPosition = (String) jsonMap.get(Minesweeper.BOMB_POSITION_KEY);
	    } catch (Exception e) {
	    	throw new RuntimeException("Json that describe bombs position is not valid");
	    }
		
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
	 *	1-8: there are bombs close tho this point 
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
			int bombNumerRowBeforeRight = (y > 0 && x < fieldSide)? getBombNumber(position - fieldSide + 1):0;
			int bombNumerRowActualLeft = (x > 0)? getBombNumber(position -1):0;
			int bombNumerRowActualRight = (x < fieldSide)? getBombNumber(position +1):0;
			int bombNumerRowAfterLeft = (y < fieldSide && x > 0)? getBombNumber(position + fieldSide - 1):0;
			int bombNumerRowAfterCenter = (y < fieldSide)? getBombNumber(position + fieldSide):0;
			int bombNumerRowAfterRight = (y < fieldSide && x < fieldSide)? getBombNumber(position + fieldSide + 1):0;
			
			return bombNumerRowBeforeLeft + bombNumerRowBeforeCenter + bombNumerRowBeforeRight + 
					bombNumerRowActualLeft + bombNumerRowActualRight + bombNumerRowAfterLeft + bombNumerRowAfterCenter
					+ bombNumerRowAfterRight;
		}

	}
	
	private Integer getBombNumber(int position){
		if(position >= 0 && position <= fieldLength){
			return Integer.valueOf(bombsPosition.substring(position, position+1));
		}else 
			return 0;
	}

}
