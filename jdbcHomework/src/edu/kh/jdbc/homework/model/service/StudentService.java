package edu.kh.jdbc.homework.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.homework.common.JDBCTemplate;
import edu.kh.jdbc.homework.model.dao.StudentDAO;
import edu.kh.jdbc.homework.model.dto.Student;

public class StudentService {

	private StudentDAO dao = new StudentDAO();
	
	/** 1. 학생 등록 서비스
	 * @param std
	 * @return
	 */
	public int insertStd(Student std) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.insertStd(conn, std);
		
		if(result > 0) { // INSERT 성공
			JDBCTemplate.commit(conn);
			
		} else { // INSERT 실패
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	/** 2. 전체 학생 조회 서비스
	 * @return
	 */
	public List<Student> selectAll() throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		
		List<Student> stdList = dao.selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return stdList;
	}

	/** 3-1. 이름과 일치하는 학생 조회
	 * @param stdName
	 * @return
	 */
	public int selectStdNo(String stdName) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		
		int stdNo = dao.selectStdNo(conn, stdName);
		
		JDBCTemplate.close(conn);
		
		return stdNo;
	}

	/** 3-2. 이름 수정하기
	 * @param name
	 * @param stdNo
	 * @return
	 */
	public int updateName(String name, int stdNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateName(conn, name, stdNo);
		
		if(result > 0) JDBCTemplate.commit(conn);
		else		   JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	/** 3-3. 나이 수정하기
	 * @param age
	 * @param stdNo
	 * @return
	 */
	public int updateAge(int age, int stdNo) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateAge(conn, age, stdNo);
		
		if(result > 0) JDBCTemplate.commit(conn);
		else		   JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	/** 3-4. 전공 수정하기
	 * @param major
	 * @param stdNo
	 * @return
	 */
	public int updateMajor(String major, int stdNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateMajor(conn, major, stdNo);
		
		if(result > 0) JDBCTemplate.commit(conn);
		else		   JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	/** 4. 학생 삭제 서비스
	 * @param num
	 * @return
	 */
	public int deleteStd(int num) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.deleteStd(conn, num);
		
		if(result > 0) JDBCTemplate.commit(conn);
		else		   JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	/** 5. 해당 전공의 학생 조회 서비스
	 * @param major
	 * @return
	 */
	public List<Student> selectMajor(String major) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		
		List<Student> stdMajor = dao.selectMajor(conn, major);
		
		JDBCTemplate.close(conn);
		
		return stdMajor;
	}

	
	
	
	

}
