/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song;

public class M2OUnidirectionalWithJoinWritableApiTest extends SDKWritableBaseTest{
	private static Logger log = Logger.getLogger(M2OUnidirectionalWithJoinWritableApiTest.class);
	public static String getTestCaseName() {
		return "Many to One Unidirectional With Join WritableApi Test Case";
	}
	
	public void testSaveObjectSong(){
		log.debug("\n--------testSaveObjectChef()---------------\n\n");
		Song song=new Song();
		song.setTitle("song");
		
		save(song);
		
		Song result=(Song)getObject(Song.class, song.getId());
		Assert.assertEquals(song.getTitle(), result.getTitle());
	}
	
	public void testSaveObjectSongWithMany2OneCascadeSaveUpdate(){
		log.debug("\n\n--------testSaveObjectSongWithMany2OneObjectAlbum()---------------\n\n");
		Song song=new Song();
		song.setTitle("song");
		Album album=new Album();
		album.setTitle("title");
		song.setAlbum(album);
		
		save(song);
		
		Song result=(Song)getObjectAndLazyObject(Song.class, song.getId(),"album");
		Assert.assertEquals(song.getTitle(), result.getTitle());
		Assert.assertEquals(album.getTitle(), result.getAlbum().getTitle());
	}
	
	public void testUpdateObjectSongWithMany2OneAlbum(){
		log.debug("\n\n--------testUpdateObjectSongWithMany2OneObjectAlbum()---------------\n\n");
		Song song=new Song();
		song.setTitle("song");
		Album album=new Album();
		album.setTitle("title");
		song.setAlbum(album);
		
		save(song);
		
		Song updateSong=(Song)getObjectAndLazyObject(Song.class, song.getId(),"album");
		Album updateAlbum=updateSong.getAlbum();
		updateAlbum.setTitle("updatedTitle");
		updateSong.setTitle("updateSong");
		
		update(updateSong);
		
		Song resultSong=(Song)getObjectAndLazyObject(Song.class, song.getId(),"album");		
		Assert.assertEquals(updateSong.getTitle(), resultSong.getTitle());
		Assert.assertEquals(updateAlbum.getTitle(), resultSong.getAlbum().getTitle());
	}
	
	public void testDeleteObjectSongWithMany2OneCascadeSaveUpdate(){
		log.debug("\n\n--------testDeleteObjectSongWithMany2OneCascadeSaveUpdate()---------------\n\n");
		Song song = new Song();
		song.setTitle("song");
		Album album = new Album();
		album.setTitle("title");
		song.setAlbum(album);

		save(song);
		album=song.getAlbum();

		delete(song);

		Song resultSong = (Song) getObject(Song.class, song.getId());
		Album resultAlbum = (Album) getObject(Album.class, album.getId());
		Assert.assertNull("Song must be deleted ",resultSong);
		Assert.assertNotNull("Album must not be deleted ",resultAlbum);
	}
}
