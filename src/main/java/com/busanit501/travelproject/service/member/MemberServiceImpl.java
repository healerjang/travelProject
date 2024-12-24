package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.dto.member.LoginDTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.RegisterDTO;
import com.busanit501.travelproject.dto.member.UpdateDTO;
import com.busanit501.travelproject.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final List<String> adminList;

    @Override
    public Map<MemberFields, Boolean> duplicateCheck(RegisterDTO registerDTO) {
        if (registerDTO.isData()) return Map.of(MemberFields.ID, !memberRepository.existsByMemberID(registerDTO.getMemberID()),
                                                MemberFields.NAME, !memberRepository.existsByMemberName(registerDTO.getMemberName()),
                                                MemberFields.EMAIL, !memberRepository.existsByMemberEmail(registerDTO.getMemberEmail()),
                                                MemberFields.PHONE, !memberRepository.existsByMemberPhone(registerDTO.getMemberPhone()));
        return Map.of();
    }

    @Override
    public boolean registerMember(RegisterDTO registerDTO) {
        Map<MemberFields, Boolean> duplicateCheckMap = duplicateCheck(registerDTO);
        if (duplicateCheckMap.values().stream().allMatch(Boolean::booleanValue)) {
            Member member = modelMapper.map(registerDTO, Member.class);
            memberRepository.save(member);
            return true;
        }
        return false;
    }

    @Override
    public MemberDTO getMember(long memberNo) {
        return modelMapper.map(memberRepository.findById(memberNo).orElseThrow(), MemberDTO.class);
    }

    @Override
    public void updateMember(UpdateDTO updateDTO) {
        Member member = memberRepository.findById(updateDTO.getMemberNo()).orElseThrow();
        member.updateMemberData(updateDTO);
        memberRepository.save(member);
    }

    @Override
    public boolean deleteMember(long memberNo) {
        if (!memberRepository.existsByMemberNo(memberNo)) return false;
        memberRepository.deleteById(memberNo);
        return true;
    }

    @Override
    public ResponseLogin login(LoginDTO loginDTO) {
        if (memberRepository.existsByMemberIDAndMemberPassword(loginDTO.getMemberID(), loginDTO.getMemberPassword())) {
            if (adminList.contains(loginDTO.getMemberID())) return ResponseLogin.ADMIN;
            else return ResponseLogin.USER;
        }
        return ResponseLogin.FALSE;
    }

    @Override
    public boolean duplicateID(String id) {
        return !memberRepository.existsByMemberID(id);
    }

    @Override
    public boolean duplicateName(String name) {
        return !memberRepository.existsByMemberName(name);
    }

    @Override
    public boolean duplicateEmail(String email) {
        return !memberRepository.existsByMemberEmail(email);
    }

    @Override
    public boolean duplicatePhone(String phone) {
        return !memberRepository.existsByMemberPhone(phone);
    }


}
