package com.m2m.service;

import java.util.Collection;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2m.model.Playlist;
import com.m2m.model.Song;
import com.m2m.repo.SongRepo;

@Service
public class SongServiceImpl implements SongServiceDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SongRepo songRepo;

	@Override
	public Collection<Song> findAll() {
		
		Collection <Song> allsongs = (Collection<Song>) songRepo.findAll();
		return allsongs;
	}

	@Override
	public Song create(Song song) {
		Song newSong = songRepo.save(song);
		return newSong;
	}

	@Override
	public Song findOne(Long id) {
		return songRepo.findOne(id);
	}

	@Override
	public Song update(Song song) {
		Song songToUpdate = findOne(song.getSongIdentifier());
		if (songToUpdate == null) {

			// Cannot update Song that hasn't been persisted
			logger.error(
					"Attempted to update a Playlist, but the entity does not exist.");
			throw new NoResultException("Requested entity not found.");
		}

		songToUpdate.setName(song.getName());
		Song updatedsong = songRepo.save(songToUpdate);

		return updatedsong;
	}

	@Override
	public void delete(Long id) {
		songRepo.delete(id);

	}

}
