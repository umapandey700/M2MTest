package com.m2m.service;

import java.util.Collection;

import com.m2m.model.Song;

public interface SongServiceDao {

	Collection <Song> findAll();

	Song create(Song song);

	Song findOne(Long id);

	Song update(Song song);

	void delete(Long id);
		
}
