import java.util.*;
import java.util.stream.Collectors;

public class StudentSurnameStorage {
    private TreeMap<String, Set<Long>> surnamesTreeMap = new TreeMap<>();


    public void studentCreated(Long id, String surname) {
        Set<Long> existingIds = surnamesTreeMap.getOrDefault(surname, new HashSet<>());
        existingIds.add(id);
        surnamesTreeMap.put(surname, existingIds);
    }

    public void studentDeleted(Long id, String surname) {
        surnamesTreeMap.get(surname).remove(id);
    }

    public void studentUpdated(Long id, String oldSurname, String newSurname) {
        studentDeleted(id, oldSurname);
        studentCreated(id, newSurname);

    }


    /**
     * Данный метод возвращяет уникальные идонтификаторы студентов в диапозоне переданных фамилий
     *
     * @return set
     */
    public Set<Long> getStudent(String firstSurname, String lastSurname) {
        Set<Long> res = surnamesTreeMap.subMap(firstSurname, true, lastSurname, true)
                .values()
                .stream()
                .flatMap(longs -> longs.stream())
                .collect(Collectors.toSet());
        return res;
    }

    public Set<Long> getStudent() {
        Set<Long> res = surnamesTreeMap.entrySet()
                .stream()
                .flatMap(longs -> longs.getValue().stream())
                .collect(Collectors.toSet());
        return res;
    }

    public Set<Long> getStudent(String surname) {

        Set<Long> res = surnamesTreeMap.tailMap(surname, true)
                .values()
                .stream()
                .limit(1)
                .flatMap(longs -> longs.stream())
                .collect(Collectors.toSet());
        return res;

    }


}
