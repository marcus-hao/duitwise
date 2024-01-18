package cat201.group37.springstudentexpensetrackerms.service;

import cat201.group37.springstudentexpensetrackerms.entity.Tabung;
import cat201.group37.springstudentexpensetrackerms.entity.User;
import cat201.group37.springstudentexpensetrackerms.repository.TabungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabungService {
    @Autowired
    private TabungRepository tabungRepository;

    public List<Tabung> getAllTabung() {
        return tabungRepository.findAll();
    }

    public List<Tabung> getTabungByUser(User user) {
        return tabungRepository.findByUser(user);
    }

    public Tabung getTabungById(Long id) {
        return tabungRepository.findById(id).orElse(null);
    }

    public List<Tabung> getTabungByType(User user, String type) {
        return tabungRepository.findByUserAndType(user, type);
    }

    public void saveTabung(Tabung tabung) {
        tabungRepository.save(tabung);
    }

    public void updateTabung(Tabung tabung) {
        tabungRepository.save(tabung);
    }

    public void deleteTabung(Long id) {
        tabungRepository.deleteById(id);
    }
}
