package htwb.ai.PauliHan.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.List;

public class XMLReader {

    public List<Song> readXMLToSongs(String filename)    throws JAXBException, FileNotFoundException, IOException {
        JAXBContext context = JAXBContext.newInstance(SongsWrapper.class, Song.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            List<Song> songs = unmarshal(unmarshaller, Song.class, filename);
            return songs;
        }

    }

    private  List<Song> unmarshal(Unmarshaller unmarshaller, Class<Song> clazz, String filename)
            throws JAXBException {
        StreamSource xml = new StreamSource(filename);
        // unmarshal( javax.xml.transform.Source source, Class<T> declaredType )
        SongsWrapper wrapper = (SongsWrapper) unmarshaller.unmarshal(xml, SongsWrapper.class).getValue();
        return wrapper.getSongs();
    }
}
