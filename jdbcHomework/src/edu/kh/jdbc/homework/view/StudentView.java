package edu.kh.jdbc.homework.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.homework.model.dto.Student;
import edu.kh.jdbc.homework.model.service.StudentService;

public class StudentView {
	
	private StudentService service = new StudentService();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		int input = 0; // 메뉴 선택용 변수
		
		do {
			try {
				
				System.out.println("\n===== 학생 관리 프로그램 =====\n");
				System.out.println("1. 학생 등록(INSERT)");
				System.out.println("2. 전체 학생 조회(SELECT)");
				System.out.println("3. 학생 정보 수정(이름, 나이, 전공)(UPDATE)");
				System.out.println("4. STD_NO를 입력 받아 일치하는 학생 삭제(DELETE)");
				System.out.println("5. 학생 중 전공이 검색어와 같은 학생 조회 (SELECT)");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거
				
				switch (input) {
				case 1: insertStd(); break;
				case 2: selectAll(); break;
				case 3: updateImpo(); break;
				case 4: deleteStd(); break;
				case 5: selectMajor(); break;
				case 0: System.out.println("\n[프로그램 종료]\n"); break;
				default: System.out.println("\n[메뉴 번호만 입력하세요]\n");
				}
				
				System.out.println("\n-------------------------------------\n");
				
				
			} catch (InputMismatchException e) {
				System.out.println("\n***잘못 입력 하셨습니다***\n");
				
				input = -1; // 잘못 입력해서 while문이 멈추는 걸 방지하기 위해 메뉴에 없을 듯한 -1을 input값에 넣는 코드.
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자 제거
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while(input != 0);
	}

	/** 5. 학생 중 전공이 검색어와 같은 학생 조회 (SELECT)
	 * 
	 */
	private void selectMajor() throws Exception{
		System.out.println("====5. 학생 중 전공이 검색어와 같은 학생 조회====\n");
		
		System.out.print("찾을 전공을 검색하세요 : ");
		String major = sc.next();
		
		List<Student> stdMajor = service.selectMajor(major);
		
		if(stdMajor.isEmpty()) {
			System.out.println("해당 전공의 학생이 존재하지 않습니다");
			return;
		}
		
		for(Student std : stdMajor) {
			System.out.println(std);
		}
		
		
	}

	/** 4. STD_NO를 입력 받아 일치하는 학생 삭제(DELETE)
	 * 
	 */
	private void deleteStd() throws Exception{
		System.out.println("\n====4. STD_NO를 입력 받아 일치하는 학생 삭제====\n");
		
		System.out.print("삭제할 학생의 번호를 입력하세요 : ");
		int num = sc.nextInt();
		
		int stdNo = service.deleteStd(num);
		
		if (stdNo == 0) {
			System.out.println("해당 번호의 학생은 존재하지 않습니다.");
			return;
		}
		
		System.out.println("삭제되었습니다.");
		
	}

	/** 3. 학생 정보 수정(이름, 나이, 전공)(UPDATE)
	 * 
	 */
	private void updateImpo() throws Exception {
		System.out.println("\n====3. 학생 정보 수정(이름, 나이, 전공)====\n");
		
		System.out.print("수정할 학생의 이름을 입력하세요 : ");
		String stdName = sc.next();
		
		int stdNo = service.selectStdNo(stdName);
		
		if(stdNo == 0) {
			System.out.println("해당 이름의 학생은 존재하지 않습니다.");
			return;
		}
		
		System.out.print("해당 학생의 어떤 정보를 수정하시겠습니까? (1. 이름 / 2. 나이 / 3. 전공 : ");
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1 : // 이름
			System.out.print("수정할 이름 입력 : ");
			String name = sc.next();
			int result1 = service.updateName(name, stdNo);
			
			if(result1 > 0) System.out.println("수정 성공.");
			else		   System.out.println("수정 실패.");
			
			break;
			
		case 2 : // 나이
			System.out.print("수정할 나이 입력 : ");
			int age = sc.nextInt();
			int result2 = service.updateAge(age, stdNo);
			
			if(result2 > 0) System.out.println("수정 성공.");
			else		   System.out.println("수정 실패.");
			break;
			
		case 3 : // 전공
			System.out.print("수정할 전공 입력 : ");
			String major = sc.next();
			int result3 = service.updateMajor(major, stdNo);
			
			if(result3 > 0) System.out.println("수정 성공.");
			else		   System.out.println("수정 실패.");
			break;
			
		default : System.out.println("1, 2, 3번 중 고르세요.");
		}
		
		
	}

	/** 2. 전체 학생 조회(SELECT)
	 * 
	 */
	private void selectAll() throws Exception{
		System.out.println("\n====2. 전체 학생 조회====\n");
		
		List<Student> stdList = service.selectAll();
		
		if (stdList.isEmpty()) {
			System.out.println("조회 결과가 없습니다."); 
			return;
		}
		
		for(Student std : stdList) {
			System.out.println(std);
		}
		
	}

	
	/** 1. 학생 등록(INSERT)
	 * 
	 */
	private void insertStd() throws Exception{
		System.out.println("\n====1. 학생 등록====\n");
		
		System.out.print("학생 이름 : ");
		String stdName = sc.next();
		
		System.out.print("학생 나이 : ");
		int stdAge = sc.nextInt();
		sc.nextLine();
		
		System.out.print("학생 전공 : ");
		String stdMajor = sc.next();
		
		//입력받은 값 3개를 한번에 묶어서 전달할 수 있도록
		// User DTO 객체를 생성한 후 필드에 값을 세팅
		Student std = new Student();
		
		// setter 이용
		std.setStdName(stdName);
		std.setStdAge(stdAge);
		std.setStdMajor(stdMajor);
		
		// 서비스 메서드 호출(INSERT) 후 결과 반환(결과 행의 갯수가 돌아오기에 int) 받기
		int result = service.insertStd(std);
		// service 객체(UserService)에 있는 insertUser() 라는 이름의 메서드를 호출하겠다는 뜻.
		
		// 반환된 결과에 따라 출력할 내용 선택
		if(result > 0) {
			System.out.println("\n" + stdName + " 학생이 등록되었습니다.\n");
			
		} else {
			System.out.println("\n***등록 실패***\n");
			
		}
		
	}

}
