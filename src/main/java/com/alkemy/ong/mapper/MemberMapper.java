package com.alkemy.ong.mapper;

import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.dto.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {

    public Member memberDtoToMemberEntity(MemberDTO memberDto) {
        Member memberEntity = new Member();
        memberEntity.setId(memberDto.getId());
        memberEntity.setName(memberDto.getName());
        memberEntity.setImage(memberDto.getImage());
        memberEntity.setFacebookUrl(memberDto.getFacebookUrl());
        memberEntity.setInstragramUrl(memberDto.getInstagramUrl());
        memberEntity.setLinkedinUrl(memberDto.getLinkedinUrl());
        memberEntity.setDescription(memberDto.getDescription());
        setAttributerMemberDto2Entity(memberDto,memberEntity);
        return memberEntity;
    }
    public MemberDTO memberEntityToMemberDto(Member savedEntity) {
        MemberDTO memberDto = new MemberDTO();
        memberDto.setId(savedEntity.getId());
        memberDto.setName(savedEntity.getName());
        memberDto.setImage(savedEntity.getImage());
        memberDto.setFacebookUrl(savedEntity.getFacebookUrl());
        memberDto.setInstagramUrl(savedEntity.getInstragramUrl());
        memberDto.setLinkedinUrl(savedEntity.getLinkedinUrl());
        memberDto.setDescription(savedEntity.getDescription());
        return memberDto;
    }

    public List<MemberDTO> memberEntityListToMemberDtoList(List<Member> memberList) {
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member entity : memberList) {
            memberDTOList.add(this.memberEntityToMemberDto(entity));
        }
        return memberDTOList;
    }


    public void memberDto2EntityWithId(Member memberEntity, MemberDTO dto) {
        setAttributerMemberDto2Entity(dto,memberEntity);
    }

    private void setAttributerMemberDto2Entity(MemberDTO memberDto, Member memberEntity) {
        memberEntity.setName(memberDto.getName());
        memberEntity.setFacebookUrl(memberDto.getFacebookUrl());
        memberEntity.setInstragramUrl(memberDto.getInstagramUrl());
        memberEntity.setLinkedinUrl(memberDto.getLinkedinUrl());
        memberEntity.setDescription(memberDto.getDescription());
        memberEntity.setImage(memberDto.getImage());
    }

    public List<MemberDTO> membersEntityPage2Dto(Page<Member> members) {
        List<MemberDTO> membersDTO = new ArrayList<>();
        members.getContent().forEach(entity -> membersDTO.add (this.memberEntityToMemberDto(entity)));
        return membersDTO;
    }
}
