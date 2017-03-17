package com.m2m.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.ParameterValueProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m2m.model.Playlist;
import com.m2m.model.Song;
import com.m2m.service.PlaylistServiceDao;
import com.m2m.service.SongServiceDao;


@RestController
public class PlaylistController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	 @Autowired
	 PlaylistServiceDao playlistService;
	 
		 @RequestMapping(
		            value = "/api/playlists",
		            method = RequestMethod.GET,
		            produces = MediaType.APPLICATION_JSON_VALUE)
		     public ResponseEntity<Collection<Playlist>> getPlaylists() {

		        Collection<Playlist> playlists = playlistService.findAll();

		        return new ResponseEntity<Collection<Playlist>>(playlists, HttpStatus.OK);
		        
		    }
		 @RequestMapping(
		            value = "/api/playlists/{id}",
		            method = RequestMethod.GET,
		            consumes = MediaType.APPLICATION_JSON_VALUE,
		            produces = MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<Playlist> getSongById(@PathVariable ("id") Long id) {

			 logger.info("> getSong id:{}", id);
				
			 Playlist savedSong = playlistService.findOne(id) ;
				if (savedSong == null) {
					new ResponseEntity<Playlist>(HttpStatus.NOT_FOUND);
				}
				logger.info("< getSong id:{}", id);

				return new ResponseEntity<Playlist>(savedSong, HttpStatus.OK);
		        
		    }
		 
		 
		 
		 @RequestMapping(
		            value = "/api/playlists",
		            method = RequestMethod.POST,
		            consumes = MediaType.APPLICATION_JSON_VALUE,
		            produces = MediaType.APPLICATION_JSON_VALUE)
		   @ResponseBody public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {

		        Playlist newList = playlistService.createOne(playlist);

		        return new ResponseEntity<Playlist>(newList, HttpStatus.OK);
		        
		    }
		 
		 @RequestMapping(
				 value = "/api/playlists/{id}",
				 method = RequestMethod.PUT,
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
		 public ResponseEntity<Playlist> updatePlaylist(@RequestBody Playlist playlist) {

		        logger.info("> putPlaylist id:{}", playlist.getPlaylistId());
				
		        Playlist savedPlaylist = playlistService.findOne(playlist.getPlaylistId()) ;
				if (savedPlaylist == null) {
					new ResponseEntity<Playlist>(HttpStatus.NOT_FOUND);
				}
		        Playlist newList = playlistService.update(playlist);

				logger.info("< putPlaylist id:{}", newList.getPlaylistId());

		        return new ResponseEntity<Playlist>(newList, HttpStatus.OK);
		        
		    }
		
		 @RequestMapping(
		            value = "/api/playlists/{id}",
		            method = RequestMethod.DELETE)
		    public ResponseEntity<Playlist> deletePlaylist(
		            @PathVariable("id") Long id) {
		        logger.info("> deletePlaylist id:{}", id);

		        playlistService.delete(id);

		        logger.info("< deletePlaylist id:{}", id);
		        return new ResponseEntity<Playlist>(HttpStatus.NO_CONTENT);
		    }
		 
	
}
