package com.m2m.Serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.m2m.model.Playlist;
import com.m2m.model.Song;

public class PlaylistSerializer extends StdSerializer<Playlist> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PlaylistSerializer() {
		this(null);
	}
	
	public PlaylistSerializer(Class<Playlist> t) {
		super(t);
	}

	@Override
	public void serialize(Playlist playlist, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		jgen.writeStartObject();
		jgen.writeNumberField("Id", playlist.getPlaylistId());
		jgen.writeStringField("Name", playlist.getName());
		jgen.writeNumberField("SongCount", playlist.getSonglist().size());

		jgen.writeFieldName("Songs");  

		jgen.writeStartArray();
		for (Song song : playlist.getSonglist() ) {
			jgen.writeStartObject();
			jgen.writeNumberField("Id", song.getSongIdentifier());
			jgen.writeStringField("Name", song.getName());
			jgen.writeEndObject();
		}
		jgen.writeEndArray();
		jgen.writeEndObject();
	}
}
