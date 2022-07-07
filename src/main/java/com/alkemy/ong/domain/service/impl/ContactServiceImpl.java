package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.domain.service.IContactService;
import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.repository.ContactRepository;
import com.alkemy.ong.mapper.ContactMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper mapper;

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        Contact entity = mapper.contactDTO2Entity(contactDTO);
        Contact entitySaved = contactRepository.save(entity);
        return mapper.contactEntity2DTO(entitySaved);
    }

    @Override
    public List<ContactDTO> getAll() {
        List<Contact> listEntity = contactRepository.findAll();
        if (listEntity.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        List<ContactDTO> listDto = mapper.entityListToDtoList(listEntity);
        return listDto;
    }
}
