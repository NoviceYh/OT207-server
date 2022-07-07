package com.alkemy.ong.domain.service;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMemberService {

    @Transactional
    MemberDTO addMember(MemberDTO memberDto);


    MemberDTO updateById(MemberDTO dto, Long id);

    void deleteById(Long id);

    List<MemberDTO> getAll();

    @Transactional(readOnly = true)
    PageDTO<MemberDTO> getAllMembersPageable(Integer page);
}
