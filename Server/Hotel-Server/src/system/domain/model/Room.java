package system.domain.model;

import java.io.Serializable;

abstract public class Room implements Serializable
{
	int id;
	int price;
	int number_of_bedrooms;
	
	Room(int id, int number_of_bedrooms)
	{
		this.id = id;
		this.number_of_bedrooms = number_of_bedrooms;
	}
	
	Room(int id, int price, int number_of_bedrooms)
	{
		this.id = id;
		this.price = price;
		this.number_of_bedrooms = number_of_bedrooms;
	}
	
	public abstract String toString();
	
	public int get_id()
	{
		return id;
	}
	
	public int get_price()
	{
		return price;
	}
	
	public int get_number_of_bedrooms()
	{
		return number_of_bedrooms;
	}
	
	public void set_price(int price)
	{
		this.price = price;
	}
	
	public void set_number_of_bedrooms(int number_of_bedrooms)
	{
		this.number_of_bedrooms = number_of_bedrooms;
	}
}