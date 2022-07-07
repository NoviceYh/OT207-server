package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.domain.repository.MemberRepository;
import com.alkemy.ong.domain.service.IMemberService;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.PageDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;

    private static final String ID_NOT_FOUND = "Id not found: ";
    private static final String URI = Url.URL + Url.NEWS_URI + Url.PAGE_URI;

    @Override
    @Transactional
    public MemberDTO addMember(MemberDTO memberDto) {

        Member MemberEntity = memberMapper.memberDtoToMemberEntity(memberDto);
        Member savedEntity = memberRepository.save(MemberEntity);
        return memberMapper.memberEntityToMemberDto(savedEntity);

    }


    @Override
    @Transactional
    public MemberDTO updateById(MemberDTO dto, Long id) {
        Member memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID: " + id));
        memberMapper.memberDto2EntityWithId(memberEntity, dto);
        Member entitySaved = memberRepository.save(memberEntity);
        return memberMapper.memberEntityToMemberDto(entitySaved);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException(ID_NOT_FOUND + id));
        member.setDeleted(true);
        memberRepository.save(member);

    }

    @Override
    public List<MemberDTO> getAll() {
        List<Member> memberList = memberRepository.findAll();
        if (memberList.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        return memberMapper.memberEntityListToMemberDtoList(memberList);
    }


    @Transactional(readOnly = true)
    @Override
    public PageDTO<MemberDTO> getAllMembersPageable(Integer page) {
        PageDTO<MemberDTO> memberDTOPageDTO = new PageDTO<>();
        Page<Member> members = this.memberRepository.findAll(PageRequest.of(page - 1, Url.MAX_PAGE, Sort.by("name")));
        return Url.pagination(memberDTOPageDTO, members, page, this.memberMapper.membersEntityPage2Dto(members), URI);
    }

}
