package com.m2m.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.m2m.Serializer.PlaylistSerializer;


@Entity
@Table(name="M2MPLAYLIST")
@JsonSerialize(using=PlaylistSerializer.class)
public class Playlist  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected  Playlist () {
	}
	
	public Playlist(String name){
        this.name = name;
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PLAYLIST_ID")
	private Long playlistId;
	
	
	public Long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}

	@Column(name="NAME")
	private String name;

	@ManyToMany(fetch=FetchType.EAGER ,cascade=CascadeType.ALL)
	@JoinTable(name = "PLAYLIST_SONG", 
	joinColumns = @JoinColumn(name = "PLAYLIST_ID", referencedColumnName = "PLAYLIST_ID"), 
	inverseJoinColumns = @JoinColumn(name = "SONG_ID", referencedColumnName = "SONG_ID"))
	private List<Song> songlist = new ArrayList<Song>(0);

	
	public List<Song> getSonglist() {
		return songlist;
	}

	public void setSonglist(List<Song> songlist) {
		this.songlist = songlist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", name=" + name + ", songlist=" + songlist + "]";
	}
	
}
