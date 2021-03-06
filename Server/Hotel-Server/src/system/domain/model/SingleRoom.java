package system.domain.model;

import java.io.Serializable;

/** a class that represents one single room */
public class SingleRoom extends NormalRoom implements Serializable
{
	public SingleRoom(int id, int price)
	{
		super(id, price, 1);
	}
	
	public SingleRoom(int id)
	{
		super(id, 120, 1);
	}

	@Override
	public String toString()
	{
		return "Single room. id: " + id + ", bedrooms: " +
				number_of_bedrooms + ", price: " + price + ".";
	}
}