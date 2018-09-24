package com.minesweeper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Minesweeper {

	Integer fieldSide;
	Integer[][] bombsPosition;
	
	public Minesweeper(Integer fieldSide, String bombsPositionJson) throws BombsJsonInvalidException {
		super();
		this.fieldSide = fieldSide;
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			BombsJson res = mapper.readValue(bombsPositionJson, BombsJson.class);
			this.bombsPosition = res.getBombsPosition();
			
			//check that each element of the array is setted
			if(this.bombsPosition.length != fieldSide){
				throw new BombsJsonInvalidException("BombsPosition missing");
			}else{
				for (Integer[] row : bombsPosition) {
					if(row.length != fieldSide){
						throw new BombsJsonInvalidException("BombsPosition missing");
					}
				}
			}
			
		}catch (Exception e) {
			throw new BombsJsonInvalidException("Bomb position Json not valid");
		}
		
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
		
		Integer checkPositionResult = null;
		try {
			checkPositionResult = this.bombsPosition[y][x];
		} catch (Exception e) {
			return checkPositionResult;
		}
		
		if(checkPositionResult == 1){
			return -1;
		}
		
		int bombNumerRowBeforeLeft = getBombsNumber(x-1,y-1);
		int bombNumerRowBeforeCenter = getBombsNumber(x,y-1);
		int bombNumerRowBeforeRight = getBombsNumber(x+1,y-1);
		int bombNumerRowActualLeft = getBombsNumber(x-1,y);
		int bombNumerRowActualRight = getBombsNumber(x+1,y);
		int bombNumerRowAfterLeft = getBombsNumber(x-1,y+1);
		int bombNumerRowAfterCenter = getBombsNumber(x,y+1);
		int bombNumerRowAfterRight = getBombsNumber(x+1,y+1);
		
		return bombNumerRowBeforeLeft +bombNumerRowBeforeCenter + bombNumerRowBeforeRight +bombNumerRowActualLeft +
				bombNumerRowActualRight + bombNumerRowAfterLeft +bombNumerRowAfterCenter + bombNumerRowAfterRight;
		
	}
	
	private Integer getBombsNumber(int x, int y){
		
		try {
			return this.bombsPosition[y][x];
		} catch (Exception e) {
			return 0;
		}
	}

}
