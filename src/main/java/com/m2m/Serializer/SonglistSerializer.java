package com.m2m.Serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.m2m.model.Playlist;
import com.m2m.model.Song;

public class SonglistSerializer extends StdSerializer<Song> {
	
	public SonglistSerializer() {
		this(null);
	}
	
	protected SonglistSerializer(Class<Song> t) {
		super(t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Song song, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeNumberField("Id", song.getSongIdentifier());
		jgen.writeStringField("Name", song.getName());
		jgen.writeNumberField("PlaylistCount", song.getPlaylists().size());

		jgen.writeFieldName("Playlist");  

		jgen.writeStartArray();
		for (Playlist plist : song.getPlaylists() ) {
			jgen.writeStartObject();
			jgen.writeNumberField("Id", plist.getPlaylistId());
			jgen.writeStringField("Name", plist.getName());
			jgen.writeEndObject();
		}
		jgen.writeEndArray();
		jgen.writeEndObject();		
	}

}
