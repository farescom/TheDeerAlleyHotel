package system.domain.model;

import java.io.Serializable;

/** represents a normal room (not a suit) */
abstract public class NormalRoom extends Room implements Serializable
{
	NormalRoom(int id, int price, int number_of_bedrooms)
	{
		super(id, price, number_of_bedrooms);
	}
}