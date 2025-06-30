package cz.dan.notes.domain.note.document;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Set;
import java.util.UUID;

@Document(indexName = "note")
@RequiredArgsConstructor
@Builder
@Data
@Setting(settingPath = "/settings/note.json")
public class Note {

    @Default
    private final String id = UUID.randomUUID().toString();

    @Field(type = FieldType.Text)
    private final String text;

    @Field(type = FieldType.Keyword)
    private final String source;

    @Field(type = FieldType.Keyword)
    private final String language;

    @Field(type = FieldType.Keyword)
    private final String author;

    @Field(type = FieldType.Keyword)
    private final Set<String> tags;

    @Field(type = FieldType.Long)
    private final Set<Long> personIds;

    @Field(type = FieldType.Long)
    private final Set<Long> teamIds;

    @Field(type = FieldType.Long)
    private final Set<Long> gameIds;

}
