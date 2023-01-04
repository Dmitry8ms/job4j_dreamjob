package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
public class MemoryCandidateRepository implements CandidateRepository {
    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();
    public MemoryCandidateRepository() {
        save(new Candidate(0, "Intern Java Developer", "Description 1", LocalDateTime.now()));
        save(new Candidate(0, "Junior Java Developer", "Description 2", LocalDateTime.now()));
        save(new Candidate(0, "Junior+ Java Developer", "Description 3", LocalDateTime.now()));
        save(new Candidate(0, "Middle Java Developer", "Description 4", LocalDateTime.now()));
        save(new Candidate(0, "Middle+ Java Developer", "Description 5", LocalDateTime.now()));
        save(new Candidate(0, "Senior Java Developer", "Description 6", LocalDateTime.now()));
    }
    public static CandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldCandidate) ->
        new Candidate(id, candidate.getName(), candidate.getDescription(),
                candidate.getCreationDate())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
