package cz.dan.notes.domain.note.document;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends ElasticsearchRepository<Note, String> {
}
