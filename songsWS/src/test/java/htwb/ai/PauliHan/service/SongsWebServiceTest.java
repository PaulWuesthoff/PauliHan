//package htwb.ai.PauliHan.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.glassfish.jersey.test.JerseyTest;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import htwb.ai.PauliHan.config.ResourceConfigTest;
//import htwb.ai.PauliHan.model.Song;
//
//
//class SongsWebServiceTest extends JerseyTest{
//
//	@Override
//	protected ResourceConfigTest configure() {
//		return new ResourceConfigTest();
//	}
//	
//	@BeforeEach
//	@Override
//	public void setUp() throws Exception{
//		super.setUp();
//	}
//	
//	@AfterEach
//    @Override
//    public void tearDown() throws Exception {
//        super.tearDown();
//    }
//	
//	@Test
//	public void getSongJsonTest() {
//		Response response = target("/songs/1").request(MediaType.APPLICATION_JSON).get();
//		Song song = response.readEntity(Song.class);
////		System.out.println(song.toString());
//		assertEquals(1,1);
//	}
//
//}
//
//
//
//
