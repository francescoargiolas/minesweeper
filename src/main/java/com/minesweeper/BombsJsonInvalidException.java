package com.minesweeper;

public class BombsJsonInvalidException extends Exception{
	String message;

	public BombsJsonInvalidException(String message) {
		super();
		this.message = message;
	}
	
	
}
