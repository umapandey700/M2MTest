package com.m2m.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.m2m.Serializer.SonglistSerializer;


@Entity
@Table(name="M2MSONG")
@JsonSerialize(using=SonglistSerializer.class)
public class Song implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Song() {
	}

	public Song(String name){
        this.name = name;
    }
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SONG_ID")
	private Long songIdentifier;
	
	@Column(name="NAME")
	private String name;

	@ManyToMany	
	@JoinTable(name = "PLAYLIST_SONG", 
	joinColumns = @JoinColumn(name = "SONG_ID", referencedColumnName = "SONG_ID"), 
	inverseJoinColumns = @JoinColumn(name = "PLAYLIST_ID", referencedColumnName = "PLAYLIST_ID"))
	private List<Playlist> playlists = new ArrayList<Playlist>();

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Long getSongIdentifier() {
		return songIdentifier;
	}

	public void setSongIdentifier(Long songIdentifier) {
		this.songIdentifier = songIdentifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
