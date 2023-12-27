package com.sesac.db_access_write.member.service;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sesac.db_access_write.common.dto.ResDto;
import com.sesac.db_access_write.kafka.KafkaProducerService;
import com.sesac.db_access_write.member.dto.MemberModifyInfo;
import com.sesac.db_access_write.member.dto.MemberSignUpInfo;
import com.sesac.db_access_write.member.entity.Member;
import com.sesac.db_access_write.member.entity.MemberRole;
import com.sesac.db_access_write.member.persistence.MemberRepository;
import com.sesac.db_access_write.member.serviceUtil.MemberServiceValidating;
import com.sesac.db_access_write.member.serviceUtil.MemberServiceMakeResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	private final MemberRepository memberRepository;

	private final KafkaProducerService kafkaProducerService;

	private final MemberServiceValidating validating;
	private final MemberServiceMakeResult makeResult;

	@Override
	public ResDto isDuplicatedEmail(String email) {
		try {
			if (validating.isDuplicatedValCnt(
				memberRepository.countByEmail(email)
			)){
				return makeResult.makeDuplicateEmailResult();
			} else {
				return makeResult.makeSuccessResultNoData();
			}
		} catch (Exception e){
			return makeResult.makeInternalServerErrorResult(e);
		}
	}

	@Override
	public ResDto isDuplicatedPhoneNumber(String phoneNumber) {
		try {
			if (validating.isDuplicatedValCnt(
				memberRepository.countByPhoneNumber(phoneNumber)
			)){
				return makeResult.makeDuplicatedPhoneNumResult();
			} else {
				return makeResult.makeSuccessResultNoData();
			}
		} catch (Exception e){
			return makeResult.makeInternalServerErrorResult(e);
		}
	}

	@Override
	public ResDto isDuplicatedNickname(String nickname) {
		try {
			if (validating.isDuplicatedValCnt(
				memberRepository.countByNickname(nickname)
			)){
				return makeResult.makeDuplicatedNicknameResult();
			} else {
				return makeResult.makeSuccessResultNoData();
			}
		} catch (Exception e){
			return makeResult.makeInternalServerErrorResult(e);
		}
	}

	@Override
	@Transactional
	public ResDto createMember(MemberSignUpInfo memberSignUpInfo) {
		try {
			if (validating.isDuplicatedValCnt(
				memberRepository.countByEmail(memberSignUpInfo.getEmail())
			)){
				return makeResult.makeDuplicateEmailResult();
			}

			if (validating.isDuplicatedValCnt(
				memberRepository.countByPhoneNumber(memberSignUpInfo.getPhoneNumber())
			)){
				return makeResult.makeDuplicatedPhoneNumResult();
			}

			if (validating.isDuplicatedValCnt(
				memberRepository.countByNickname(memberSignUpInfo.getNickname())
			)){
				return makeResult.makeDuplicatedNicknameResult();
			}

			Member member = Member.builder()
				.email(memberSignUpInfo.getEmail())
				.phoneNumber(memberSignUpInfo.getPhoneNumber())
				.nickname(memberSignUpInfo.getNickname())
				.password(validating.encodingPwd(memberSignUpInfo.getPassword()))
				.memberRole(Collections.singleton(MemberRole.USER_1))
				.build();

			memberRepository.save(member);

			Member savedMember = memberRepository.findByEmailAndDeletedAtIsNull(memberSignUpInfo.getEmail());

			if (!kafkaProducerService.sendCreateMemberMsg(
				kafkaProducerService.getMemberToKafkaCreateMemberMap(savedMember)
			)) {
				return makeResult.makeSendingToKafkaFailedResult();
			}

			return makeResult.makeSuccessResultNoData();

		} catch (Exception e){
			return makeResult.makeInternalServerErrorResult(e);
		}
	}

	/* 회원 정보 수정 */
	@Override
	@Transactional
	public ResDto modifyMember(String email, MemberModifyInfo memberModifyInfo) {
		try {
			Member existMember = memberRepository.findByEmailAndDeletedAtIsNull(email);

			if (validating.isChangeInfoDuplicated(
				existMember.getPhoneNumber(),
				memberModifyInfo.getPhoneNumber(),
				validating.isDuplicatedValCnt(
					memberRepository.countByPhoneNumber(memberModifyInfo.getPhoneNumber()))
			)){
				return makeResult.makeDuplicatedPhoneNumResult();
			}
			if (validating.isChangeInfoDuplicated(
					existMember.getNickname(),
					memberModifyInfo.getNickname(),
					validating.isDuplicatedValCnt(
						memberRepository.countByNickname(memberModifyInfo.getNickname()))
			)){
				return makeResult.makeDuplicatedNicknameResult();
			}

			existMember.changeNickname(memberModifyInfo.getNickname());
			existMember.changePhoneNum(memberModifyInfo.getPhoneNumber());

			memberRepository.save(existMember);
			Member savedMember = memberRepository.findByEmailAndDeletedAtIsNull(email);

			if (!kafkaProducerService.sendUpdateMemberMsg(
				kafkaProducerService.getMemberToKafkaUpdateMemberMap(savedMember)
			)){
				return makeResult.makeSendingToKafkaFailedResult();
			}

			return makeResult.makeSuccessResultNoData();
		}catch (Exception e){
			return makeResult.makeInternalServerErrorResult(e);
		}
	}

	/* 회원 정보 삭제 */
	@Override
	@Transactional
	public ResDto deleteMember(String email) {
		try {
			Member deletingMember = memberRepository.findByEmailAndDeletedAtIsNull(email);
			log.info(deletingMember);
			deletingMember.setDeletedAt(LocalDateTime.now());
			memberRepository.save(deletingMember);

			if(!kafkaProducerService.sendDeletedMemberMsg(
				kafkaProducerService.getMemberToKafkaDeleteMemberMap(deletingMember)))
			{
				return makeResult.makeSendingToKafkaFailedResult();
			}

			return makeResult.makeSuccessResultNoData();

		}catch (Exception e){
			return makeResult.makeInternalServerErrorResult(e);
		}
	}
}
