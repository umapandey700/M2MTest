package com.m2m.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2m.model.Playlist;
import com.m2m.model.Song;
import com.m2m.service.PlaylistServiceDao;
import com.m2m.service.SongServiceDao;


@RestController
@Transactional
public class SongController extends BaseController {

	@Autowired
	SongServiceDao songService;
	
	@Autowired
	PlaylistServiceDao playlistService;
	
	 @RequestMapping(
	            value = "/api/songs",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Collection<Song>> getSongs() {

	        Collection<Song> songs = songService.findAll();	        
	        for (Song song : songs) {
	        		List<Playlist> plists = song.getPlaylists();
	        		for(Playlist p : plists) {
	   	   			 	logger.info("> getSong playlist.name:{}", p.getName());
	        		}	        		
	        }
	        
	        return new ResponseEntity<Collection<Song>>(songs, HttpStatus.OK);
	        
	    }
	  
	  
	 @RequestMapping(
	            value = "/api/songs",
	            method = RequestMethod.POST,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Song> createSong(@RequestBody Song song) {

	        Song newSong = songService.create(song);
	        
	        return new ResponseEntity<Song>(newSong, HttpStatus.OK);
	        
	    }
	 
	 @RequestMapping(
	            value = "/api/songs/{id}",
	            method = RequestMethod.GET,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Song> getSongById(@PathVariable ("id") Long id) {

		 logger.info("> getSong id:{}", id);
			
			Song savedSong = songService.findOne(id) ;
			if (savedSong == null) {
				new ResponseEntity<Playlist>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Song>(savedSong, HttpStatus.OK);
	    }
	 
	 @RequestMapping(
			 value = "/api/songs/{id}",
			 method = RequestMethod.PUT,
			 consumes = MediaType.APPLICATION_JSON_VALUE,
			 produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Song> updateSong(@RequestBody Song song) {

	        logger.info("> updateSong id:{}", song.getSongIdentifier());
			
	        Song savedSong = songService.findOne(song.getSongIdentifier()) ;
			if (savedSong == null) {
				new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
			}
	        Song newSong = songService.update(song);

			logger.info("< updateSong id:{}", newSong.getSongIdentifier());

	        return new ResponseEntity<Song>(newSong, HttpStatus.OK);
	        
	    }
	
	 @RequestMapping(
	            value = "/api/songs/{id}",
	            method = RequestMethod.DELETE)
	    public ResponseEntity<Song> deleteSong(
	            @PathVariable("id") Long id) {
	        logger.info("> deleteSong id:{}", id);

	        songService.delete(id);

	        logger.info("< deleteSong id:{}", id);
	        return new ResponseEntity<Song>(HttpStatus.NO_CONTENT);
	    }
	 
	 @RequestMapping(
	            value = "/api/songs/{id}/addToPlaylist",
	            method = RequestMethod.POST,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Playlist> createSong(@RequestBody Song song, @PathVariable("id") Long id) {

		 Playlist playlist = playlistService.findOne(id);
		 playlist.getSonglist().add(song);
		 playlistService.update(playlist);
	        
	        return new ResponseEntity<Playlist>(playlist, HttpStatus.OK);
	        
	    }
	 
	 @RequestMapping(value = "/api/songs/{id}/playlist", method = RequestMethod.GET)
		public ResponseEntity<Collection<Playlist>> getPersonParties(@PathVariable long id) {
			Song savedSong = songService.findOne(id);

			if (savedSong != null) {
				return new ResponseEntity<>(savedSong.getPlaylists(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}

}
