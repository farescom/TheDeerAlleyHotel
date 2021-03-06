package system.domain.model;

import java.io.Serializable;

/** represents users DoubleRoom 
 * @param type 1 - with a king size bed, 2- twin beds.,
 * */
public class DoubleRoom extends NormalRoom implements Serializable
{
	int type;		// 1 - with a king size bed, 2- twin beds.,
	
	public DoubleRoom(int id, int price, int type)
	{
		super(id, price, 2);
		this.type = type;
	}

	public DoubleRoom(int id, int type)
	{
		super(id, 160, 2);
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Double room" + (type == 1 ? "king size bed" : "twin beds") +
				". id: " + id + ", bedrooms: "  + number_of_bedrooms + ", price: " + price + ".";
	}
}
