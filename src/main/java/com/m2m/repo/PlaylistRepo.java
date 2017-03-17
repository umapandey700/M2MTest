package com.m2m.repo;

import org.springframework.data.repository.CrudRepository;

import com.m2m.model.Playlist;

public interface PlaylistRepo extends CrudRepository<Playlist, Long> {	

}
