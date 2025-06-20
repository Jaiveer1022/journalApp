package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEnrtyRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEnrtyRepository journalEnrtyRepository;

    @Autowired
    private UserService userService;

    public  void saveEntry(JournalEntry entry) {
        journalEnrtyRepository.save(entry);
    }

    @Transactional
    public  void saveEntry(JournalEntry entry, String userName) {

      try {
          User user = userService.findByUserName(userName);
          entry.setDate(LocalDate.now());
          JournalEntry saved = journalEnrtyRepository.save(entry);
          user.getJournalEntries().add(saved);
          userService.saveEntry(user);

      }catch (Exception e) {
          System.out.println("Error in saving journal entry");
      }
    }

    public List<JournalEntry> getAll(){
        return journalEnrtyRepository.findAll();
    }

    public Optional<JournalEntry> findEntryById(ObjectId id) {
        return journalEnrtyRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEnrtyRepository.deleteById(id);
    }

}
