package edu.kh.jdbc.homework.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.homework.model.dto.Student;

import static edu.kh.jdbc.homework.common.JDBCTemplate.*;

public class StudentDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	/** 1. 학생 등록 DAO
	 * @param conn
	 * @param std
	 * @return
	 */
	public int insertStd(Connection conn, Student std) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = """
					INSERT INTO KH_STUDENT 
					VALUES(SEQ_STD_NO.NEXTVAL, ?, ?, ?, DEFAULT )
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, std.getStdName());
			pstmt.setInt(2, std.getStdAge());
			pstmt.setString(3, std.getStdMajor());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


	/** 2. 전체 학생 조회 DAO
	 * @param conn
	 * @return
	 */
	public List<Student> selectAll(Connection conn) throws Exception{
		
		List<Student> stdList = new ArrayList<Student>();
		
		try {
			String sql = """
					SELECT STD_NO, STD_NAME, STD_AGE, MAJOR,
					TO_CHAR(ENT_DATE, 'YYYY"년" MM"월" DD"일"') ENT_DATE
					FROM KH_STUDENT 
					ORDER BY STD_NO
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int stdNo = rs.getInt("STD_NO");
				String stdName = rs.getString("STD_NAME");
				int stdAge = rs.getInt("STD_AGE");
				String stdMajor = rs.getString("MAJOR");
				String enrollDate = rs.getString("ENT_DATE");
				
				Student std = new Student(stdNo, stdName, stdAge, stdMajor, enrollDate);
				
				stdList.add(std);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return stdList;
	}


	/** 3-1. 이름과 일치하는 학생 조회 DAO
	 * @param conn
	 * @param stdName
	 * @return
	 */
	public int selectStdNo(Connection conn, String stdName) throws Exception{
		int stdNo = 0;
		
		try {
			String sql = """
					SELECT STD_NO
					FROM KH_STUDENT
					WHERE STD_NAME = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, stdName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				stdNo = rs.getInt("STD_NO");
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return stdNo;
	}


	/** 3-2. 이름 수정하기 DAO
	 * @param conn
	 * @param name
	 * @param stdNo
	 * @return
	 */
	public int updateName(Connection conn, String name, int stdNo) throws Exception{
		int result = 0;
		
		try {
			String sql = """
					UPDATE KH_STUDENT
					SET STD_NAME = ?
					WHERE STD_NO = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, stdNo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 3-3. 나이 수정하기 DAO
	 * @param conn
	 * @param age
	 * @param stdNo
	 * @return
	 */
	public int updateAge(Connection conn, int age, int stdNo) throws Exception {
		int result = 0;
		
		try {
			String sql = """
					UPDATE KH_STUDENT
					SET STD_AGE = ?
					WHERE STD_NO = ?
					""";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, age);
			pstmt.setInt(2, stdNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 3-4. 전공 수정하기 DAO
	 * @param conn
	 * @param major
	 * @param stdNo
	 * @return
	 */
	public int updateMajor(Connection conn, String major, int stdNo) throws Exception{
		int result = 0;
		
		try {
			String sql = """
					UPDATE KH_STUDENT
					SET MAJOR = ?
					WHERE STD_NO = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, major);
			pstmt.setInt(2, stdNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 4. 학생 삭제 DAO
	 * @param conn
	 * @param num
	 * @return
	 */
	public int deleteStd(Connection conn, int num) throws Exception{
		int result = 0;
		
		try {
			String sql = """
					DELETE FROM KH_STUDENT
					WHERE STD_NO = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 5. 해당 전공의 학생 조회 DAO
	 * @param conn
	 * @param major
	 * @return
	 */
	public List<Student> selectMajor(Connection conn, String major) throws Exception {
		
		List<Student> stdMajor = new ArrayList<Student>();
		
		try {
			String sql = """
					SELECT STD_NO, STD_NAME, STD_AGE, MAJOR,
					TO_CHAR(ENT_DATE, 'YYYY"년" MM"월" DD"일"') ENT_DATE
					FROM KH_STUDENT 
					WHERE MAJOR = ?
					ORDER BY STD_NO
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, major);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int stdNo = rs.getInt("STD_NO");
				String stdName = rs.getString("STD_NAME");
				int stdAge = rs.getInt("STD_AGE");
				String Major = rs.getString("MAJOR");
				String enrollDate = rs.getString("ENT_DATE");
				
				Student std = new Student(stdNo, stdName, stdAge, Major, enrollDate);
				
				stdMajor.add(std);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return stdMajor;
	}
	
	

}
