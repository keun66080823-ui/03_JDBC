package edu.kh.jdbc.homework.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 매개변수가 있는 생성자
@ToString
public class Student {
	private int stdNo;
	private String stdName;
	private int stdAge;
	private String stdMajor;
	private String enrollDate;
}
