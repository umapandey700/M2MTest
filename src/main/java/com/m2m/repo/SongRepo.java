package com.m2m.repo;

import org.springframework.data.repository.CrudRepository;

import com.m2m.model.Song;

public interface SongRepo extends CrudRepository<Song, Long> {

}
