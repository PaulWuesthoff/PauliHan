package htwb.ai.PauliHan.services;

import htwb.ai.PauliHan.model.InMemoryDatabase;
import htwb.ai.PauliHan.model.Song;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class XMLWriter {


    public boolean writeSongsToXML(InMemoryDatabase inMemoryDatabase, PrintWriter out){
        try {
            JAXBContext context = JAXBContext.newInstance(Song.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            List<Song> song = inMemoryDatabase.getSongs();
            marshaller.marshal(song, out);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }

    }

}
