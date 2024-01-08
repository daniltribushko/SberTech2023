package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.event.EventTypeAlreadyExistException;
import com.example.sbertech2023.exceptions.event.EventTypeByIdNotFoundException;
import com.example.sbertech2023.models.entities.EventType;
import com.example.sbertech2023.repositories.EventTypeRepository;
import com.example.sbertech2023.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для работы с типами мероприятий
 */
@Service
public class EventTypeServiceImp implements EventTypeService {
    private final EventTypeRepository eventTypeRepository;

    @Autowired
    public EventTypeServiceImp(EventTypeRepository eventTypeRepository){
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public void saveEventType(String name) {
        EventType eventType = eventTypeRepository.findByName(name).orElse(null);
        if (eventType != null){
            throw new EventTypeAlreadyExistException(name);
        }
        eventTypeRepository.save(new EventType(name));
    }

    @Override
    public void deleteEventType(Integer id) {
        EventType eventType = eventTypeRepository.findById(id)
                .orElseThrow(() -> new EventTypeByIdNotFoundException(id));
        eventTypeRepository.delete(eventType);
    }
}
