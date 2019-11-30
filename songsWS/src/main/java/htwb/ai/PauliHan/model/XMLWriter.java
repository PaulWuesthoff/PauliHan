package htwb.ai.PauliHan.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.PrintWriter;
import java.util.Map;

public class XMLWriter {


    public boolean writeSongsToXML(Database database, PrintWriter out){
        try {
            JAXBContext context = JAXBContext.newInstance(Song.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Map<Integer,Song> song = database.getSongMap();
            marshaller.marshal(song, out);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }

    }

}
