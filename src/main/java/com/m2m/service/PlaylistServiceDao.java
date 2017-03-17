package com.m2m.service;

import java.util.Collection;

import com.m2m.model.Playlist;

public interface PlaylistServiceDao {

	Collection <Playlist> findAll();
	
    Playlist findOne(Long id);
    
    Playlist createOne(Playlist playlist);
    
    Playlist update(Playlist playlist);

    void delete(Long id);

	
}
