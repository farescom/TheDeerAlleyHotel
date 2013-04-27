package system.domain.mediator;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import system.domain.model.Booking;

public interface ModelInterface
{
	public void Book(Booking booking) throws TransformerException, ParserConfigurationException, IOException;
}
