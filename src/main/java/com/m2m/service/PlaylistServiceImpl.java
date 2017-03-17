package com.m2m.service;

import java.util.Collection;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.m2m.model.Playlist;
import com.m2m.repo.PlaylistRepo;

@Service
public class PlaylistServiceImpl implements PlaylistServiceDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PlaylistRepo playrepo;

	@Override
	public Collection<Playlist> findAll() {

		return (Collection<Playlist>) playrepo.findAll();
	}

	@Override
	public Playlist findOne(Long id) {
		return playrepo.findOne(id);
	}

	@Override
	public Playlist createOne(Playlist playlist) {

		return playrepo.save(playlist);
	}

	@Override
	public Playlist update(Playlist playlist) {

		Playlist playlistToUpdate = findOne(playlist.getPlaylistId());
		if (playlistToUpdate == null) {

			// Cannot update Playlist that hasn't been persisted
			logger.error(
					"Attempted to update a Playlist, but the entity does not exist.");
			throw new NoResultException("Requested entity not found.");
		}

		playlistToUpdate.setName(playlist.getName());
		Playlist updatedplaylist = playrepo.save(playlistToUpdate);

		return updatedplaylist;
	}

	@Override
	public void delete(Long id) {

		playrepo.delete(id);
	}

}
