package com.sesac.member_write_server.member.entity;

import java.util.Set;

import org.hibernate.annotations.SQLDelete;

import com.sesac.member_write_server.common.entity.BaseTimeEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"role"})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update member set deleted_at = CURRENT_TIMESTAMP where member_id = ?")
@Table(name = "member")
@Entity
public class Member extends BaseTimeEntity {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@Column(nullable = false, length = 100, unique = true)
	private String phoneNumber;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 10)
	private String nickname;

	@ElementCollection(fetch = FetchType.LAZY)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_id"))
	@Column(nullable = false)
	private Set<MemberRole> memberRole;

	@Builder
	public Member(String email, String phoneNumber, String password, String nickname, Set<MemberRole> memberRole)
	{
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nickname = nickname;
		this.password = password;
		this.memberRole = memberRole;
	}

	public void changePassword(String password){
		this.password = password;
	}

	public void changePhoneNum(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public void changeNickname(String nickname){
		this.nickname = nickname;
	}
}
