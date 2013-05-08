package utility.persistence;

import java.util.ArrayList;

import system.domain.model.DoubleRoom;
import system.domain.model.Room;
import system.domain.model.SingleRoom;
import system.domain.model.Suit;

public class dataProvider
{
	private static final int SUITE_1_BEDROOM = 2;
	private static final int SUITE_2_BEDROOM = 2;
	private static final int SUITE_3_BEDROOM = 3;
	private static final int SINGLE_ROOM = 3;
	private static final int DOUBLE_ROOM_KING_SIZE_BED = 12;
	private static final int DOUBLE_ROOM_TWIN_BEDS = 6;
	
	public static ArrayList<Room> getRoomList()
	{
		ArrayList<Room> room = new ArrayList<Room>();
		/*
		  suites:
			- 2 single bedroom suites (at €240 a night),
			- a 2-bedroom suite (at €320),
			- a 3-bedroom suite (at €400).
			rooms
			- 3 single rooms (at €120 a night),
			- 18 double bedrooms (at €160) - 12 with a king size bed and 6 with twin beds.,
		 */
		int id = 0;
		
		for(int i = 0; i < SUITE_1_BEDROOM; i++)
		{
			room.add(new Suit(id++, 1));
		}
		
		for(int i = 0; i < SUITE_2_BEDROOM; i++)
		{
			room.add(new Suit(id++, 2));
		}
		
		for(int i = 0; i < SUITE_3_BEDROOM; i++)
		{
			room.add(new Suit(id++, 3));
		}
		
		for(int i = 0; i < SINGLE_ROOM; i++)
		{
			room.add(new SingleRoom(id++));
		}
		
		for(int i = 0; i < DOUBLE_ROOM_KING_SIZE_BED; i++)
		{
			room.add(new DoubleRoom(id++, 1));
		}
		for(int i = 0; i < DOUBLE_ROOM_TWIN_BEDS; i++)
		{
			room.add(new DoubleRoom(id++, 2));
		}

		return room;
	}
}
